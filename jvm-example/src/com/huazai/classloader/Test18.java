package com.huazai.classloader;

public class Test18 {
    public static void main(String[] args) {
        // 启动类加载器所加载类所在目录的环境变量
        System.out.println(System.getProperty("sun.boot.class.path"));
        // 扩展类加载器所加载类所在目录的环境变量
        System.out.println(System.getProperty("java.ext.dirs"));
        // 应用类加载器所加载类所在目录的环境变量
        System.out.println(System.getProperty("java.class.path"));
    }
}
