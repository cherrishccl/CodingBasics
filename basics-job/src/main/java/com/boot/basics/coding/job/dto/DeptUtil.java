package com.boot.basics.coding.job.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2021/7/12 15:35
 * @Version 1.0
 * @Description DeptUtil
 */
@Data
@ExcelTarget("empUtil")
public class DeptUtil {


    @Excel(name = "部门编号", width = 30 , needMerge = true)
    private Integer id;

    @Excel(name = "部门名称", width = 30 , needMerge = true)
    private String deptName;

    @ExcelCollection(name = "员工信息")
    private List<EmpUtil> emps;

}
