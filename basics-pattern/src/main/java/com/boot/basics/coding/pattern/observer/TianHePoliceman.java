package com.boot.basics.coding.pattern.observer;

/**
 * @Author cherrishccl
 * @Date 2020/8/28 17:01
 * @Version 1.0
 * @Description
 */
public class TianHePoliceman implements Policeman {
    @Override
    public void action(Citizen ci) {
        String help = ci.getHelp();
        if (help.equals("normal")) {
            System.out.println("一切正常, 不用出动");
        }
        if (help.equals("unnormal")) {
            System.out.println("有犯罪行为, 天河警察出动!");
        }
    }
}
