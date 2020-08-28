package com.boot.basics.coding.pattern.observer;

/**
 * @Author cherrishccl
 * @Date 2020/8/28 16:58
 * @Version 1.0
 * @Description
 */
public class TianHeCitizen extends Citizen {
    public TianHeCitizen(Policeman pol) {
        setPolicemen();
        register(pol);
    }

    @Override
    public void sendMessage(String help) {
        setHelp(help);
        for (int i = 0; i < pols.size(); i++) {
            // 通知警察行动
            Policeman pol = pols.get(i);
            pol.action(this);
        }
    }

}
