package com.boot.basics.coding.tool;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2021/1/28 14:13
 * @Version 1.0
 * @Description GenSql
 */
@Slf4j
public class GenSql {
    public static void main(String[] args) {
      /*  List<BuyDebtData> list = EasypoiUtils.importExcel("C:\\Users\\Administrator\\Desktop\\买债数据.xlsx",
                0, 0, BuyDebtData.class);
        list.stream().forEach(buyDebtData -> {
            // log.info("{}", buyDebtData);
        });*/
        ExcelImportUtil.importExcel(new File("C:\\Users\\Administrator\\Desktop\\买债数据.xlsx"), BuyDebtData.class, null);
    }

    static class BuyDebtData{
        @Excel(name = "序号")
        private String no;
        @Excel(name = "海乐行项目编号")
        private String bizNo;
        @Excel(name = "客户名称")
        private String custName;
        @Excel(name = "身份证号码")
        private String idNo;
        @Excel(name = "借款合同编号")
        private String contractNo;
        @Excel(name = "借款余额")
        private String loanBal;
        @Excel(name = "借款利息")
        private String interest;
        @Excel(name = "已支付金额")
        private String payAmt;
        @Excel(name = "债权总额")
        private String loanAmt;
        @Excel(name = "债权转让价款")
        private String buyAmt;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getBizNo() {
            return bizNo;
        }

        public void setBizNo(String bizNo) {
            this.bizNo = bizNo;
        }

        public String getCustName() {
            return custName;
        }

        public void setCustName(String custName) {
            this.custName = custName;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getContractNo() {
            return contractNo;
        }

        public void setContractNo(String contractNo) {
            this.contractNo = contractNo;
        }

        public String getLoanBal() {
            return loanBal;
        }

        public void setLoanBal(String loanBal) {
            this.loanBal = loanBal;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getPayAmt() {
            return payAmt;
        }

        public void setPayAmt(String payAmt) {
            this.payAmt = payAmt;
        }

        public String getLoanAmt() {
            return loanAmt;
        }

        public void setLoanAmt(String loanAmt) {
            this.loanAmt = loanAmt;
        }

        public String getBuyAmt() {
            return buyAmt;
        }

        public void setBuyAmt(String buyAmt) {
            this.buyAmt = buyAmt;
        }
    }
}
