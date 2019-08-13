package com.huazai.bytecode;

/**
 * 方法的静态分配：
 * Grandpa g1 = new Father();
 * 以上代码，g1的静态类型是Grandpa，而g1的实际类型（真正的指向类型）是Father。
 * 结论：变量的静态类型是不会发生变化的，而变量的实际类型是可以发生变化的（多态的体现），实际类型是在运行期方可确定。
 */
public class Test5 {
    // 方法重载是一种静态行为，编译期就可以完全确定。
    public void test(Grandpa grandpa) {
        System.out.println("grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("son");
    }

    public static void main(String[] args) {
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();

        Test5 test5 = new Test5();
        test5.test(g1);
        test5.test(g2);
    }
}

class Grandpa {
}

class Father extends Grandpa {
}

class Son extends Father {
}
