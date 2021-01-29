package com.boot.basics.coding.job;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2021/1/29 10:54
 * @Version 1.0
 * @Description FileUtilTest
 */
public class FileUtilTest {
    public static void main(String[] args) throws IOException {
        File targetP = new File("C:\\Users\\Administrator\\Desktop\\platformSql.sql");
        File targetC = new File("C:\\Users\\Administrator\\Desktop\\capitalSql.sql");
         List<String> txtps = FileUtils.readLines(targetP, "UTF-8");
         List<String> txtcs = FileUtils.readLines(targetC, "UTF-8");
         StringBuilder mergeStr = new StringBuilder("");
         for(int i = 0; i < txtps.size(); i+=6){
                if(txtps.get(i).equals(txtcs.get(i))){
                    mergeStr.append(txtps.get(i)).append("\n").append(txtps.get(i + 1)).append("\n").append(txtps.get(i + 2)).append("\n").append(txtps.get(i + 3)).append("\n").append(txtps.get(i + 4)).append("\n");
                    mergeStr.append("\n");
                    mergeStr.append(txtcs.get(i + 2)).append("\n").append(txtcs.get(i + 3)).append("\n").append(txtcs.get(i + 4)).append("\n");
                    mergeStr.append(txtps.get(i + 5)).append("\n");
                }
         }

        FileUtils.write(new File("C:\\Users\\Administrator\\Desktop\\docs\\merge.sql"), mergeStr.toString(), "UTF-8", false);

    }
}
