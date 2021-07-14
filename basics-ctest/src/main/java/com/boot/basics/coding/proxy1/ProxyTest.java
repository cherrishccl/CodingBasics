package com.boot.basics.coding.proxy1;

/**
 * @Author cherrishccl
 * @Date 2021/7/7 11:16
 * @Version 1.0
 * @Description Test
 */
public class ProxyTest {
    public static void main(String[] args) {
        UserDao target1 = new UserDao();
        IUserDao staticProxy = new UserDaoProxy(target1);
        staticProxy.save();
        System.out.println("-------------------------------");
        IUserDao target2 = new UserDao();
        IUserDao jdkProxy = (IUserDao) new JdkProxyFactory(target2).getProxyInstance();
        jdkProxy.save();
        System.out.println("-------------------------------");
        UserRepo target3 = new UserRepo();
        UserRepo cglibProxy = (UserRepo) new CglibProxyFactory(target3) .getProxyInstance();
        cglibProxy.save();
    }
}
