package com.boot.basics.coding.tool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2020/8/11 11:17
 * @Version 1.0
 * @Description
 */
public class UtilsTest {
    public static void main(String[] args) throws SQLException {
        //test1();
        test2();
        //test3();
    }

    private static void test3() throws SQLException {
        JdbcUtils jdbcUtils = new JdbcUtils();
        String sql = "INSERT INTO tmp_user(user_name, cert_no, cert_type, address, user_type, remark) VALUES (?, ?, ?, ?, ?, ?)";
        int loopCount = 100;
        int dataCount = 10000;
        long start = System.currentTimeMillis();
        Connection conn = jdbcUtils.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 外层循环提交次数
        for(int i = 0; i < loopCount; i++){
            // 内层循环 单次提交数据量
            for(int j = 1; j <= dataCount; j++){
                String type = (i % 2 == 0 ? "00" : "01");
                int seq = i * dataCount +  j;
                pstmt.setObject(1, "user" + seq);
                pstmt.setObject(2, "1000" + seq);
                pstmt.setObject(3, type);
                pstmt.setObject(4, "重庆市" + seq);
                pstmt.setObject(5, type);
                pstmt.setObject(6, "江北区" + seq);
                pstmt.addBatch();
            }
            // 阻塞
            pstmt.executeBatch();
            System.out.println("--------------------------");
            conn.commit();
            System.out.println("批量耗时" + i + "：" + (System.currentTimeMillis() - start));
        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
    }

    private static void test2() throws SQLException {
        JdbcUtils jdbcUtils = new JdbcUtils();
        String sqlPrefix = "INSERT INTO tmp_user(user_name, cert_no, cert_type, address, user_type, remark) VALUES";
        int loopCount = 100;
        int dataCount = 10000;
        long start = System.currentTimeMillis();
        // 外层循环提交次数
        for(int i = 0; i < loopCount; i++){
            StringBuilder sqlSuffix = new StringBuilder();
            // 内层循环 单次提交数据量
            for(int j = 1; j <= dataCount; j++){
                String type = (i % 2 == 0 ? "00" : "01");
                int seq = i * dataCount +  j;
                sqlSuffix.append("(")
                        .append("'user"+ seq + "', ")
                        .append("'1000"+ seq + "', ")
                        .append("'" + type + "', ")
                        .append("'重庆市" + seq + "', ")
                        .append("'" + type + "', ")
                        .append("'江北区" + seq + "'")
                        .append(")");
                //.append("), ");
                if(j != dataCount){
                    sqlSuffix.append(", ");
                }
            }
            //String sql = sqlPrefix + sqlSuffix.substring( 0 , sqlSuffix.length() - 2 );
            String sql = sqlPrefix + sqlSuffix;
            //System.out.println(sql);
            jdbcUtils.executeBatch(sql, false);
            jdbcUtils.commit();
            System.out.println("批量耗时" + i + "：" + (System.currentTimeMillis() - start));
        }
//        jdbcUtils.releaseConn();
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
    }

    private static void test1() throws SQLException {
        JdbcUtils jdbcUtils = new JdbcUtils();
        String sql = "insert into tmp_user(user_name, cert_no, cert_type, address, user_type, remark) values (?, ?, ?, ?, ?, ?)";
        sql = "insert into tmp_user(user_name, cert_no, cert_type, address, user_type, remark) values";
        long start = System.currentTimeMillis();
        // 222500   222370
        int end = 1000000;
        for(int i = 100000; i < end; i++){
            String type = (i % 2 == 0 ? "00" : "01");
            // jdbcUtils.execute(sql, Arrays.asList("user" + i, "1000" + i, type, "重庆市" + i, type, "江北区" + i), false);
            String vals[] = new String[]{"user" + i, "1000" + i, type, "重庆市" + i, type, "江北区" + i};
            String val = String.format(" ('%s', '%s', '%s', '%s', '%s', '%s')", vals);
            sql = sql.concat(val);
            //System.out.println(sql);
            if((i > 0 && i % 2000 == 0) || i == (end - 1)){
                boolean flag = jdbcUtils.execute(sql, null, false);
                jdbcUtils.commit();
                System.out.println(i + "------->" + flag);
                sql = "insert into tmp_user(user_name, cert_no, cert_type, address, user_type, remark) values";
                continue;
            }
            sql = sql.concat(",");
        }
        jdbcUtils.commit();
        System.out.println(sql);
        System.out.println("--------->" + (System.currentTimeMillis() - start));
    }
}
