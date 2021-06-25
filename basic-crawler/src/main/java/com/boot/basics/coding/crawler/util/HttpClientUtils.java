package com.boot.basics.coding.crawler.util;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2021/6/25 9:37
 * @Version 1.0
 * @Description
 * <dependency>
 *    <groupId>org.apache.httpcomponents</groupId>
 *    <artifactId>httpclient</artifactId>
 *    <version>4.5.13</version>
 * </dependency>
 */
public class HttpClientUtils {
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=utf8";

    public static String doGet(String url, Map<String, String> headers, Map<String, String> params) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultStr = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (null != params) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 添加头
            addHeaders(httpGet, headers);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                resultStr = EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
            }
        } catch (Exception e) {
            // 自定义处理异常
            e.printStackTrace();
        } finally {
            close(httpclient, response);
        }
        return resultStr;
    }

    public static String doPostJson(String url, Map<String, String> headers, String jsonStr){
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultStr = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 添加header
            addHeaders(httpPost, headers);
            StringEntity entity = new StringEntity(jsonStr);

            entity.setContentType(CONTENT_TYPE_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                resultStr = EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
            }
        } catch (Exception e) {
            // 自定义处理异常
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }

        return resultStr;
    }

    public static String doPostForm(String url, Map<String, String> headers, Map<String, Object> params) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultStr = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 添加header
            addHeaders(httpPost, headers);
            // 创建参数列表
            if (null != params) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, (String) params.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultStr = EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
        } catch (Exception e) {
            // 自定义处理异常
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }

        return resultStr;
    }

    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse response){
        if(null != response){
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(null != httpClient){
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addHeaders(HttpPost httpPost, Map<String, String> headers){
        if(null != httpPost && null != headers && headers.size() > 0){
            for(Map.Entry<String, String> entry : headers.entrySet()){
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    private static void addHeaders(HttpGet httpGet, Map<String, String> headers){
        if(null != httpGet && null != headers && headers.size() > 0){
            for(Map.Entry<String, String> entry : headers.entrySet()){
                httpGet.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }
    private HttpClientUtils(){}

    public static void main(String[] args) {
        Map<String, String> headers = new HashMap<>(4);
        // 创建请求内容
        headers.put("HTTP Method", "POST");
        headers.put("Connection", "Keep-Alive");
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("x-token", "123456789");
        doGet("http://localhost:8080/uinfo", headers, null);
    }
}
