package com.huazai.itcast.t1.stringtable;

/**
 * 字符串字面量延迟加载。
 *
 * StringTable特性：
 * 1、常量池中的字符串仅是符号，第一次用到时才变为对象。
 * 2、利用串池的机制，来避免重复创建字符串对象。
 * 3、1.8之后字符串变量拼接的原理是StringBuilder。
 * 4、字符串常量拼接的原理是编译期优化。
 * 5、可以手动调用intern方法，主动将堆中字符串对象放入串池。
 * 6、1.8之后将这个字符串对象尝试放入串池，如果有则并不会放入，如果没有则放入串池，会把串池中的对象返回。
 * 7、1.6之前将这个字符串对象尝试放入串池，如果有则并不会放入，如果没有会把此对象复制一份，放入串池，会把串池中的对象返回。
 *
 * @author pyh
 * @datetime 2022/11/7 19:58
 * @description
 * @className StringLiteralDemo
 */
public class StringLiteralDemo {
    public static void main(String[] args) {
        int x = args.length;
        // 观察Memory面板字符串个数（count:1053）
        System.out.println();

        // "1".intern();
        System.out.print("1");
        System.out.print("2");
        System.out.print("3");
        System.out.print("4");
        System.out.print("5");
        System.out.print("6");
        System.out.print("7");
        System.out.print("8");
        System.out.print("9");
        System.out.print("0");

        System.out.print("1"); // 字符串个数 （count:1063）
        System.out.print("2");
        System.out.print("3");
        System.out.print("4");
        System.out.print("5");
        System.out.print("6");
        System.out.print("7");
        System.out.print("8");
        System.out.print("9");
        System.out.print("0");
        System.out.print(x); // 字符串个数 （count:1063）
    }
}
