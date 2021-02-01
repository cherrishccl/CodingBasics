package com.boot.basics.coding.proxy;

/**
 * @Author chencl
 * @Date 2021/1/29 16:30
 * @Version 1.0
 * @Description FinallyTest
 */
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println("执行结果: " + test3()); //错误示例 1
        System.out.println("执行结果: " + test4()); //正确示例 3
    }

    /**
     * finally代码块中的代码"不执行"
     * 错误示例
     * @return 1
     */
    private static int test3(){
        int r = 0;
        try{
            r = r + 1;
            return r;
        }finally {
            r = r + 2;
        }
    }
    /**
     * finally代码块中的代码"不执行"
     * 正确示例
     * @return 3
     */
    private static int test4(){
        int r = 0;
        try{
            r = r + 1;
        }finally {
            r = r + 2;
        }
        return r;
    }
}
