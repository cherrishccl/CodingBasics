package com.boot.basics.coding.tool;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2020/9/10 14:30
 * @Version 1.0
 * @Description
 */
public class ExcelTest {
    public static void main(String[] args) throws SQLException, ParseException, IOException {
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false&allowMultiQueries=true&rewriteBatchedStatements=true";
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "123456";
        String sql = "select jobno, step, sub_step, process_function, bhdate, start_time, end_time  from bh_proc_log order by bhdate, jobno, step";
        JdbcUtils jdbcUtils = new JdbcUtils(driver, url, username, password);
        Connection conn = jdbcUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<ExcelData> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        while (rs.next()){
            ExcelData excelData = new ExcelData();
            excelData.setJobno(rs.getInt(1));
            excelData.setStep(rs.getInt(2));
            excelData.setSub_step(rs.getInt(3));
            excelData.setProcess_function(rs.getString(4));
            excelData.setBhdate(rs.getString(5));
            excelData.setStart_time(rs.getString(6));
            excelData.setEnd_time(rs.getString(7));
            long cost = sdf.parse(excelData.getEnd_time()).getTime() - sdf.parse(excelData.getStart_time()).getTime();
            excelData.setCost(cost + "");
            list.add(excelData);
        }
        //File file = new File("C:\\Users\\Administrator\\Desktop\\task1.xls");
        OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\task1.xls");
        EasypoiUtils.exportExcel(list, null, "excel",
                ExcelData.class, "task1.xls", true, os);
        os.close();
    }

    public static class ExcelData {
        @Excel(name = "jobno")
        private Integer jobno;
        @Excel(name = "step")
        private Integer step;
        @Excel(name = "sub_step")
        private Integer sub_step;
        @Excel(name = "process_function")
        private String process_function;
        @Excel(name = "bhdate")
        private String bhdate;
        @Excel(name = "start_time")
        private String start_time;
        @Excel(name = "end_time")
        private String end_time;

        @Excel(name = "cost")
        private String cost;

        public Integer getJobno() {
            return jobno;
        }

        public void setJobno(Integer jobno) {
            this.jobno = jobno;
        }

        public Integer getStep() {
            return step;
        }

        public void setStep(Integer step) {
            this.step = step;
        }

        public Integer getSub_step() {
            return sub_step;
        }

        public void setSub_step(Integer sub_step) {
            this.sub_step = sub_step;
        }

        public String getProcess_function() {
            return process_function;
        }

        public void setProcess_function(String process_function) {
            this.process_function = process_function;
        }

        public String getBhdate() {
            return bhdate;
        }

        public void setBhdate(String bhdate) {
            this.bhdate = bhdate;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }
    }
}
