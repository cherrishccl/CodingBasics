package com.boot.basics.coding;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @Author cherrishccl
 * @Date 2021/8/6 14:10
 * @Version 1.0
 * @Description FileUtils
 */
@Slf4j
public class FileUtils {
    public static void close(FileChannel channel){
        if(null != channel){
            try {
                channel.close();
            } catch (IOException e) {
                log.error("close file channel error: ", e);
            }
        }
    }
    public static void mergeFile(String fileName, String chunkFilePath, String newFilePath){
        if(null != chunkFilePath && !"".equals(chunkFilePath.trim())){
            File dir = new File(chunkFilePath);
            if(dir.exists() && dir.isDirectory()){
                File[] childs = dir.listFiles();
                List<File> children = new ArrayList<>(Arrays.asList(childs));
                Collections.sort(children, new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        if(Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())){
                            return -1;
                        }
                        return 1;
                    }
                });
                if(null == fileName || "".equals(fileName.trim())){
                    fileName = System.currentTimeMillis() + "";
                }
                String newFileName = null;
                if(null != newFilePath && !"".equals(newFilePath.trim())){
                    newFileName = newFilePath + fileName;
                }else {
                    newFileName = dir.getParent() + "/" + fileName;
                }
                newFileName = newFileName.replaceAll("//", "/");
                // 合并后的文件
                File newFile = new File(newFileName);
                FileChannel newFileChannel = null;
                FileChannel inChannel = null;
                try{
                    if(newFile.exists()){
                        newFile.createNewFile();
                    }
                    newFileChannel = new FileOutputStream(newFile).getChannel();

                    for(File file : children){
                        inChannel = new FileInputStream(file).getChannel();
                        inChannel.transferTo(0, inChannel.size(), newFileChannel);
                        inChannel.close();
                        // 删除分片
                        file.delete();
                    }
                }catch (Exception e) {
                    log.error("合并文件异常");
                    //发生异常，文件合并失败 ，删除创建的文件
                    newFile.delete();
                    dir.delete();
                } finally {
                    close(inChannel);
                    close(newFileChannel);
                }
                //删除分片所在的文件夹
                dir.delete();
            }
        }
    }
}
