package com.boot.basics.coding.tool;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * @Author cherrishccl
 * @Date 2021/10/15 16:15
 * @Version 1.0
 * @Description 图片压缩
 */
public class ImgUtils {
    public static void main(String[] args) throws IOException {
//        Thumbnails.of(new File("D:\\downloads\\L2GN7k6p2ZLLskaEXqyOxdDJeaj23yQy.jpg"))
//                //.size(3000, 4000)
//                .scale(1.0D)
//                .toFile(new File("D:\\123.jpg"));
        compressImg("D:\\var\\flygo\\data\\images", 1.0D);
    }

    public static void compressImg(String srcDir, double scale) throws IOException {
        File dir = new File(srcDir);
        if(dir.exists() && dir.isDirectory()){
            File[] files = dir.listFiles();
            if(null != files && files.length > 0){
                for(File file : files){
                    if(file.isDirectory()){
                        compressImg(file.getAbsolutePath(), scale);
                    }else {
                        //String absolutePath = file.getAbsolutePath();

                        String fileName = file.getName();
                        String parentDir = file.getParent();
                        System.out.println(parentDir);
                        System.out.println(fileName);

                        Thumbnails.of(file).scale(scale).toFile(new File(parentDir + "/1_" + fileName));
                    }
                }
            }
        }
    }
}
