package com.boot.basics.coding.tool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * @Author cherrishccl
 * @Date 2020/8/11 11:17
 * @Version 1.0
 * @Description
 */
public class UtilsTest {
    public static void main(String[] args) throws SQLException {
//        Connection conn = JdbcUtils1.getConnection();
        JdbcUtils jdbcUtils = new JdbcUtils();
        String sql = "insert into tmp_user(user_name, cert_no, cert_type, address, user_type, remark) values (?, ?, ?, ?, ?, ?)";
        long start = System.currentTimeMillis();
        for(int i = 41621; i < 100000; i++){
            System.out.println("--------------->" + i);
            String type = (i % 2 == 0 ? "00" : "01");
            jdbcUtils.executeByPreparedStatement(sql, Arrays.asList("user" + i, "1000" + i, type, "重庆市" + i, type, "江北区" + i));
            if(i > 0 && i % 500 == 0){
                jdbcUtils.commit();
            }
        }
        System.out.println("--------->" + (System.currentTimeMillis() - start));

    }
}
