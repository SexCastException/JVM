package com.huazai.loading;

public class C {
    public C() {
        System.out.println("无参构造函数");
    }
    public C(String a){
        System.out.println("有参构造函数");
    }

    // 静态代码块在类的首次主动使用的时候只运行一次，而非静态代码块在没创建一个实例之前都会调用。
    {
        System.out.println("这是一个非静态代码块");
    }

    public static void test() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        new C();
        new C("hello world");
        C.test();
    }
}
