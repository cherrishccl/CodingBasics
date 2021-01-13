package com.boot.basics.coding.tool;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2021/1/13 13:12
 * @Version 1.0
 * @Description LoanCalculator
 */
@lombok.Data
public class LoanCalculator {
    private static int DECIMAL_SCALE = 9;
    private static BigDecimal BIGDECAMAL_100 = new BigDecimal(100);
    private static BigDecimal BIGDECAMAL_12 = new BigDecimal(12);
    private static BigDecimal BIGDECAMAL_30 = new BigDecimal(30);
    private static BigDecimal BIGDECAMAL_360 = new BigDecimal(360);
    /**
     * 等额本息 还款列表计算(自然月，即回款日期与出借日期相同)
     * 公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
     *
     * @param amount   贷款金额
     * @param yearRate 年利率Interest
     * @param months   贷款月数
     * @return
     */
    public static LoanCalculateDTO equalPrincipalAndInterest(BigDecimal amount, BigDecimal yearRate, int months) {
        LoanCalculateDTO resultDTO = new LoanCalculateDTO(amount, yearRate, months);
        // 月利率
        BigDecimal monthRate = yearRate.divide(BIGDECAMAL_12, DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
        //每月还款额=[总本金×月利率×(1+月利率)^还款月数]÷[(1+月利率)^还款月数-1]
        //(1+月利率)^还款月数
        BigDecimal tmp = monthRate.add(BigDecimal.ONE).pow(months);
        // 每月本息还款总额
        BigDecimal monthPayTotal = amount.multiply(monthRate).multiply(tmp).divide(tmp.subtract(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);
        // 总利息
        BigDecimal totalInterest = monthPayTotal.multiply(new BigDecimal(months)).subtract(amount);
        resultDTO.setInterest(totalInterest);

        // 剩余本金付初始值为总本金
        BigDecimal remainTotal = amount;
        BigDecimal monthPrincipal = BigDecimal.ZERO;
        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i <= months; i++) {
            // 月还利息=剩余本金×月利率, 月还本金=月还本息-月还利息
            BigDecimal monthInterest = remainTotal.multiply(monthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            //为避免因精度损失产生误差，最后一期  还款利息=月还本息-剩余本金
            //为避免利息产生负数的情况出现 当利息小于等于0时利息赋值为0.1元
            if (months == i) {
                monthInterest = monthPayTotal.subtract(remainTotal);
                if (monthInterest.compareTo(BigDecimal.ZERO) <= 0) {
                    monthInterest = BigDecimal.ONE.divide(BigDecimal.TEN, 2, BigDecimal.ROUND_HALF_UP);
                }
            }
            //月还本金=月还本息-月还利息
            monthPrincipal = monthPayTotal.subtract(monthInterest);

            remainTotal = remainTotal.subtract(monthPrincipal);

            resultDTO.getRepayPlan().add(new LoanRepayPlanDTO(monthPayTotal, monthPrincipal, monthInterest, remainTotal, i));
        }

        return resultDTO;

    }

    public static BigDecimal monthOfEqualPrincipalAndInterest(BigDecimal amount, BigDecimal yearRate, int months, int scale) {
        // 月利率
        BigDecimal monthRate = yearRate.divide(BIGDECAMAL_12, DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
        //每月还款额=[总本金×月利率×(1+月利率)^还款月数]÷[(1+月利率)^还款月数-1]
        //(1+月利率)^还款月数
        BigDecimal tmp = monthRate.add(BigDecimal.ONE).pow(months);
        BigDecimal monthPayTotal = amount.multiply(monthRate).multiply(tmp).divide(tmp.subtract(BigDecimal.ONE), scale < 0 ? DECIMAL_SCALE : scale, BigDecimal.ROUND_HALF_UP);
        return monthPayTotal;
    }

    /**
     * 贷款计算结果
     */
    @lombok.Data
    public static class LoanCalculateDTO{
        /** 本息和 */
        private BigDecimal amount;
        /** 本金 */
        private BigDecimal principal;
        /** 利息 */
        private BigDecimal interest;
        /** 年利率 */
        private BigDecimal yearRate;
        /** 月利率 */
        private Integer months;
        /** 还款计划 */
        private List<LoanRepayPlanDTO> repayPlan;

        public LoanCalculateDTO() {}

        public LoanCalculateDTO(BigDecimal principal, BigDecimal yearRate, Integer months) {
            this.principal = principal;
            this.yearRate = yearRate;
            this.months = months;
            this.repayPlan = new ArrayList<>(months);
        }
    }

    /**
     * 贷款还款计划
     */
    @lombok.Data
    public static class LoanRepayPlanDTO{
        /** 本息和 */
        private BigDecimal amount;
        /** 本金 */
        private BigDecimal principal;
        /** 利息 */
        private BigDecimal interest;
        /** 剩余本金 */
        private BigDecimal remainPrincipal;
        /** 期次 */
        private Integer term;

        public LoanRepayPlanDTO(BigDecimal amount, BigDecimal principal, BigDecimal interest, BigDecimal remainPrincipal, Integer term) {
            this.amount = amount;
            this.principal = principal;
            this.interest = interest;
            this.remainPrincipal = remainPrincipal;
            this.term = term;
        }

        public LoanRepayPlanDTO() {}
    }
}
