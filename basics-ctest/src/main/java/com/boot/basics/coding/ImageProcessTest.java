package com.boot.basics.coding;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2021/9/26 14:24
 * @Version 1.0
 * @Description ImageProcessTest
 */
public class ImageProcessTest {
    public static void main(String[] args) throws IOException {
        String baseDir = "D:\\var\\temp\\lhyh\\";
        List<String> urlList = Arrays.asList("https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/GJJODrLtP4JeYgReoNSJjvxsIWMj8U3O.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/gDRDh3IVuqqLtkgrYANFpHxnef3gHvyB.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/kimcZo8XtNuHXczdnfAf77kORQYzgmqe.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/zpgT2QbmF9AKnBQDyagnIrJOg54FmvR7.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/91mOh5iVlj1OMgXfVvgoxk4JO09h6NZD.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/odeHW7vv6mUzL9nfpRTgbzzEpAlvGjrR.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/ib8vNqZKwMP4ARFlxmrc6x2AjiGPJz8M.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/FqgvD15hL2ZN48SfaCidXQOcKasWlJdU.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/qZ5Ze1AOOE45OstSoMpdWOzFL1j37GP8.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/QV4xYHmKKFNKxrS95q9U2MNn6crZiZnB.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/uZbUn78qlot2xhYt8sqhkbqfSHtr1Ye8.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/sb8x7vVIazWu6XtzrIt4cpqVKszXwj3f.png",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/mCz2UMSSnfBrmYerynV9sxgY9edg4JNL.png",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/aegbTSxi3PwBso3vQ5vbIUZkpbBOde7W.jpg",
                "https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210926/UyS8TF7O0NuBR5chi4OZfwjRyxZCt63T.jpg");

        urlList = Arrays.asList("https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210924/WqCqT11QBtGx2V97e9DJje6zKNbpNSXL.jpg?x-oss-process=image/resize,p_40","https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210924/92HshMwLfAlKmtjX8aUUJg1HOTIiEGYu.jpg?x-oss-process=image/resize,p_40","https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210924/Wa044QWuseWU9CuwltvCOnInVdXZg0lR.jpg?x-oss-process=image/resize,p_40","https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210924/x9wIn6tL4EcGjqU2TnorXN07vwqASmtQ.jpg?x-oss-process=image/resize,p_40","https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210924/RbIKvJo4br9t1A2U2oW9DZDt2xDUDmZJ.jpg?x-oss-process=image/resize,p_40","https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210924/GhybHS9ImBTcI1RjnVcl1PlHW9drR8mP.jpg?x-oss-process=image/resize,p_40","https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210924/c7BFClzukN0ewYnI35GIKVtnxcjsdmO1.jpg?x-oss-process=image/resize,p_40","https://songche-prod.oss-cn-shanghai.aliyuncs.com/20210924/hYWbatCRdt49IkFsS4UQ1p3iWXi49taM.jpg?x-oss-process=image/resize,p_40");
        String compressStr = "?x-oss-process=image/resize,p_40";
        for(String urlStr : urlList){
            String urlStr1 = urlStr.replace("https://", "");
            urlStr1 = urlStr1.replace("http://", "");
            String[] packs =urlStr1.split("/");
            URL url = new URL(urlStr + compressStr);
            File dir = new File(baseDir + packs[1]);
            if(!dir.exists()){
                dir.mkdir();
            }
            FileUtils.copyURLToFile(url, new File(baseDir + packs[1] + "\\" + packs[2]));
        }


    }
}
