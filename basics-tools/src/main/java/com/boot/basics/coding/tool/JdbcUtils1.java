package com.boot.basics.coding.tool;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author cherrishccl
 * @Date 2020/8/11 11:16
 * @Version 1.0
 * @Description JDBC工具类
 */
public class JdbcUtils1 {
    private JdbcUtils1(){}

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    private static Connection conn;

    static {

        /**
         * 使用properties集合读取配置信息
         */
        try {
            String r = JdbcUtils1.class.getClassLoader().getResource("db.properties").getPath();
            FileReader fr = new FileReader(r);
            Properties p = new Properties();
//            p.load(fr);
            p.load(JdbcUtils1.class.getClassLoader().getResourceAsStream("db.properties"));
            /**
             * 获取集合中的数据库连接信息,给成员变量赋值
             */
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

            /**
             * 创建驱动和数据库连接对象
             */
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            /**
             * 如果数据库连接失败，则不应该继续往下，抛出运行时异常给虚拟机，终止程序
             */
            throw new RuntimeException("数据库连接失败！");
        }
    }

    /**
     * 获取数据库连接对象Connection
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * 释放资源
     */
    public static void close(ResultSet rs, Statement stat, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
