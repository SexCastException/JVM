package com.huazai.classloader;

/**
 * 关于类加载器命名空间：
 * 1、子类所加载的类能够访问父加载器所加载的类
 * 2、父加载器所加载的类无法访问到子加载器所加载的类
 */
public class MySample {
    public MySample() {
        System.out.println("MySample" + MySample.class.getClassLoader());
        new MyCat();
    }
}

/**
 * 疑问：为什么把MyCat和MySample定义在同一个文件会报错呢
 */

class Test17 {
    public static void main(String[] args) throws Exception {
        MyClassLoader16 classLoader = new MyClassLoader16("myClassLoader");
        classLoader.setPath("C:\\Users\\pyh\\Desktop\\");
        Class<?> clazz = classLoader.loadClass("com.huazai.classloader.MySample");
        System.out.println(clazz.hashCode());
        System.out.println(clazz.getClassLoader());
        System.out.println("-------------");
        Object o = clazz.newInstance();
    }
}
