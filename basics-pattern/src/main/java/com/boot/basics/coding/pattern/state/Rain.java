package com.boot.basics.coding.pattern.state;

/**
 * @Author cherrishccl
 * @Date 2020/9/1 17:40
 * @Version 1.0
 * @Description
 */
public class Rain implements Weather {
    @Override
    public String getWether() {
        return "rainny";
    }
}
