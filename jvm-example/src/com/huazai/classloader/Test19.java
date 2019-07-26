package com.huazai.classloader;

public class Test19 {
    // 将Test1的class文件移动到java系统属性sun.boot.class.path的一个目录文件夹下，加载类Test1的时候由启动加载器来加载器
    public static void main(String[] args) throws Exception {
        MyClassLoader16 classLoader = new MyClassLoader16("myClassLoader");
        Class<?> clazz = classLoader.loadClass("com.huazai.loading.Test1");
        System.out.println(clazz.getClassLoader());
    }
}
