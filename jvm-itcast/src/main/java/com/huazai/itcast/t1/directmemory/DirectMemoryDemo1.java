package com.huazai.itcast.t1.directmemory;

import org.junit.Test;
import sun.misc.Unsafe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pyh
 * @datetime 2022/11/16 22:31
 * @description
 * @className DirectMemoryDemo1
 */
public class DirectMemoryDemo1 {
    final String FROM = "/Users/pyh/Downloads/Adobe_Illustrator_2021_25.0.1.66_M1_SP_20210111.dmg";
    final String TO = "/Users/pyh/Desktop/a.dmg";
    final String TO1 = "/Users/pyh/Desktop/a1.dmg";
    final int _1Mb = 1024 * 1024;

    /**
     * 使用传统IO来拷贝文件
     * <p>
     * 磁盘文件先由操作系统将文件读取到系统内存缓冲区，java想要读取系统内存缓冲区的数据，先在堆内存分配一块java缓冲区，再将系统内存缓冲区中的
     * 数据读取到java缓冲区来达到文件拷贝的目的。
     * java堆内存受gc管理。
     */
    @Test
    public void io() {
        long start = System.nanoTime();
        try (FileInputStream from = new FileInputStream(FROM); FileOutputStream to = new FileOutputStream(TO1);) {
            byte[] buf = new byte[_1Mb];
            while (true) {
                int len = from.read(buf);
                if (len == -1) {
                    break;
                }
                to.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("io 用时：" + (end - start) / 1000_000.0);
    }

    /**
     * 使用直接内存来拷贝文件。
     * <p>
     * 直接内存系统和java代码都可以直接访问，拷贝文件的时候，java代码直接把磁盘文件读取到直接内存中然后再从直接内存中使用数据，
     * 比传统的IO少拷贝一次数据，所以性能比传统的IO更好。
     * <p>
     * 直接内存不受gc管理，所以需要显示调用回收直接内存。
     */
    @Test
    public void directMemory() {
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FROM).getChannel(); FileChannel to = new FileOutputStream(TO).getChannel();) {
            ByteBuffer bb = ByteBuffer.allocateDirect(_1Mb);
            while (true) {
                int len = from.read(bb);
                if (len == -1) {
                    break;
                }
                bb.flip();
                to.write(bb);
                bb.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("directBuffer 用时：" + (end - start) / 1000_000.0);
    }

    /**
     * 直接内存OOM
     */
    @Test
    public void directMemoryOOM() {
        List<ByteBuffer> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                // 每次分配100M直接内存
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);
                list.add(byteBuffer);
                i++;
            }
        } finally {
            System.out.println(i);
        }
    }

    /**
     * java直接内存底层原理。
     * 使用了 Unsafe 对象完成直接内存的分配回收，并且回收需要主动调用 freeMemory 方法，ByteBuffer 的实现类内部，
     * 使用了 Cleaner （虚引用）来监测 ByteBuffer 对象，一旦ByteBuffer 对象被垃圾回收，那么就会由 ReferenceHandler 线程
     * 通过 Cleaner 的 clean 方法调用 freeMemory 来释放直接内存
     * <p>
     * 总结：直接内存是通过gc来间接释放的。
     *
     * @throws Exception
     */
    @Test
    public void allocateDirectBuffer() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // 申请直接内存，返回直接内存的引用地址
        long address = unsafe.allocateMemory(1024 * 1024 * 1024);
        unsafe.setMemory(address, 1024 * 1024 * 1024, (byte) 0);
        // 通过地址来释放直接内存
        unsafe.freeMemory(address);
    }

    /**
     * 禁用显示的gc调用参数：-XX:+DisableExplicitGC
     * 禁用显示调用gc功能之后，System.gc();代码将失效，则虚引用需要等待jvm自动回收回收后才能触发直接内存的会后
     *
     * @throws IOException
     */
    @Test
    public void disableExplicitGC() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
        System.out.println("分配完毕...");
//        System.in.read();
        System.out.println("开始释放...");
        byteBuffer = null;
        // 显式调用垃圾回收，禁用之后该代码无效
        System.gc();
//        System.in.read();
        System.out.println("finish...");
    }
}
