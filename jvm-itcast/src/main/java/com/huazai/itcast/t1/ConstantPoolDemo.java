package com.huazai.itcast.t1;

/**
 * javap -v <classes>：查看类详细信息
 * <p>
 * 常量池，就是一张表，虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量等信息。
 * 运行时常量池，常量池是 *.class 文件中的，当该类被加载，它的常量池信息就会放入运行时常量池，并把里面的符号地址变为真实地址。
 *
 * @author pyh
 * @datetime 2022/11/4 22:40
 * @description
 * @className ConstantPoolDemo
 */
public class ConstantPoolDemo {
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
