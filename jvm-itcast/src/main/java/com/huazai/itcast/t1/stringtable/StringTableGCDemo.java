package com.huazai.itcast.t1.stringtable;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * GC对串池进行垃圾回收演示
 * <p>
 * -Xmx10m:设置堆大小
 * -XX:+PrintStringTableStatistics:打印串池统计信息
 * -XX:+PrintGCDetails -verbose:gc:打印GC详细信息
 *
 * @author pyh
 * @datetime 2022/11/9 21:43
 * @description
 * @className StringTableGCDemo
 */
public class StringTableGCDemo {

    /**
     * StringTable底层利用hash表来存储数据（数组 + 红黑树）。
     * Number of buckets：桶的数量（数组）
     * Number of entries：键值对的数量
     * Number of literals：字面量的数量
     * <p>
     * -XX:StringTableSize=100：设置串池的大小，不加单位则直接设置桶的个数，加单位（k,m,g）则根据单位设置桶相应的数值大小。
     */
    @Test
    public void test() {
        /*
        StringTable statistics:
        Number of buckets       :     60013 =    480104 bytes, avg   8.000
        Number of entries       :      1968 =     47232 bytes, avg  24.000
        Number of literals      :      1968 =    129688 bytes, avg  65.898
        Total footprint         :           =    657024 bytes
         */
    }

    /**
     * 合理设置StringTable大小可提升程序的性能
     */
    @Test
    public void test1() {
        int i = 0;
        try {
            for (int j = 0; j < 100000; j++) {
                String.valueOf(j).intern();
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }

    /**
     *
     */
    @Test
    public void test2() throws IOException {
        long start = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/linux.words"), "utf-8"))) {
            String line = null;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
//                line.intern();
            }
        } finally {
            System.out.println("cost:" + (System.nanoTime() - start) / 1000000);
        }
    }

}
