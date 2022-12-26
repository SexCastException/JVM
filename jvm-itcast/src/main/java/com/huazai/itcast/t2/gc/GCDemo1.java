package com.huazai.itcast.t2.gc;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author pyh
 * @datetime 2022/11/28 22:19
 * @description
 * @className GCDemo1
 */
public class GCDemo1 {
    private static final int _512KB = 512 * 1024;
    private static final int _1MB = 1024 * 1024;
    private static final int _6MB = 6 * 1024 * 1024;
    private static final int _7MB = 7 * 1024 * 1024;
    private static final int _8MB = 8 * 1024 * 1024;

    /**
     * 虚拟机参数：
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc -XX:-ScavengeBeforeFullGC
     */
    @Test
    public void test01() {

    }

    /**
     * 虚拟机参数：
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc -XX:-ScavengeBeforeFullGC
     */
    @Test
    public void test02() {
//        System.gc();
        ArrayList<byte[]> list = new ArrayList<>();
        list.add(new byte[_7MB]);
        list.add(new byte[_512KB]);
        list.add(new byte[_8MB]);
    }

    // select * from dm_workspace where dw.business_bill_id in (select bb.id from dm_business_bill bb where bb.business_code = '0316-MT-20221012153148');
    // update dm_workspace dw set dw.transfer_state = 0 where dw.business_bill_id in (select bb.id from dm_business_bill bb where bb.business_code = '0316-MT-20221012153148') and rownum = 1;
//    update dm_workspace dw set dw.transfer_state = 0 where dw.id = 'c2001f03f2ff4bb8b8e5606af0cbc60a';
//    commit;

    /**
     * 虚拟机参数：
     * -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc -XX:-ScavengeBeforeFullGC
     * <p>
     * 堆是线程共享的，栈是线程独立的；当一个线程抛出OOM异常后，它所占据的内存资源会全部被释放掉，从而不会影响其他线程的运行
     */
    @Test
    public void test03() throws InterruptedException {
        new Thread(() -> {
            ArrayList<byte[]> list = new ArrayList<>();
            list.add(new byte[_8MB]);
            list.add(new byte[_8MB]);
        }).start();

        System.out.println("sleep....");
        Thread.sleep(1000L);
    }
}
