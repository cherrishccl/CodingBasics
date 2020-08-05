package com.boot.basics.coding.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @Author cherrishccl
 * @Date 2020/8/5 10:03
 * @Version 1.0
 * @Description
 */
public class DataUtil {

    /**
     * 文件转字节数组
     *
     * @param file
     * @return
     */
    public static byte[] fileToByte(File file) {
        byte[] ret = null;
        try {
            if (file == null) {
                System.out.println("入参文件不能为空");
                return ret;
            }
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            in.close();
            out.close();
            ret = out.toByteArray();
        } catch (Exception e) {
            System.out.println("文件转字节数据异常：");
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 字节数组转文件保存
     *
     * @param buf
     * @param filePath
     * @param fileName
     */
    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            System.out.println("字节数组转文件保存异常：");
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param textBytes txt文本字节数组
     * @param charSet
     * @return
     */
    public static List<String> byte2List(byte[] textBytes, String charSet) {
        ArrayList<String> list = new ArrayList<>();
        InputStreamReader inReader = null;
        BufferedReader bufReader = null;
        try {
            inReader = new InputStreamReader(new ByteArrayInputStream(textBytes), charSet);
            bufReader = new BufferedReader(inReader);
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            System.out.println("字节数组转List集合异常：");
            e.printStackTrace();
            throw new RuntimeException("字节数组转List集合异常", e);
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inReader != null) {
                try {
                    inReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * 解压缩.
     *
     * @param inputByte byte[]数组类型的数据
     * @return 解压缩后的数据
     * @throws IOException
     */
    public static byte[] inflater(final byte[] inputByte) throws IOException {
        try {
            int compressedDataLength = 0;
            Inflater compresser = new Inflater(false);
            compresser.setInput(inputByte, 0, inputByte.length);
            ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
            byte[] result = new byte[1024];
            try {
                while (!compresser.finished()) {
                    compressedDataLength = compresser.inflate(result);
                    if (compressedDataLength == 0) {
                        break;
                    }
                    o.write(result, 0, compressedDataLength);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Inflater 解压缩数据异常：", ex);
            } finally {
                o.close();
            }
            compresser.end();
            return o.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Inflater 解压缩数据异常：", e);
        }
    }

    /**
     * base64解码后再解压缩
     *
     * @param defalterAndEncode64Str 压缩后再base64编码字符串
     * @return 原字节数组
     */
    public static byte[] inflaterAndDecode64(String defalterAndEncode64Str) {
        try {
            byte[] deflaterAy = Base64.getDecoder().decode(defalterAndEncode64Str);
            return inflater(deflaterAy);
        } catch (IOException e) {
            throw new RuntimeException("base64解码解压缩异常:", e);
        }
    }

    /**
     * 压缩.
     *
     * @param inputByte 需要解压缩的byte[]数组
     * @return 压缩后的数据
     * @throws IOException
     */
    public static byte[] deflater(final byte[] inputByte) throws IOException {
        try {
            int compressedDataLength = 0;
            Deflater compresser = new Deflater();
            compresser.setInput(inputByte);
            compresser.finish();
            ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
            byte[] result = new byte[1024];
            try {
                while (!compresser.finished()) {
                    compressedDataLength = compresser.deflate(result);
                    o.write(result, 0, compressedDataLength);
                }
            } finally {
                o.close();
            }
            compresser.end();
            return o.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Deflater 压缩数据异常：", e);
        }
    }

    /**
     *  base64解码后再解压缩
     *
     * @param defalterAndEncode64Str 压缩后再base64编码字符串
     * @return 原字节数组
     */
    /**
     * 数据进行压缩后再base64编码
     *
     * @param inputByte 数据
     * @return
     */
    public static String deflaterAndEncode64(byte[] inputByte) {
        try {
            byte[] deflaterAy = deflater(inputByte);
            return Base64.getEncoder().encodeToString(deflaterAy);
        } catch (IOException e) {
            throw new RuntimeException("数据压缩、base64编码异常:", e);
        }
    }

    /**
     * base64解码解压缩
     *
     * @param defalterAndEncode64
     * @return
     */
    public static byte[] inflaterAndDecode64Byte(byte[] defalterAndEncode64) {
        try {
            byte[] deflaterAy = Base64.getDecoder().decode(defalterAndEncode64);
            return inflater(deflaterAy);
        } catch (IOException e) {
            throw new RuntimeException("base64解码解压缩异常:", e);
        }
    }

    /**
     * 数据压缩，BASE64编码
     *
     * @param inputByte
     * @return
     */
    public static byte[] deflaterAndEncode64Byte(byte[] inputByte) {
        try {
            byte[] deflaterAy = deflater(inputByte);
            return Base64.getEncoder().encode(deflaterAy);
        } catch (IOException e) {
            throw new RuntimeException("数据压缩、base64编码异常:", e);
        }
    }
}
