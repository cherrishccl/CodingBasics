package com.boot.basics.coding.pattern;

/**
 * @Author cherrishccl
 * @Date 2020/7/23 15:56
 * @Version 1.0
 * @Description 枚举方式进行实例化，是线程安全的，此种方式也是线程最安全的
 */
public class SigletonExample7 {

    public static SigletonExample7 getInstance() {
        return Sigleton.INSTANCE.getInstace();
    }

    private enum Sigleton{
        INSTANCE;
        private SigletonExample7 sigleton;

        // JVM保证这个方法绝对只调用一次
        Sigleton() {
            this.sigleton = new SigletonExample7();
        }

        public SigletonExample7 getInstace(){
            return sigleton;
        }

    }

    private SigletonExample7(){}
}
