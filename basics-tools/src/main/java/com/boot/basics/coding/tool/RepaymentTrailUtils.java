package com.boot.basics.coding.tool;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Author cherrishccl
 * @Date 2021/1/13 10:16
 * @Version 1.0
 * @Description 贷款还款试算
 */
public class RepaymentTrailUtils {

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
    public static void equalPrincipalAndInterest(BigDecimal amount, BigDecimal yearRate, int months) {
        // 月利率
        BigDecimal monthRate = yearRate.divide(BIGDECAMAL_12, DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
        //每月还款额=[总本金×月利率×(1+月利率)^还款月数]÷[(1+月利率)^还款月数-1]
        //(1+月利率)^还款月数
        BigDecimal tmp = monthRate.add(BigDecimal.ONE).pow(months);
        BigDecimal monthPayTotal = amount.multiply(monthRate).multiply(tmp).divide(tmp.subtract(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);

		/*总利息
			计算总利息有3种方法:
			1.在循环中将每月应还利息累加
			2.总利息=月还本息×总期数-总本金
			3.公式：总利息=[总期数×贷款本金×月利率×(1+月利率)^还款月数]÷[(1+月利率)^还款月数-1]-总本金
			具体使用哪种根据情况而定，推荐第二种
		*/
        //2:
        BigDecimal sumInterest = monthPayTotal.multiply(new BigDecimal(months)).subtract(amount);
        //3:
		/*BigDecimal sumInterest = new BigDecimal(months).multiply(amount).multiply(monthRate)
				.multiply(BigDecimal.ONE.add(monthRate).pow(months))
				.divide(BigDecimal.ONE.add(monthRate).pow(months).subtract(BigDecimal.ONE),2,BigDecimal.ROUND_HALF_UP)
				.subtract(amount);*/

        BigDecimal remainTotal = amount;    //剩余本金付初始值为总本金
        BigDecimal monthPrincipal = BigDecimal.ZERO;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("本金：" + amount.setScale(2) + "\t年利率：" + yearRate.multiply(BIGDECAMAL_100) + "%\t总期数：" + months + "\t总利息:" + sumInterest);
        System.out.println("期次\t回款本息（元）\t回款利息（元）\t回款本金（元）\t剩余本金（元）\t回款日期");
        for (int i = 1; i <= months; i++) {
			/*
			 	每月应还本金和利息也有2种方法:
			 	1.公式:
			 		每月应还利息=总本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
			 		每月应还本金=总本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
			 */
			/*BigDecimal interest = amount.multiply(monthRate)
					.multiply(BigDecimal.ONE.add(monthRate).pow(months).subtract(BigDecimal.ONE.add(monthRate).pow(i-1)))
					.divide(BigDecimal.ONE.add(monthRate).pow(months).subtract(BigDecimal.ONE),2,BigDecimal.ROUND_HALF_UP);

			monthPrincipal = amount.multiply(monthRate)
					.multiply(BigDecimal.ONE.add(monthRate).pow(i-1))
					.divide(BigDecimal.ONE.add(monthRate).pow(months).subtract(BigDecimal.ONE),2,BigDecimal.ROUND_HALF_UP);
			*/


            //2.月还利息=剩余本金×月利率
            //月还本金=月还本息-月还利息
            //具体使用哪种根据喜好而定，第二种更容易理解
            BigDecimal interest = remainTotal.multiply(monthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            //为避免因精度损失产生误差，最后一期  还款利息=月还本息-剩余本金
            //为避免利息产生负数的情况出现 当利息小于等于0时利息赋值为0.1元
            if (months == i) {
                interest = monthPayTotal.subtract(remainTotal);
                if (interest.compareTo(BigDecimal.ZERO) <= 0) {
                    interest = new BigDecimal(1).divide(new BigDecimal(10), 2, BigDecimal.ROUND_HALF_UP);
                }
            }
            //月还本金=月还本息-月还利息
            monthPrincipal = monthPayTotal.subtract(interest);

            remainTotal = remainTotal.subtract(monthPrincipal);
            calendar.add(Calendar.MONTH, 1);
            System.out.println(i + "\t" + monthPayTotal + "\t\t" + interest + "\t\t" + monthPrincipal + "\t\t" + remainTotal + "\t\t" + sdf.format(calendar.getTime()));
        }

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

    public static double getPerMonthPrincipalInterest(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / 12;
        BigDecimal monthIncome = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 2, BigDecimal.ROUND_DOWN);
        return monthIncome.doubleValue();
    }

    public static void main(String[] args) {
        double amount = 400000D;
        double yearRate = 0.0534D;
        yearRate = 0.0325D;
        int months = 360;
        System.out.println(getPerMonthPrincipalInterest(amount, yearRate, months));

        System.out.println(monthOfEqualPrincipalAndInterest(BigDecimal.valueOf(amount), BigDecimal.valueOf(yearRate), months, 2).toPlainString());
        equalPrincipalAndInterest(BigDecimal.valueOf(amount), BigDecimal.valueOf(yearRate), months);

        System.out.println(LoanCalculator.equalPrincipalAndInterest(BigDecimal.valueOf(amount), BigDecimal.valueOf(yearRate), months));
    }
}
