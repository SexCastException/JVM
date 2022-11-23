package com.huazai.itcast.t2.reference;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用demo
 *
 * @author pyh
 * @datetime 2022/11/23 19:56
 * @description
 * @className WeakReferenceDemo
 */
public class WeakReferenceDemo {
    private static final int _4MB = 4 * 1024 * 1024;

    /**
     * 弱引用：在垃圾回收器线程扫描它所管辖的内存区域的过程中，⼀旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
     */
    @Test
    public void weak() {
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WeakReference<byte[]> weakReference = new WeakReference<>(new byte[_4MB]);
            System.out.println(weakReference.get());
            list.add(weakReference);
        }
        System.out.println("======================================手动gc=====================================");
        System.gc();
        for (WeakReference<byte[]> weakReference : list) {
            System.out.println(weakReference.get());
        }
    }

    /**
     * 弱引⽤可以和⼀个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加⼊到与之关联的引用队列中
     */
    @Test
    public void weakAndRQueue() throws InterruptedException {
        List<WeakReference<byte[]>> list = new ArrayList<>();
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
        for (int i = 0; i < 5; i++) {
            WeakReference<byte[]> weakReference = new WeakReference<>(new byte[_4MB], referenceQueue);
            System.out.println(weakReference.get());
            list.add(weakReference);
        }
        System.out.println("======================================手动gc=====================================");
        // 如果手动调用gc之后，不等待一下，被移除的弱引用另外的线程还没来的急放入到引用队列，导致引用队列没有数据，但是遍历弱引用所关联的对象为null，奇葩
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("========================从队列中获取无用的 弱引用对象，并移除=========================");
        Reference<? extends byte[]> reference = referenceQueue.poll();
        while (reference != null) {
            list.remove(reference);
            System.out.println("成功移除第" + (5 - list.size()) + "个");
            reference = referenceQueue.poll();
        }

        System.out.println("========================遍历对象还没被gc关联的软引用对象=============================");
        for (WeakReference<byte[]> weakReference : list) {
            System.out.println(weakReference.get());
        }
    }
}
