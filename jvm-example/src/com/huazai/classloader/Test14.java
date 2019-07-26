package com.huazai.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class Test14 {
    public static void main(String[] args) throws IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        System.out.println(contextClassLoader == systemClassLoader);

        String resource = "com/huazai/classloader/Test14.class";
        Enumeration<URL> resources = contextClassLoader.getResources(resource);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println(url);
        }

        Class<Test14> test14Class = Test14.class;
        ClassLoader classLoader = test14Class.getClassLoader();
        System.out.println(classLoader);

    }
}
