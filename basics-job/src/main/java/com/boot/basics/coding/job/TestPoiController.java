package com.boot.basics.coding.job;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.boot.basics.coding.job.dto.DeptUtil;
import com.boot.basics.coding.job.dto.EmpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author cherrishccl
 * @Date 2021/7/12 14:09
 * @Version 1.0
 * @Description TestPoiController
 */
@Slf4j
@RestController
public class TestPoiController {
    @RequestMapping("/import")
    public Map<String,Object> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<OrderData> list = EasyPoiUtils.importExcel(file, 1, 2, OrderData.class);
        log.info("{}", list);
        return null;
    }

    @GetMapping("/export")
    public Map<String,Object> exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<OrderData> list = new ArrayList<>();
        OrderData orderData = new OrderData();
        orderData.setBizNo("BIZ20210712");
        orderData.setAmount(BigDecimal.valueOf(333));
        orderData.setBizName("7月金额");
        orderData.setRemark("收到");
        orderData.setDetails(new ArrayList<>());
        OrderData.OrderDetail orderDetail = new OrderData.OrderDetail();
        orderDetail.setAmt(BigDecimal.valueOf(111));
        orderDetail.setNo(1);
        orderData.getDetails().add(orderDetail);
        orderDetail = new OrderData.OrderDetail();
        orderDetail.setAmt(BigDecimal.valueOf(222));
        orderDetail.setNo(2);
        orderData.getDetails().add(orderDetail);
        list.add(orderData);

        orderData = new OrderData();
        orderData.setBizNo("BIZ20210612");
        orderData.setAmount(BigDecimal.valueOf(111));
        orderData.setBizName("6月金额");
        orderData.setRemark("收到1");
        orderData.setDetails(new ArrayList<>());
        orderDetail = new OrderData.OrderDetail();
        orderDetail.setAmt(BigDecimal.valueOf(111));
        orderDetail.setNo(1);
        orderData.getDetails().add(orderDetail);
        list.add(orderData);
        log.info("{}", list);
        EasyPoiUtils.exportExcel(list, "订单数据", "订单数据", OrderData.class, "订单数据.xls", true, response);
        return null;
    }

    @GetMapping("export1")
    public void export(HttpServletResponse response) {
        try {
            // 设置响应输出的头类型
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=user.xls");
            // =========easypoi部分
            List<DeptUtil> exportList = new LinkedList<DeptUtil>();
            DeptUtil deptUtil = new DeptUtil();
            deptUtil.setId(1);
            deptUtil.setDeptName("研发部");

            List<EmpUtil> ll = new LinkedList<EmpUtil>();
            EmpUtil empUtil = new EmpUtil();
            empUtil.setId(1);
            empUtil.setAge(20);
            empUtil.setEmpName("研发");
            empUtil.setHiredate(new Date());

            EmpUtil empUtil2 = new EmpUtil();
            empUtil2.setId(1);
            empUtil2.setAge(20);
            empUtil2.setEmpName("测试");
            empUtil2.setHiredate(new Date());
            ll.add(empUtil);
            ll.add(empUtil2);
            deptUtil.setEmps(ll);
            exportList.add(deptUtil);
            ExportParams deptExportParams = new ExportParams();
            // 设置sheet得名称
            deptExportParams.setSheetName("员工报表1");

            Map<String, Object> deptExportMap = new HashMap<>();
            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
            deptExportMap.put("title", deptExportParams);
            // 模版导出对应得实体类型
            deptExportMap.put("entity", DeptUtil.class);
            // sheet中要填充得数据
            deptExportMap.put("data", exportList);

            List<Map<String, Object>> sheetsList = new ArrayList<>();
            sheetsList.add(deptExportMap);
            Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
