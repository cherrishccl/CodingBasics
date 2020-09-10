package com.boot.basics.coding.tool;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2020/8/27 10:12
 * @Version 1.0
 * @Description
 */
public class EasypoiUtils {

    /*public static void downloadExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                     boolean isCreateHeader, HttpServletResponse response) throws IOException {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null){
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            response.setHeader("Content-Length", String.valueOf(baos.size()));
            response.getOutputStream().write(baos.toByteArray());
        }
    }*/
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, OutputStream outputStream) throws IOException {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, outputStream, exportParams);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, OutputStream outputStream, ExportParams exportParams) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        //Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null){
            writeOutputStream(fileName, workbook, outputStream);
        }
    }

    private static void writeOutputStream(String fileName, Workbook workbook, OutputStream outputStream) throws IOException {
        //log.info("Write OutputStream: {}", fileName);
         workbook.write(outputStream);
        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        outputStream.write(baos.toByteArray());*/
    }

    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> clazz) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }

        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);

        try {
            return ExcelImportUtil.importExcel(new File(filePath), clazz, params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /*public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> clazz) {
        if (file == null) {
            return null;
        }

        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);

        try {
            return ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    private EasypoiUtils() {
    }


}
