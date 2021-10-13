package com.boot.basics.coding.tool;

import com.deepoove.poi.XWPFTemplate;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2021/10/13 15:32
 * @Version 1.0
 * @Description WordUtils
 */
public class WordUtils {
    /**
     * 填充数据
     * @param datas
     */
    public static String fillData(String templatePath, Map<String, Object> datas, String newFilePath){
        XWPFTemplate xwpfTemplate = XWPFTemplate.compile(templatePath);
        xwpfTemplate.render(datas);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(newFilePath);
            xwpfTemplate.write(fos);
            fos.flush();
            fos.close();
            xwpfTemplate.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String word2pdf(String wordPath, String newFilePath){
        if(null == newFilePath || "".equals(newFilePath.trim())){
            newFilePath = wordPath.replace(".docx", ".pdf");
        }
        try {
            FileInputStream fis = new FileInputStream(wordPath);
            FileOutputStream fos = new FileOutputStream(newFilePath);
            XWPFDocument doc = new XWPFDocument(fis);
            PdfConverter.getInstance().convert(doc, fos, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  void decoderBase64File(String base64Code, String targetPath,String catalogue) {
        try {
            File file = new File(catalogue);
            if(file.exists()==false){
                file.mkdirs();
            }
            byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
            FileOutputStream out = new FileOutputStream(targetPath);
            out.write(buffer);
            out.close();
        }catch (Exception e){

        }
    }
    public static void main(String[] args) {
        Map<String, Object> datas = new HashMap<>();
        datas.put("title", "XXXXXXXXXXXXXXXXXX");
        datas.put("date", "2021-10-13");
        datas.put("value", "123");
        fillData("C:\\Users\\Administrator\\Desktop\\TEMPLATE.docx", datas, "C:\\Users\\Administrator\\Desktop\\1013.docx");
        word2pdf("C:\\Users\\Administrator\\Desktop\\1013.docx", null);
    }
}
