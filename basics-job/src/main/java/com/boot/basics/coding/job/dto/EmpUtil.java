package com.boot.basics.coding.job.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author cherrishccl
 * @Date 2021/7/12 15:36
 * @Version 1.0
 * @Description EmpUtil
 */
@Data
@ExcelTarget("empUtil")
public class EmpUtil {


    @Excel(name = "序号", width = 30, isColumnHidden = true)
    private Integer id;

    @Excel(name = "员工姓名", width = 30, groupName = "基本信息")
    private String empName;

    @Excel(name = "年龄", width = 30, type = 10, groupName = "基本信息")
    private Integer age;

    @Excel(name = "入职时间", width = 30, groupName = "工作信息", format = "yyyy/MM/dd HH:mm")
    private Date hiredate;

    @Excel(name = "薪酬", width = 30, type = 10, groupName = "工作信息")
    private BigDecimal salary;

}
