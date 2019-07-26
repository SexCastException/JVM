package com.huazai.classloader;

public class Test7 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 类加载器返回null，说明此类是由启动加载器Bootstrap classloader（根加载器），加载的，具体说明查看Class API文档
        System.out.println(Class.forName("java.lang.String").getClassLoader());
        System.out.println(Class.forName("com.huazai.classloader.C").getClassLoader());
    }
}

class C {

}
