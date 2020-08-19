package com.boot.basics.coding.pattern.iterator;

/**
 * @Author cherrishccl
 * @Date 2020/8/19 10:00
 * @Version 1.0
 * @Description
 */
public class IteratorImpl implements Iterator{
    private List list;
    private int index;

    public IteratorImpl(List list) {
        this.list = list;
        this.index = 0;
    }

    @Override
    public Object next() {
        Object obj = list.get(index);
        index++;
        return obj;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void last() {
        index = list.getSize();
    }

    @Override
    public boolean hasNext() {
        return index < list.getSize();
    }
}
