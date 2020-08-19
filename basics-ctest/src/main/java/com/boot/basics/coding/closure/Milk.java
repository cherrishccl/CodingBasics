package com.boot.basics.coding.closure;

/**
 * @Author cherrishccl
 * @Date 2020/8/19 11:33
 * @Version 1.0
 * @Description
 */
public class Milk {
    private static final String name = "纯牛奶";
    private static int num = 16;

    public Milk() {
        System.out.println(name + "：16盒/每箱");
    }

    /**
     * 闭包
     * @return 返回一个喝牛奶的动作
     */
    public Action haveMeal(){
        return new Action() {
            @Override
            public void drink() {
                if(num == 0){
                    System.out.println("木有了，都被你丫喝完了.");
                    return;
                }
                System.out.println(name);
                num--;
                System.out.println("喝掉一瓶牛奶");
            }
        };
    }

    /**
     * 获取剩余数量
     */
    public void currentNum(){
        System.out.println(name+"剩余："+num);
    }

}
