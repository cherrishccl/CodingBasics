package com.boot.basics.coding.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2021/1/28 15:07
 * @Version 1.0
 * @Description ExcelController
 */
@Slf4j
@RestController
public class ExcelController {

    @GetMapping(value = "/pay")
    public String pay(){
        return "sss";
    }
    private final String SQL_P = "begin;\n" +
            "update colobus_deposit_account set deposit_balance = deposit_balance - %s where deposit_account_no = '000000000000000011';\n" +
            "insert into colobus_deposit_account_detail (biz_no, deposit_account_no, customer_id, user_id, customer_name, merchant_no, deposit_use_amount, deposit_balance, deposit_direction, deposit_type_name, deposit_type_no, remark, version, delete_flag, create_time, create_by, update_time, update_by)\n" +
            "select '%s', deposit_account_no, customer_id, user_id, customer_name, merchant_no, %s, deposit_balance, '2', deposit_type_name, deposit_type_no, '海乐行-云贷买债：%s,款项录入', 1, '0', now(), 'SYSTEM', now(), 'SYSTEM' from colobus_deposit_account where deposit_account_no = '000000000000000011';\n" +
            "commit;\n";
    private final String SQL_C = "begin;\n" +
            "update colobus_deposit_account set deposit_balance = deposit_balance + %s where deposit_account_no = '544636055400353795';\n" +
            "insert into colobus_deposit_account_detail (biz_no, deposit_account_no, customer_id, user_id, customer_name, merchant_no, deposit_use_amount, deposit_balance, deposit_direction, deposit_type_name, deposit_type_no, remark, version, delete_flag, create_time, create_by, update_time, update_by)\n" +
            "select '%s', deposit_account_no, customer_id, user_id, customer_name, merchant_no, %s, deposit_balance, '1', deposit_type_name, deposit_type_no, '海乐行-云贷买债：%s,款项录入', 1, '0', now(), 'SYSTEM', now(), 'SYSTEM' from colobus_deposit_account where deposit_account_no = '544636055400353795';\n" +
            "commit;\n";
    @RequestMapping("/upload")
    public Map<String,Object> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<BuyDebtData> list = EasyPoiUtils.importExcel(file, BuyDebtData.class);
        int no = 1;
        StringBuilder psb = new StringBuilder("");
        StringBuilder csb = new StringBuilder("");
        StringBuilder txtsb = new StringBuilder("");
        File targetP = new File("C:\\Users\\Administrator\\Desktop\\docs\\platformSql29.sql");
        File targetC = new File("C:\\Users\\Administrator\\Desktop\\docs\\capitalSql29.sql");
        File txt = new File("C:\\Users\\Administrator\\Desktop\\docs\\content29.txt");
        for(BuyDebtData buyDebtData : list){
            BigDecimal buyAmt = new BigDecimal(buyDebtData.getBuyAmt());
            if(BigDecimal.ONE.compareTo(buyAmt) >= 0){
                continue;
            }
            buyAmt = buyAmt.multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);

            String noStr = String.format("%03d",no);
            psb.append("-- ").append(buyDebtData.getBizNo()).append(", ").append(buyDebtData.getBuyAmt()).append("\n");
            String psql = String.format(SQL_P, buyDebtData.getBuyAmt(), "BD20210129180000" + noStr, buyDebtData.getBuyAmt(), buyDebtData.getBizNo());
            psb.append(psql);

            csb.append("-- ").append(buyDebtData.getBizNo()).append(", ").append(buyDebtData.getBuyAmt()).append("\n");
            String csql = String.format(SQL_C, buyDebtData.getBuyAmt(), "BD20210129180000" + noStr, buyDebtData.getBuyAmt(), buyDebtData.getBizNo());
            csb.append(csql);

            txtsb.append(buyDebtData.getBizNo()).append(",").append(buyAmt.toPlainString()).append(";");
            no++;
        }
        FileUtils.write(targetP, psb.toString(), "UTF-8", false);
        FileUtils.write(targetC, csb.toString(), "UTF-8", false);
        FileUtils.write(txt, txtsb.toString(), "UTF-8", false);

        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","ok");
        map.put("data",list);
        return map;
    }
}
