package com.boot.basics.coding.tool;

import java.sql.*;

/**
 * @Author cherrishccl
 * @Date 2020/9/4 17:01
 * @Version 1.0
 * @Description
 * 用JDBC执行批量提交正确方式:
 * 1. Statement stmt = conn.createStatement();
 *    for(int i = 0; i < 10000; i++){
 *        String batchSql = "insert into t_user(name, sex, age) values('name', 'F', 22)";
 *        stmt.addBatch(batchSql);
 *    }
 *    stmt.executeBatch();
 *
 * 2. 连接参数添加&rewriteBatchedStatements=true
 *    PreparedStatement stmt = conn.prepareStatement("insert into t_user(name, sex, age) values (?, ?, ?)");
 *    for(int i = 0; i < 10000; i++){
 *        stmt.setString(1, "name");
 *        stmt.setString(2, "F");
 *        stmt.setString(3, 22);
 *        stmt.addBatch();
 *    }
 */
public class BatchTest1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://10.253.130.55:3306/test?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false" +
                "&allowMultiQueries=true&rewriteBatchedStatements=true";
        String url1 = "jdbc:mysql://10.253.130.55:3306/test?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&allowMultiQueries=true";
        String username = "yhjr_dev_dba";
        String password = "yhjrdb2019*";
        /************************插入10000条数据**************************************/
        // url 和 url1 效率差不多
//        batch1(driver, url, username, password); // 平均耗时4175
//        batch1(driver, url1, username, password); // 平均耗时4156
        // url 和 url1 效率差不多
//        batch2(driver, url, username, password); // 平均耗时1951
//        batch2(driver, url1, username, password); // 平均耗时1873
        /***********************插入100条数据***************************************/
        // url 和 url1 效率差不多
//         batch3(driver, url, username, password); // 平均耗时7730
//         batch3(driver, url1, username, password); // 平均耗时6208
            // url 和 url1 效率差不多
//         batch4(driver, url, username, password); // 平均耗时6096
//         batch4(driver, url1, username, password); // 平均耗时6056
            // url 和 url1 效率差不多
//         batch5(driver, url, username, password); // 平均耗时6466
//         batch5(driver, url1, username, password); // 平均耗时6310
            // url 和 url1 效率差不多
//         batch6(driver, url, username, password); // 平均耗时5969
//         batch6(driver, url1, username, password); // 平均耗时6201

        /***********************插入10000条数据***************************************/
//           batch7(driver, url, username, password); // 平均耗时990
//           batch7(driver, url1, username, password); // 平均耗时>>>>>120000

//            batch8(driver, url, username, password); // 平均耗时602
//            batch8(driver, url1, username, password); // 平均耗时>>>>>120000

        batch9(driver, url, username, password);

    }

    private static Connection connect(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    private static void batch1(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);
        Statement stmt = conn.createStatement();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                    "'name" + i + "'," + i + "," + i + "," + i +
                    ")";
            stmt.addBatch(batchSql);
        }
        stmt.executeBatch();
        // 4175
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }
    private static void batch2(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);

        Statement stmt = conn.createStatement();
        conn.setAutoCommit(false);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                    "'name" + i + "'," + i + "," + i + "," + i +
                    ")";
            stmt.addBatch(batchSql);
        }
        stmt.executeBatch();
        conn.commit();
        // 1398
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }

    private static void batch3(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);

        Statement stmt = conn.prepareStatement("select * from dual");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                    "'name" + i + "'," + i + "," + i + "," + i +
                    ")";
            stmt.addBatch(batchSql);
        }
        stmt.executeBatch();
        // 7730
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }
    private static void batch4(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);

        Statement stmt = conn.prepareStatement("select * from dual");
        conn.setAutoCommit(false);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                    "'name" + i + "'," + i + "," + i + "," + i +
                    ")";
            stmt.addBatch(batchSql);
        }
        stmt.executeBatch();
        conn.commit();
        // 7730
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }

    private static void batch5(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);
        PreparedStatement stmt = conn.prepareStatement("select * from dual");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                    "'name" + i + "'," + i + "," + i + "," + i +
                    ")";
            stmt.addBatch(batchSql);
        }
        stmt.executeBatch();
        // 6162
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }
    private static void batch6(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);
        PreparedStatement stmt = conn.prepareStatement("select * from dual");
        conn.setAutoCommit(false);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                    "'name" + i + "'," + i + "," + i + "," + i +
                    ")";
            stmt.addBatch(batchSql);
        }
        stmt.executeBatch();
        conn.commit();
        // 6162
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }

    private static void batch7(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);
        PreparedStatement stmt = conn.prepareStatement("insert into t_user(user_name, age, salary, max_size) values (?, ?, ?, ?)");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            stmt.setInt(1, i + 1);
            stmt.setInt(2, i + 1000);
            stmt.setInt(3, i + 1);
            stmt.setInt(4, i);
            stmt.addBatch();
        }
        stmt.executeBatch();
        // 6162
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }
    private static void batch8(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);
        PreparedStatement stmt = conn.prepareStatement("insert into t_user(user_name, age, salary, max_size) values (?, ?, ?, ?)");
        conn.setAutoCommit(false);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            stmt.setInt(1, i + 1);
            stmt.setInt(2, i + 1000);
            stmt.setInt(3, i + 1);
            stmt.setInt(4, i);
            stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        // 6162
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }

    private static void batch9(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Connection conn = connect(driver, url, username, password);
        PreparedStatement stmt = conn.prepareStatement("insert into t_user(user_name, age, salary, max_size) values (?, ?, ?, ?)");
        conn.setAutoCommit(false);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            stmt.setInt(1, i + 1);
            stmt.setInt(2, i + 1000);
            stmt.setInt(3, i + 1);
            stmt.setInt(4, i);
            stmt.addBatch();
        }
        stmt.executeBatch();
        //conn.commit();
        conn.setAutoCommit(true);
        // 6162
        System.out.println("耗时======>" + (System.currentTimeMillis() - start));
    }
}
