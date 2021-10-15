package com.boot.basics.coding.tool;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * @Author cherrishccl
 * @Date 2021/10/15 16:15
 * @Version 1.0
 * @Description ImgUtils
 */
public class ImgUtils {
    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("D:\\downloads\\L2GN7k6p2ZLLskaEXqyOxdDJeaj23yQy.jpg"))
                //.size(3000, 4000)
                .scale(1.0D)
                .toFile(new File("D:\\123.jpg"));
    }
}
