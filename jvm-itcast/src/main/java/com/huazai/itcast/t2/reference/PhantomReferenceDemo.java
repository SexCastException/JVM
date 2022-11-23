package com.huazai.itcast.t2.reference;

import org.junit.Test;
import sun.misc.Cleaner;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pyh
 * @datetime 2022/11/23 21:19
 * @description
 * @className PhantomReferenceDemo
 */
public class PhantomReferenceDemo {

    private static final int _4MB = 4 * 1024 * 1024;

    /**
     * 虚引用：get方法获取被引用的对象永远为null，因为该引用的get方法为空实现
     */
    @Test
    public void phantomAndRQueue() {
        List<PhantomReference<byte[]>> list = new ArrayList<>();
        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
        for (int i = 0; i < 5; i++) {
            PhantomReference<byte[]> phantomReference = new PhantomReference<>(new byte[_4MB], referenceQueue);
            System.out.println(phantomReference.get());
            list.add(phantomReference);
        }

        System.out.println("=============================================================================");
        for (PhantomReference<byte[]> phantomReference : list) {
            System.out.println(phantomReference.get());
        }
    }

    /**
     * 当虚引用引用的对象被gc之后，会触发Cleaner对象的clean方法，clean方法会调用Runnable的run方法。
     * 而clean方法的由引用超类Reference的ReferenceHandler线程来触发。
     * <p>
     * 直接内存正是利用的了这个原理在clean方法中释放
     */
    @Test
    public void clean() {
        DemoObject demoObject = new DemoObject();
        Cleaner.create(demoObject, () -> {
            System.out.println("释放资源");
        });
        demoObject = null;
        System.gc();
        System.out.println("finish");
    }

    private static class DemoObject {

    }
}
