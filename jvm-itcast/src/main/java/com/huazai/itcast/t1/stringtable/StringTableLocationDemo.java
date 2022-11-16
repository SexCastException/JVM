package com.huazai.itcast.t1.stringtable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * StringTable 位置演示：
 * 1.8之前StringTable是常量池中的一部分，并在常量池在永久代内存中，永久代的内存只有FullGc才能进行垃圾回收，所以StringTable垃圾回收效率低；
 * 1.8之后StringTable移动到了堆内存中，MinorGc可以对其进行垃圾回收哦，提高了StringTable的回收效率。
 *
 * 设置永久代最大容量大小参数：-XX:MaxPermSize=8m
 * 设置堆内存大小参数：-Xms10m
 *
 * @author pyh
 * @datetime 2022/11/8 21:44
 * @description
 * @className StringTableLocationDemo
 */
public class StringTableLocationDemo {
    @Test
    public void test() {
        List<String> list = new ArrayList<String>();
        int i = 0;
        try {
            for (int j = 0; j < 260000000; j++) {
                list.add(String.valueOf(j).intern());
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }
}
