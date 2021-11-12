package com.boot.basics.coding.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.*;
import java.util.Date;

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
    private final static String TABLE_NAME = "table_name";
    private final static String TABLE_COMMENT = "table_comment";

    private final static String COLUMN_NAME = "column_name";
    private final static String COLUMN_COMMENT = "column_comment";
    private final static String COLUMN_TYPE = "column_type";
    private final static String COLUMN_KEY = "column_key";
    private final static String COLUMN_KEY_PRI = "PRI";
    private final static String COLUMN_KEY_UNI = "UNI";

    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private static final String SQL_QRY_TABLES = "select table_name, table_comment from information_schema.tables where table_schema = ? and table_type='base table'";
    private static final String SQL_QRY_COLUMNS = "select column_name, data_type, column_comment, column_type, column_key from information_schema.columns where table_schema = ? and table_name = ?";

    private static final String CLZ_COM_STR = "/**\n" +
            " * @Author generated\n" +
            " * @Date %s\n" +
            " * @Version 1.0\n" +
            " * @Description %s\n" +
            " */\n" +
            "public class %s {\n";

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
            String tableName = rs.getString(TABLE_NAME);
            String tableComment = rs.getString(TABLE_COMMENT);
            map.put(tableName, tableComment);
        }
        rs.close();
        pstmt.close();
        return map;
    }
    private static List<Map<String, String>> getColumns(String schema, String table) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SQL_QRY_COLUMNS);
        pstmt.setString(1, schema);
        pstmt.setString(2, table);
        ResultSet rs = pstmt.executeQuery();
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> map = null;
        while (rs.next()){
            map = new LinkedHashMap<>();

            map.put(COLUMN_NAME, rs.getString(COLUMN_NAME));
            map.put(COLUMN_COMMENT, rs.getString(COLUMN_COMMENT));
            map.put(COLUMN_TYPE, rs.getString(COLUMN_TYPE));
            map.put(COLUMN_KEY, rs.getString(COLUMN_KEY));

            result.add(map);
        }
        rs.close();
        pstmt.close();
        return result;
    }

    private static String parseColumns(List<Map<String, String>> columns){
        String result = "";
        if(null != columns && columns.size() > 0){
            for(Map<String, String> map : columns){
                String columnName = map.get(COLUMN_NAME);
                if(null != columnName && !"".equals(columnName)){
                    String[] cns = columnName.split("_");
                    String clnName = "";
                    for(int i = 0; i < cns.length; i++){
                        String cn = cns[i];
                        if(null != cn && !"".equals(cn.trim())){
                            cn = cn.toLowerCase();
                            char[] cs = cn.toCharArray();
                            if(i != 0){
                                if(Character.isLetter(cs[0])){
                                    cs[0]-=32;
                                }
                            }
                            clnName = clnName + String.valueOf(cs);
                        }
                    }
                    StringBuilder sb = new StringBuilder("");
                    sb.append("\t/** ");
                    String columnComment = map.get(COLUMN_COMMENT);
                    if(null != columnComment && !"".equals(columnComment.trim())){
                        sb.append(columnComment);
                    }else {
                        sb.append(columnName);
                    }
                    sb.append(" */").append("\n");
                    String columnKey = map.get(COLUMN_KEY);
                    if(COLUMN_KEY_PRI.equals(columnKey)){
                        sb.append("\t@Id").append("\n").append("@GeneratedValue(strategy = GenerationType.IDENTITY)").append("\n");
                    }
                    // sb.append("@Column(name = \"mark_id\", length = 32)");
                    sb.append("\t@Column(name = ").append("\"").append(columnName).append("\"").append(")").append("\n");
                    String columnType = map.get(COLUMN_TYPE);
                    if(null != columnType && !"".equals(columnType.trim())){
                        String bracketTxt = null;
                        if(columnType.contains("(") && columnType.contains(")")){
                            bracketTxt = columnType.substring(columnType.indexOf("(") + 1, columnType.indexOf(")"));
                        }
                        if(null != bracketTxt){
                            columnType = columnType.replace("(" + bracketTxt + ")", "");
                        }

                        sb.append("\tprivate ");
                        if("int".equals(columnType)){
                            sb.append("Integer ");
                        }else if("bigint".equals(columnType)){
                            sb.append("Long ");
                        }else if("date".equals(columnType)){
                            sb.append("Date ");
                        }else if("timestamp".equals(columnType)){
                            sb.append("Date ");
                        }else if("double".equals(columnType)){
                            sb.append("Double ");
                        }else if("char".equals(columnType)){
                            sb.append("String ");
                        }else if("varchar".equals(columnType)){
                            sb.append("String ");
                        }else if("decimal".equals(columnType)){
                            sb.append("BigDecimal ");
                        }
                        sb.append(clnName).append(";\n");
                    }
                    result = sb.toString();
                }
            }
        }
        return result;
    }

    private static void write2File(String content, String fileName) throws IOException {
        if(null != fileName && !"".equals(fileName.trim())){
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            if(null != content && !"".equals(content)){
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
            }
        }
    }

    private static void generate(String schema, String filDir, String packageName) throws SQLException, IOException {
        Map<String, String> tableMap = getTables(schema);
        if(null != tableMap && tableMap.size() > 0){
            String packageStr = "";
            if(null != packageName && !"".equals(packageName.trim())){
                packageStr = "package " + packageName + ";\n";
            }
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
                    // System.out.println(comstr);
                    String clnStr = parseColumns(getColumns(schema, tableName));
                    String clzFullStr = packageStr + comstr + clnStr + "\n}";
                    write2File(clzFullStr, filDir + clzName + ".java");
                }
                // System.out.println(clzName + "============" + tableComment);
            }
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        GenerateDOUtils.generate("finance_ibp", "D:/var/temp/clz/", "com.scw.finance");
    }

    private GenerateDOUtils(){}
}
