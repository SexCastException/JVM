package com.huazai.itcast.t1;

/**
 * 1. jps:查看当前系统中有哪些
 * 2. jmap:查看堆内存占用情况 jmap - heap 进程id
 * 3. jconsole:图形界面的，多功能的监测工具，可以连续监测
 *
 * @author pyh
 * @datetime 2022/11/3 21:41
 * @description
 * @className Demo1
 */
public class HeapDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(30000);
        byte[] array = new byte[1024 * 1024 * 10];
        System.out.println("2...");
        Thread.sleep(20000);
        array = null;
        System.gc();
        System.out.println("3...");
        Thread.sleep(1000000L);
    }
}
