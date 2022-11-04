package com.huazai.itcast.t1;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * JDK1.8版本之前方法区用的堆的内存，叫永久代(PermGen)，JDK1.8之后用的操作系统的内存，叫元空间(metaspace)。
 * <p>
 * 1.8 以前会导致永久代内存溢出，设置永久代最大容量大小参数：-XX:MaxPermSize=8m
 * 1.8 之后会导致元空间内存溢出，设置元空间最大容量大小参数：-XX:MaxMetaspaceSize=8m
 * tip:1.8之后如果报:Compressed class space(压缩类空间)，此时关闭压缩类空间开关（-XX:-UseCompressedClassPointers），
 * 转而报元空间溢出异常（java.lang.OutOfMemoryError: Metaspace）
 *
 * @author pyh
 * @datetime 2022/11/3 22:23
 * @description
 * @className Demo1
 */
public class MethodAreaDemo extends ClassLoader {
    public static void main(String[] args) {
        int j = 0;
        try {
            MethodAreaDemo test = new MethodAreaDemo();
            for (int i = 0; i < 100000; i++, j++) {
                // ClassWriter 作用是生成类的二进制字节码
                ClassWriter cw = new ClassWriter(0);
                // 方法参数：版本号， 类的权限， 类名, 包名, 父类， 接口
                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, "com.huazai.itcast.t1.methodarea", "java/lang/Object", null);
                byte[] code = cw.toByteArray();
                // 演示不断循环加载类占用方法区，直至内存溢出
                test.defineClass("Class" + i, code, 0, code.length); // Class 对象
            }
        } finally {
            System.out.println(j);
        }
    }
}
