package com.huazai.loading;

public class Test6 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println("外部调用count1的值：" + Singleton.count1);
        System.out.println("外部调用count2的值：" + Singleton.count2);
    }
}

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
