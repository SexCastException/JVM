package com.huazai.itcast.t1.stringtable;

import org.junit.Test;

/**
 * 以下代码分别在1.6和1.8执行会有不同的结果。
 *
 * @author pyh
 * @datetime 2022/11/7 20:27
 * @description
 * @className StringLiteralDemo1
 */
public class StringLiteralDemo1 {
    @Test
    public void test1() {
        String s1 = new String("a") + new String("b");
        // 将堆中s1对象放入串池中，并返回赋值给s2
        String s2 = s1.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == "ab");
    }

    @Test
    public void test2() {
        // 空串对象并不能放入串池中，所以jvm认为并不是相同对象
        String s1 = new String("") + new String("b");
        String s2 = s1.intern();
        System.out.println(s1 == s2);
    }

    @Test
    public void test3() {
        // 动态生成的对象并没有放入串池中
        String s1 = new String("a") + new String("b");
        String s2 = "ab";
        System.out.println(s1 == s2);
        System.out.println(s1 == "ab");
    }

    @Test
    public void test4() {
        String x = "ab";
        String s1 = new String("a") + new String("b");
        // 此时串池中已经存在"ab"，所以调用intern方法并未将堆中的s1对象成功放入串池
        String s2 = s1.intern();
        System.out.println(s2 == s1);
        System.out.println(s2 == x);
        System.out.println(s2 == "ab");
    }

    @Test
    public void test5() {
        String s1 = new String("a") + new String("b");
        String s2 = s1.intern();

        // 此时串池中已经存在"ab"，所以x的值直接从串池中获取
        String x = "ab";

        System.out.println(s2 == s1);
        System.out.println(s2 == x);
        System.out.println(s2 == "ab");
    }
}
