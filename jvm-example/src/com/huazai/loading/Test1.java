package com.huazai.loading;

/**
 * 对于静态变量来说，主动使用的时候，只有直接定义了该字段的类才会被初始化（即使子类主动使用子类也不会被初始化）。
 * 当一个类在初始化时，要求其父类全部都已初始化完毕了
 *
 * -XX:+TraceClassLoading，开启用户追踪类的加载信息并打印出来
 * 格式：
 * -XX:+<option>：表示开启option选项
 * -XX:-<option>：表示关闭option选项
 * -XX:<option>=<value>：表示将option选项的值设为value
 */
public class Test1 {

    // test类含有main方法，被标志位程序的启动类，属于主动使用，会被初始化
    static {
        System.out.println("test1");
    }

    public static void main(String[] args) {
        System.out.println(MyParent1.str);
    }

}

class MyParent1 {
    public static String str = "parent1 class";

    static {
        System.out.println("MyParent1 class block");
    }
}

class MyChild1 extends MyParent1 {
    public static String str1 = "child1 class";

    static {
        System.out.println("MyChild1 block class");
    }
}
