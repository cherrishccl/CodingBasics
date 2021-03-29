package com.boot.basics.coding.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2021/3/29 15:22
 * @Version 1.0
 * @Description HttpClientUtils
 */
public class HttpClientUtils {

    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=utf8";

    public static String get(String url, Map<String, String> headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        addHeaders(httpGet, headers);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    public static String post(String url, Map<String, String> headers, String jsonParamStr) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(CONTENT_TYPE, CONTENT_TYPE_JSON);
        httpPost.setEntity(new StringEntity(jsonParamStr, CHARSET_UTF8));
        addHeaders(httpPost, headers);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        return result;
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
}
