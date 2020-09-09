# MySQL JDBC Statement.executeBatch实践(执行效率低)
`
现在很少使用原生jdbc去实现代码, 最近在测试MySQL批处理数据遇到一个问题: 
执行Statement.executeBatch时效率极其低下(插入10000条数据), 断点调试时在此处停顿很长时间(908712ms)
`
数据版本:
```
select version();
// 5.7.17-11-V2.0R530D002-20190816-1203-log
```
数据库表:
```
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) DEFAULT NULL,
  `age` smallint(6) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `max_size` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
```
测试代码如下:
```
public class BatchTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        batch("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&allowMultiQueries=true",
                "root", "123456");
    }

    private static void batch(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        // jdbc driver: mysql-connector-java:8.0.21
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
         PreparedStatement pstmt = conn.prepareStatement("select * from dual");
        for(int i = 1; i <= 10000; i++){
            String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                    "'name" + i + "'," + i + "," + i + "," + i +
                    ")";
            pstmt.addBatch(batchSql);
        }
        long start = System.currentTimeMillis();
        // 执行耗时很长
        int[] rs = pstmt.executeBatch();
        conn.commit();
        // total ======> 908712
        System.out.println("total ======> " + (System.currentTimeMillis() - start));

    }
}
```
**原因**
1. MySQL JDBC驱动在默认情况下会无视executeBatch()语句, 把我们期望批量执行的一组sql语句拆散, 一条一条地发给MySQL数据库, 直接造成较低的性能.
2. batchHasPlainStatements=true, 即使用了Statement.addBatch(String), 不执行批处理操作, 一条一条地发给MySQL数据库, 直接造成较低的性能.

JDBC驱动源码:
```
public class ClientPreparedStatement extends com.mysql.cj.jdbc.StatementImpl implements JdbcPreparedStatement {
    /**
     * Does the batch (if any) contain "plain" statements added by
     * Statement.addBatch(String)?
     * 
     * If so, we can't re-write it to use multi-value or multi-queries.
     */
    protected boolean batchHasPlainStatements = false;
    // ......
    protected long[] executeBatchInternal() throws SQLException {
            synchronized (checkClosed().getConnectionMutex()) {
    
                if (this.connection.isReadOnly()) {
                    throw new SQLException(Messages.getString("PreparedStatement.25") + Messages.getString("PreparedStatement.26"),
                            MysqlErrorNumbers.SQL_STATE_ILLEGAL_ARGUMENT);
                }
    
                if (this.query.getBatchedArgs() == null || this.query.getBatchedArgs().size() == 0) {
                    return new long[0];
                }
    
                // we timeout the entire batch, not individual statements
                int batchTimeout = getTimeoutInMillis();
                setTimeoutInMillis(0);
    
                resetCancelledState();
    
                try {
                    statementBegins();
    
                    clearWarnings();
                     // 1. batchHasPlainStatements 包含原始sql语句
                     // 2. rewriteBatchedStatements 使用批量
                    if (!this.batchHasPlainStatements && this.rewriteBatchedStatements.getValue()) {
    
                        if (((PreparedQuery<?>) this.query).getParseInfo().canRewriteAsMultiValueInsertAtSqlLevel()) {
                            return executeBatchedInserts(batchTimeout);
                        }
    
                        if (!this.batchHasPlainStatements && this.query.getBatchedArgs() != null
                                && this.query.getBatchedArgs().size() > 3 /* cost of option setting rt-wise */) {
                            return executePreparedBatchAsMultiStatement(batchTimeout);
                        }
                    }
    
                    return executeBatchSerially(batchTimeout);
                } finally {
                    this.query.getStatementExecuting().set(false);
    
                    clearBatch();
                }
            }
    }
    // ......
}
```
**修正:** 
1. 在连接url加上&rewriteBatchedStatements=true, 如: jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&allowMultiQueries=true&rewriteBatchedStatements=true
2. 使用Statement.addBatch()替换Statement.addBatch(String)
```
public class BatchTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        batch("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&allowMultiQueries=true&rewriteBatchedStatements=true",
                "root", "123456");
    }

    private static void batch(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        String pSql = "insert into t_user(user_name, age, salary, max_size) values (?, ?, ?, ?)";
        PreparedStatement pstmt =conn.prepareStatement(pSql);
        for(int i = 1; i <= 10000; i++){
            try {
                pstmt.setString(1, "name" + i);
                pstmt.setInt(2, i);
                pstmt.setInt(3, i);
                pstmt.setInt(4, i);
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        long start = System.currentTimeMillis();
        int[] rs = pstmt.executeBatch();
        conn.commit();
        // total ======> 2598
        System.out.println("total ======> " + (System.currentTimeMillis() - start));
    }
}
```