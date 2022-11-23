package com.huazai.itcast.t2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示GCRoot
 * <p>
 * jmap -dump:format=b,live,file=heap.bin  &lt;pid>
 * format:b表示生成快照的时候以二进制方式保存
 * live:表示只要存活的快照对象，此参数会触发一次垃圾回收
 * file:指定生成快照的名称
 *
 * @author pyh
 * @datetime 2022/11/23 15:55
 * @description
 * @className GCRootsDemo1
 */
public class GCRootsDemo1 {
    public static void main(String[] args) throws IOException {
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        System.out.println(1);
        // 此处生成一次快照
        System.in.read();

        list1 = null;
        System.out.println(2);
        // 此处生成一次快照
        System.in.read();
        System.out.println("end...");
    }
}
