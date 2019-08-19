package com.huazai.loading;

import java.util.Random;

/**
 * 一个接口在初始化的时候，并不要求父接口都完成了初始化。
 * 只有在真正使用到接口的时候（如引用接口中定义的非编译器确定的常量时），才会初始化。
 */
public class Test5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

/*class */interface MyParent5 {
    public static final int a = new Random().nextInt(10);
    public static Thread thread = new Thread() {
        {
            System.out.println("MyParent5 invoke");
        }
    };
}

/*class */interface MyChild5 extends MyParent5 {
    public static final int b = new Random().nextInt(10);
    public static Thread thread = new Thread() {
        {
            System.out.println("MyChild5 invoke");
        }
    };
}