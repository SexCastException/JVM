package com.huazai.classloader;

public class MyCat {
    public MyCat() {
        System.out.println("MyCat" + MyCat.class.getClassLoader());
    }
}
