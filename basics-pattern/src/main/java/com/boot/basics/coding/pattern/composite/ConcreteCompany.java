package com.boot.basics.coding.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2020/8/5 17:21
 * @Version 1.0
 * @Description
 */
public class ConcreteCompany implements Company {

    private List<Company> companyList = new ArrayList<>();

    private String name;

    public ConcreteCompany(String name){
        this.name = name;
    }
    @Override
    public void add(Company company) {
        companyList.add(company);
    }

    @Override
    public void remove(Company company) {
        companyList.remove(company);
    }

    @Override
    public void display(int depth) {
        StringBuffer stringBuffer = new StringBuffer("-");
        for (int i = 0; i < depth ; i++) {
            stringBuffer.append("-");
        }

        System.out.println(stringBuffer.append(name));

        for (Company company : companyList) {
            company.display(depth+2);
        }
    }

    @Override
    public void duty() {
        for (Company company : companyList) {
            company.duty();
        }
    }
}
