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
                stmt =conn.prepareStatement("select * from dual");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(null != stmt){
                stmt.addBatch();
            }
            int[] rs = stmt.executeBatch();
        }
    }
}
