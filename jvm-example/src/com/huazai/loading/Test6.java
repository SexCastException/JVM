package com.huazai.loading;

/*
    重点难点
 */
public class Test6 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println("外部调用count1的值：" + Singleton.count1);
        System.out.println("外部调用count2的值：" + Singleton.count2);
    }
}

/**
 * 对于类的静态变量，先按顺序从上至下在连接阶段赋默认值，然后在初始化阶段从上至下赋初始值。
 */
class Singleton {
    public static int count1 /*= 1*/;

    // 此处代码移到构造函数下面
//    public static int count2 = 0;

    private static Singleton singleton = new Singleton();

    private Singleton() {
        count1++;
        count2++;
        System.out.println("构造器count1的值：" + count1);
        System.out.println("构造器count2的值：" + count2);
    }

    public static int count2 = 0;

    public static Singleton getInstance() {
        return singleton;
    }
}
