package com.huazai.bytecode;

public class Test8 {
    public static void main(String[] args) {
        caculate();
    }

    public static int caculate() {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;

        int result = (a + b - c) * d;
        System.out.println(result);
        return result;
    }
}
