package com.boot.basics.coding;

/**
 * @Author cherrishccl
 * @Date 2020/9/14 16:07
 * @Version 1.0
 * @Description
 */
public class SbTest {
    public static void main(String[] args) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into TLA_LN_PLAN_HIS (ID, TRANS_NO, TRANS_TIME, ");
        sql.append("PLAN_ID, LNCINO, LNPLANSEQ, PLANPERI, ISCLR, HCODE, ");
        sql.append("LNPERISTAT, PLANRTNDT, PLANAMT, PLANINT, PHSEQ, RCVAMT, RCVINT, RCVCINT, ");
        sql.append("RCVCCINT, RCVPINT, RCVCPINT, RCVCCPINT, RTNAMT, RTNINT, RTNCINT, RTNCCINT, ");
        sql.append("RTNPINT, RTNCPINT, RTNCCPINT, ADDCINT, ADDCCINT, ADDPINT, ADDCPINT, ADDCCPINT, ");
        sql.append("ADDHINT, ADDGINT, RSAVINT, RSAVCINT, RSAVCCINT, RSAVPINT, RSAVCPINT, RSAVCCPINT, ");
        sql.append("RSAVHINT, RSAVGINT, RSAVGPINT, AMT_ACCUM, INT_ACCUM, CINT_ACCUM, PINT_ACCUM, ");
        sql.append("CPINT_ACCUM, CCINT_ACCUM, GAMT_ACCUM, LINTDT, NOWEINTDT, EHDATE, EGDATE, CLRDT, PRNCNT,  ");

        sql.append("TX_RCVAMT, TX_RCVINT, TX_RCVCINT, TX_RCVCCINT, TX_RCVPINT, ");
        sql.append("TX_RCVCPINT, TX_RCVCCPINT, TX_RTNAMT, TX_RTNINT, TX_RTNCINT, ");
        sql.append("TX_RTNCCINT, TX_RTNPINT, TX_RTNCPINT, TX_RTNCCPINT, TX_ADDCINT, ");
        sql.append("TX_ADDCCINT, TX_ADDPINT, TX_ADDCPINT, TX_ADDCCPINT, TX_ADDHINT, ");
        sql.append("TX_ADDGINT, TX_RSAVINT, TX_RSAVCINT, TX_RSAVCCINT, TX_RSAVPINT, ");
        sql.append("TX_RSAVCPINT, TX_RSAVCCPINT, TX_RSAVHINT, TX_RSAVGINT, TX_RSAVGPINT, ");
        sql.append("TX_AMT_ACCUM, TX_INT_ACCUM, TX_CINT_ACCUM, TX_PINT_ACCUM, ");
        sql.append("TX_CPINT_ACCUM, TX_CCINT_ACCUM, TX_GAMT_ACCUM ) ");

        sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?,?,?,?,?,?,?,?,?,?,");
        sql.append("?,?,?,?,?,?,?,?,?,?,");
        sql.append("?,?,?,?,?,?,?,?,?,?,");
        sql.append("?,?,?,?,?,?,?");
        sql.append(")");
        String str = sql.toString();
        System.out.println(str);
    }
}
