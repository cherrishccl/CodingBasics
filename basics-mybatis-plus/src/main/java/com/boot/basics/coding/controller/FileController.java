package com.boot.basics.coding.controller;

import com.boot.basics.coding.model.RestResp;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @Author cherrishccl
 * @Date 2021/8/6 13:52
 * @Version 1.0
 * @Description FileController
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {
    private static final String finalDirPath = "D:\\temp";
    /**
     * 判断文件是否上传过，是否存在分片，断点续传
     */
    @RequestMapping(value = "/checkShard", method = RequestMethod.POST)
    @ResponseBody
    public RestResp checkshard(HttpServletRequest request) {
        String filemd5 = request.getParameter("fileMd5");
        // 读取目录里的所有文件
        File dir = new File(finalDirPath + "/" + filemd5);
        File[] childs = dir.listFiles();
        //文件上传下标索引, 文件没有上传过，下标为零, 文件上传中断过，返回当前已经上传到的下标
        int chunkIndex = 0;
        if(null != childs){
            chunkIndex = childs.length - 1;
        }
        return RestResp.success(chunkIndex);
    }
    /**
     * 上传文件
     *
     * @param paramDTO
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/chunkUpload", method = RequestMethod.POST)
    @ResponseBody
    public RestResp chunkUpload(FileParamDTO paramDTO, HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            String fileName = paramDTO.getName();
            int chunkIndex = paramDTO.getChunkIndex();
            File file = new File(finalDirPath + "/" + paramDTO.getMd5());
            if (!file.exists()) {
                file.mkdir();
            }
            File chunkFile = new File(finalDirPath + "/" +  paramDTO.getMd5() + "/" + chunkIndex);
            try{
                FileUtils.copyInputStreamToFile(paramDTO.getFile().getInputStream(), chunkFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return RestResp.success();
    }

    /**
     * 分片上传成功之后，合并文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/chunkMerge", method = RequestMethod.POST)
    @ResponseBody
    public RestResp chunkMerge(HttpServletRequest request, HttpServletResponse response) {
        FileChannel outChannel = null;
        try {
            String filename = request.getParameter("fileName");
            String filemd5 = request.getParameter("fileMd5");
            // 读取目录里的所有文件
            File dir = new File(finalDirPath + "/" + filemd5);
            File[] childs = dir.listFiles();
            // 转成集合，便于排序
            List<File> fileList = new ArrayList<File>(Arrays.asList(childs));
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                        return -1;
                    }
                    return 1;
                }
            });
            // 合并后的文件
            File outputFile = new File(finalDirPath + "/" + filename);
            // 创建文件
            if(outputFile.exists()){
                //logger.info("创建文件");
                outputFile.createNewFile();
            }
            outChannel = new FileOutputStream(outputFile).getChannel();
            FileChannel inChannel = null;
            try {
                for (File file : fileList) {
                    inChannel = new FileInputStream(file).getChannel();
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                    inChannel.close();
                    // 删除分片
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
                //发生异常，文件合并失败 ，删除创建的文件
                outputFile.delete();
                dir.delete();//删除文件夹
            }finally {
                if(inChannel!=null){
                    inChannel.close();
                }
            }
            dir.delete(); //删除分片所在的文件夹
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(outChannel!=null){
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    @lombok.Data
    static class FileParamDTO{
        // 用户id
        private String uid;
        //任务ID
        private String id;
        //总分片数量
        private int chunks;
        //当前为第几块分片
        private int chunkIndex;
        //当前分片大小
        private long size = 0L;
        //文件名
        private String name;
        //分片对象
        private MultipartFile file;
        // MD5
        private String md5;
    }
}
