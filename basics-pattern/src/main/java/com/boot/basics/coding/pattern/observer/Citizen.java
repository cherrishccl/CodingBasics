package com.boot.basics.coding.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2020/8/28 16:53
 * @Version 1.0
 * @Description
 */
public abstract class Citizen {
    List<Policeman> pols;
    String help = "normal";

    public void setHelp(String help) {
        this.help = help;
    }

    public String getHelp() {
        return this.help;
    }

    public abstract void sendMessage(String help);

    public void setPolicemen() {
        this.pols = new ArrayList();
    }

    public void register(Policeman pol) {
        this.pols.add(pol);
    }

    public void unRegister(Policeman pol) {
        this.pols.remove(pol);
    }
}
