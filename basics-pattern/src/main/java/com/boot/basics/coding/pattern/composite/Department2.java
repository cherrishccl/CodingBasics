package com.boot.basics.coding.pattern.composite;

/**
 * @Author cherrishccl
 * @Date 2020/8/5 17:22
 * @Version 1.0
 * @Description
 */
public class Department2 implements Company {

    private String name;
    public Department2(String name){
        this.name = name;
    }
    @Override
    public void add(Company company) {

    }

    @Override
    public void remove(Company company) {

    }

    @Override
    public void display(int depth) {
        StringBuffer stringBuffer = new StringBuffer("-");
        for (int i = 0; i < depth ; i++) {
            stringBuffer.append("-");
        }

        System.out.println(stringBuffer.append(name));
    }

    @Override
    public void duty() {
        System.out.println(name + "负责销售");
    }
}
