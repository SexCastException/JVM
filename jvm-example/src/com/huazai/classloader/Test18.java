package com.huazai.classloader;

import sun.misc.Launcher;

/**
 * 在运行期，-个Java类是由该类的完全限定名(binary name,二进制名)和用于加载该类的定义类加载器(defining loader) 所共同决定的。
 * 如果同样名字(即相同的完全限定名)的类是由两个不同的加载器所加载，那么这些类就是不同的，即便.class文件的字节码完全一样， 并且从相同的
 * 位置加载亦如此。
 */


public class Test18 {
    /**
     * 在0racle的Hotspot实现中，系统属性sun.boot.class.path如果修改错了，则运行会出错，提示如下错误信息:
     * Error occurred during
     * initialization of VM
     * java/lang/NoClassDefFoundError: java/lang/0bject
     *
     * @param args
     */
    public static void main(String[] args) {
        // 启动类加载器所加载类所在目录的环境变量
        System.out.println(System.getProperty("sun.boot.class.path"));
        // 扩展类加载器所加载类所在目录的环境变量
        System.out.println(System.getProperty("java.ext.dirs"));
        // 应用类加载器所加载类所在目录的环境变量
        // 控制台运行结果为“.”，原因在于安装jdk的时候设置classpath环境变量
        System.out.println(System.getProperty("java.class.path"));

        /*
        內建于JVM中的启动类加载器会加载java.lang.ClassLoader以及其他的Java平台类，当JVM启动时，一块特殊的机器码会运行，
        它会加载扩展类加载器与系统类加载器，这块特殊的机器码叫做启动类加载器(Bootstrap) 。
        启动类加载器并不是Java类，而其他的加载器则都是Java类，启动类加载器是特定于平台的机器指令，它负责开启整个加载过程。
        所有类加载器(除了启动类加载器)都被实现为Java类，不过，总归要有-个组件来加载第-个Java类加载器，从而让整个加载过程能够顺利进行下去，
        加载第一个纯Java类加载器就是启动类加载器的职责。
        启动类加载器还会负责加载供JRE正常运行所需要的基本组件,这包括java.util与java.lang包中的类等等。
        */

        // 除了启动类加载器，所有ClassLoader以及其子类的加载器都为启动类加载器
        System.out.println(ClassLoader.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(Launcher.class.getClassLoader());

        System.out.println("***********************");

        // 属性java.system.class.loader用于指定应用类加载器，该值默认为null，默认使用sun.misc.Launcher$AppClassLoader加载器为系统默认加载器
        System.out.println(System.getProperty("java.system.class.loader"));

        System.out.println(Test18.class.getClassLoader());
        System.out.println(MyClassLoader16.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());

        // 添加参数-Djava.system.class.loader=com.huazai.classloader.MyClassLoader16运行此类，即将自定义加载器MyClassLoader设置为系统类加载器
        // 类使用默认的系统类加载器加载和必须定义一个公共构造，其被用作代理父ClassLoader类型的单个参数。
        // 否则回报java.lang.Error: java.lang.NoSuchMethodException: com.huazai.classloader.MyClassLoader16.<init>(java.lang.ClassLoader)
        // 以上操作在ClassLoader.getSystemClassLoader()方法文档中有说明

    }
}
