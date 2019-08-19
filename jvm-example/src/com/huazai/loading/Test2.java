package com.huazai.loading;

/**
 * 常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中。
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化
 * <p>
 * 注意：这里指的是将常量存放到了Test2的常量池中，之后Teste2与MyParent2就没有任何关系了，甚至可以将MyParent2的class文件删除
 * <p>
 * 反编译命令 ：javap -c <全类名>
 * 助记符：
 * ldc：表示将int，float或是String类型的常量从常量池推送至栈顶
 * bipush：表示将单字节（-128~127）的常量推送至栈顶
 * sipush：表示将一个短整型常量值（-32768~32767）推送至栈顶
 * iconst_1：表示将int类型1推送至栈顶（iconst_m1—iconst_5)
 */
public class Test2 {
    static {
        System.out.println("test2");
    }

    /*
        通过虚拟机参数TraceClassLoading可以知道，调用类以及子类的静态常量，虚拟机不认为该类将要被使用，所以并没有预先加载。
        既然没有预先加载，即使使用了该类的静态常量，在删除该类的class文件程序运行也不会报错。
     */
    public static void main(String[] args) {
//        System.out.println(MyParent2.str);
//        System.out.println(MyParent2.s);
//        System.out.println(MyParent2.i);

        System.out.println(MyChild2.str);
    }
}

class MyParent2 {
    public static final String str = "parent2 class";
    public static short s = 32;
    public static int i = 1;

    static {
        System.out.println("hello parent2");
    }
}

class MyChild2 extends MyParent2 {
    public static final String str1 = "child2 class";

    static {
        System.out.println("hello child2");
    }
}
