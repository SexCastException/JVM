package com.huazai.itcast.t2.reference;

import org.junit.Test;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用demo
 * <p>
 * 设置堆内存大小和gc工作详情相关参数
 * -Xmx20m -XX:+PrintGCDetails -verbose:gc
 *
 * @author pyh
 * @datetime 2022/11/23 18:59
 * @description
 * @className SoftReferenceDemo
 */
public class SoftReferenceDemo {
    private static final int _4MB = 4 * 1024 * 1024;

    /**
     * 强引用：强引用对象还在使用，jvm宁愿抛出oom也不会进行回收
     */
    @Test
    public void strong() throws IOException {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new byte[_4MB]);
        }
        System.in.read();
    }

    /**
     * 软引用：当堆内存不足时，才会回收软引用引用的对象
     */
    @Test
    public void soft() {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[_4MB]);
            System.out.println(softReference.get());
            list.add(softReference);
        }
        System.gc();
        System.out.println("=============================================================================");
        for (SoftReference<byte[]> softReference : list) {
            System.out.println(softReference.get());
        }
    }

    /**
     * 软引用可以和引用队列一起使用，如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加⼊到与之关联的引用队列中。
     */
    @Test
    public void softAndRQueue() {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        // 引用队列
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[_4MB], referenceQueue);
            System.out.println(softReference.get());
            list.add(softReference);
        }
        System.gc();
        System.out.println("========================从队列中获取无用的 软引用对象，并移除=========================");
        // 队列中获取到引用对象所引用的对象已经被gc了
        Reference<? extends byte[]> reference = referenceQueue.poll();
        while (reference != null) {
            list.remove(reference);
            System.out.println("成功移除第" + (5 - list.size()) + "个");
            reference = referenceQueue.poll();
        }

        System.out.println("========================遍历对象还没被gc关联的软引用对象=============================");
        for (SoftReference<byte[]> softReference : list) {
            System.out.println(softReference.get());
        }
    }
}
