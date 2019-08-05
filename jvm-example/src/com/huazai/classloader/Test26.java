package com.huazai.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * ServiceLoader的使用参考该类文档
 */
public class Test26 {
    public static void main(String[] args) {
//        Thread.currentThread().setContextClassLoader(MyClassLoader16.class.getClassLoader().getParent());

        // 重点查看方法以及相关的一系列相关源码
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Driver next = iterator.next();
            System.out.println("class:" + next.getClass() + ",classLoader:" + next.getClass().getClassLoader());
        }
        System.out.println("--------------------");
        System.out.println("当前上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器：" + ServiceLoader.class.getClassLoader());
    }
}
