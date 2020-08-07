package com.boot.basics.coding.pattern.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2020/8/7 9:57
 * @Version 1.0
 * @Description
 */
public class Context {
    private String content;
    private List<Expression> list = new ArrayList<Expression>();

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void add(Expression eps) {
        list.add(eps);
    }

    public List<Expression> getList() {
        return list;
    }
}
