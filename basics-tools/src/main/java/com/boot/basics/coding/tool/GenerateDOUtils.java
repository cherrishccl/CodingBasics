package com.boot.basics.coding.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author cherrishccl
 * @Date 2021/11/11 16:33
 * @Version 1.0
 * @Description GenerateDOUtils
 */
public class GenerateDOUtils {
    private final static String DB_PROPERTIES = "schema.properties";
    private final static String DB_DRIVER = "driver";
    private final static String DB_URL = "url";
    private final static String DB_USERNAME = "username";
    private final static String DB_PASSWORD = "password";
    private final static String DB_DRIVER_PATH = "driver_path";
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private static final String SQL_QRY_TABLES = "select table_name, table_comment from information_schema.tables where table_schema=? and table_type='base table'";
    private static final String SQL_QRY_COLUMNS = "select column_name, data_type, column_comment, column_type from information_schema.columns where table_schema='finance_ibp' and table_name='baboon_garage'";

    private static final String CLZ_COM_STR = "/**\n" +
            " * @Author generated\n" +
            " * @Date %s\n" +
            " * @Version 1.0\n" +
            " * @Description %s\n" +
            " */\n" +
            "public class %s {";

    static {
        try {
//            String r = GenerateDOUtils.class.getClassLoader().getResource(DB_PROPERTIES).getPath();
//            FileReader fr = new FileReader(r);
            Properties p = new Properties();
//            p.load(fr);
            p.load(GenerateDOUtils.class.getClassLoader().getResourceAsStream(DB_PROPERTIES));
            driver = p.getProperty(DB_DRIVER);
            url = p.getProperty(DB_URL);
            username = p.getProperty(DB_USERNAME);
            password = p.getProperty(DB_PASSWORD);

            String driverPath = p.getProperty(DB_DRIVER_PATH);
            // 加载jar包
            loadJar(driverPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败!");
        }
    }

    private static void loadJar(String path) throws NoSuchMethodException {
        File jarFile = new File(path);
        if(!jarFile.exists()){
            throw new RuntimeException("jar包文件不存在");
        }
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        // 获取方法的访问权限，保存原始值
        boolean accessible = method.isAccessible();
        if(!accessible){
            // 修改访问权限为可写
            method.setAccessible(true);
        }
        // 获取系统类加载器
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        // 获取jar文件的url路径
        URL url = null;
        try {
            url = jarFile.toURI().toURL();
            // jar路径加入到系统url路径里
            method.invoke(urlClassLoader, url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            // 回写访问权限
            method.setAccessible(accessible);
        }
    }

    private static Map<String, String> getTables(String schema) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SQL_QRY_TABLES);
        pstmt.setString(1, schema);
        ResultSet rs = pstmt.executeQuery();
        Map<String, String> map = new LinkedHashMap<>();
        while (rs.next()){
            String tableName = rs.getString("table_name");
            String tableComment = rs.getString("table_comment");
            map.put(tableName, tableComment);
        }
        return map;
    }

    private static void generate(String schema) throws SQLException {
        Map<String, String> tableMap = getTables(schema);
        if(null != tableMap && tableMap.size() > 0){
            for(Map.Entry<String, String> entry : tableMap.entrySet()){
                String tableName = entry.getKey();
                String clzName = "";
                if(null != tableName && !"".equals(tableName.trim())){
                    String[] ts = tableName.split("_");
                    for(String s : ts){
                        if(null != s && !"".equals(s.trim())){
                            char[] cs = s.toCharArray();
                            if(Character.isLetter(cs[0])){
                                cs[0]-=32;
                            }
                            clzName = clzName + String.valueOf(cs);
                        }
                    }
                }
                String tableComment = entry.getValue();
                if(null != clzName && !"".equals(clzName.trim())){
                    String comstr = String.format(CLZ_COM_STR, new Date().toString(), null == tableComment ? clzName : tableComment, clzName);
                    System.out.println(comstr);
                }
                System.out.println(clzName + "============" + tableComment);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        GenerateDOUtils.generate("finance_ibp");
    }

    private GenerateDOUtils(){}
}
