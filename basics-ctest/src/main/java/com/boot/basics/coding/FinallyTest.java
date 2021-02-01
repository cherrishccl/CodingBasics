package com.boot.basics.coding;

/**
 * @Author cherrishccl
 * @Date 2021/1/29 16:30
 * @Version 1.0
 * @Description FinallyTest
 */
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println("执行结果: " + test3()); //错误示例 1
        System.out.println("执行结果: " + test4()); //错误示例 3
        System.out.println("执行结果: " + test1()); //错误示例 2
        System.out.println("执行结果: " + test2()); //正确示例 1
    }

    /**
     * finally中的代码"非最后执行"
     */
    private static void test5(){
        try{
            throw new RuntimeException("error!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("finally...");
        }
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
     * @return 2
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
    /**
     * 错误示例：
     * 如果在finally中存在return语句，那么try-catch中的return值都会被覆盖。
     * 所以在try-catch-finally中存在 return返回值的情况，一定要确保return语句只在方法的尾部出现一次。
     * @return 2
     */
    private static int test1(){
        int r = 0;
        try{
            r = r + 1;
            return r; // 此处不返回值
        }catch (Exception e){
        }finally {
            r = 2;
            return  r; // 此处返回2
        }
    }

    /**
     * 正确示例：
     * 确保return语句只在此处出现一次
     * @return 1
     */
    private static int test2(){
        int r = 0;
        try{
            r = r + 1;
        }catch (Exception e){
        }finally {
        }
        return r; //确保return 语句只在此处出现一次
    }
}
