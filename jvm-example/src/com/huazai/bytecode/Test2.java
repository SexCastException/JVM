package com.huazai.bytecode;

import java.io.Serializable;

public class Test2 implements Serializable {
    // 非静态字段所赋的默认值其实也是通过构造函数操作的，在方法的code_info中的助记符可以验证此结论。
    String str = "hello world";

    private int x = 5;

    public static Integer in = 10;

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.setX(88);
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public void test(String str) {
        synchronized (str) {
            System.out.println("hello world");
        }
    }
}
