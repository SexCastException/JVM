package com.huazai.loading;

/**
 * 只有当程序访问的静态变量或静态方法确实在当前类或接口中定义时，才可以认为是对类或接口的主动使用。
 * 当一个类在初始化时，要求其父类全部都已初始化完毕了
 * <p>
 * -XX:+TraceClassLoading，开启用户追踪类的加载信息并打印出来
 * 格式：
 * -XX:+<option>：表示开启option选项
 * -XX:-<option>：表示关闭option选项
 * -XX:<option>=<value>：表示将option选项的值设为value
 * <p>
 * JVM规范允许类加载器在预料某个类将要被使用时就预先加载它，如果在预先加载的过程中遇到了.class文件缺失或者错误，类加载器必须在程序
 * 首次主动使用该类时才会报告错误（LinkageError错误）。如果这个类一直没有被程序主动使用，那么类加载器就不会报告错误。
 */
public class Test1 {

    // test类含有main方法，被标志位程序的启动类，属于主动使用，会被初始化
    static {
        System.out.println("test1");
    }

    /*
           通过子类调用父类的静态成员方法，虽然子类不属于主动使用，也没有初始化，通过TraceClassLoading参数可以知道，虚拟机
           预测子类可能被使用，所以就进行了预加载。
        */
    public static void main(String[] args) {
        System.out.println(MyChild1.str);
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
