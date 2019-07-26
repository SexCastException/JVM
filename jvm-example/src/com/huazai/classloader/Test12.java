package com.huazai.classloader;

/**
 * systemClassLoader.loadClass("com.huazai.classloader.CL");和Class.forName("com.huazai.classloader.CL");
 * 上面这两种方式都能获取指定类的类加载器，但是前者不属于对类的主动使用，不初始化，后者表示对类主动使用，对类初始化
 */
public class Test12 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> loadClass = systemClassLoader.loadClass("com.huazai.classloader.CL");
        System.out.println(loadClass);

        System.out.println("------------");

        Class<?> aClass = Class.forName("com.huazai.classloader.CL");
        System.out.println(aClass);
    }
}

class CL {
    static {
        System.out.println("CL class block");
    }
}
