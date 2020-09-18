package com.boot.basics.coding.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author cherrishccl
 * @Date 2020/9/4 17:01
 * @Version 1.0
 * @Description PreparedStatement.prepareStatement(sql) 中 addBatch() 执行1次, addBatch(sql)执行N次
 * 正确使用方式: PreparedStatement + addBatch(), Statement + addBatch(sql)
 */
public class BatchTest {
    public static void main(String[] args) {
        batch("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&allowMultiQueries=true&rewriteBatchedStatements=true",
                "root", "123456");
    }

    private static void batch(String driver, String url, String username, String password){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(null != conn){
            PreparedStatement stmt = null;
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String pSql = "insert into t_user(user_name, age, salary, max_size) values (?, ?, ?, ?)";
            pSql = "update t_user set age = ?, salary = ?, max_size = ? where id = ?";
            try {
                // stmt =conn.prepareStatement("select * from dual");
                stmt =conn.prepareStatement(pSql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(null != stmt){
                for(int i = 1; i <= 10000; i++){
                    try {
                        String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                                "'name" + i + "'," + i + "," + i + "," + i +
                                ")";
                        stmt.addBatch(batchSql);

                        // stmt.setString(1, "name" + i);
                        /*stmt.setInt(1, i + 1);
                        stmt.setInt(2, i + 1000);
                        stmt.setInt(3, i + 1);
                        stmt.setInt(4, i);
                        stmt.addBatch();*/
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                long start = System.currentTimeMillis();
                int[] rs = stmt.executeBatch();
                conn.commit();
                System.out.println("total ======> " + (System.currentTimeMillis() - start));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
