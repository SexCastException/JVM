package com.huazai.classloader;

/**
 * 数组的类加载
 */
public class Test15 {
    public static void main(String[] args) {
        String[] strings = new String[10];
        System.out.println(strings.getClass());
        // 数组类的类加载器与其元素类型的类加载器相同，此处的null表示String的类加载器为Bootstrap classLoader
        System.out.println(strings.getClass().getClassLoader());

        System.out.println("---------------");


        int[] ints = new int[1];
        System.out.println(ints.getClass());
        // 如果元素类型是原始类型，则数组类没有类加载器，此处的null说明没有类加载器
        System.out.println(ints.getClass().getClassLoader());

    }
}
