package com.boot.basics.coding.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author cherrishccl
 * @Date 2020/9/4 17:01
 * @Version 1.0
 * @Description
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
            /*try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            try {
                stmt =conn.prepareStatement("select * from dual");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(null != stmt){
                for(int i = 0; i < 10000; i++){
                    String batchSql = "insert into t_user(user_name, age, salary, max_size) values (" +
                            "'name" + i + "'," + i + "," + i + "," + i +
                            ")";
                    try {
                        stmt.addBatch(batchSql);
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
