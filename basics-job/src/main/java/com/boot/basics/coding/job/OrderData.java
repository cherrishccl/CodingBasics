package com.boot.basics.coding.job;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2021/7/12 14:12
 * @Version 1.0
 * @Description OrderData
 */
@ExcelTarget("order")
@lombok.Data
public class OrderData {
    @Excel(name = "业务编号", needMerge = true)
    private String bizNo;
    @Excel(name = "业务名称", needMerge = true)
    private String bizName;
    @Excel(name = "金额", needMerge = true)
    private BigDecimal amount;
    @ExcelCollection(name = "明细数据")
    List<OrderDetail> details;
    @Excel(name = "备注", needMerge = true)
    private String remark;

    @ExcelTarget("order")
    @lombok.Data
    public static class OrderDetail{
        @Excel(name = "明细编号")
        private Integer no;
        @Excel(name = "明细金额")
        private BigDecimal amt;
    }
}
