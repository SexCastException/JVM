package com.huazai.bytecode;

/*
    方法的动态分配：
    方法的动态分配涉及到一个重要概念：方法接收者

    invokevirtual字节码指令的多态查找流程

    方法重载（overload）和方法重写（overwrite）比较，可以得出以下结论：
    方法重载是静态的，是编译期行为；方法重载是动态的，是运行期行为。

 */

/**
 * 动态分派查找机制：从操作数栈最顶端找到引用的实际类型，根据实际类型在字节码中invokevirtual所对应的参数指定的特定方法，
 * 在该实际类型中查找是否与该特定方法相同的方法（方法名和参数列表相同），如果找不到则根据继承的层级从下往上查找，找到则返回，找不到报错。
 *
 */
public class Test6 {
    public static void main(String[] args) {
        Fruit apple = new Apple();
        Fruit oranger = new Orange();

        apple.test();
        oranger.test();

        apple = new Orange();
        apple.test();
    }
}

class Fruit {
    public void test() {
        System.out.println("Fruit");
    }
}

class Apple extends Fruit {
    @Override
    public void test() {
        System.out.println("Apple");
    }
}

class Orange extends Fruit {
    @Override
    public void test() {
        System.out.println("Oranger");
    }
}
