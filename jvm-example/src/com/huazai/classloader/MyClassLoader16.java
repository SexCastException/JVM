package com.huazai.classloader;

import java.io.*;

public class MyClassLoader16 extends ClassLoader {
    /**
     * 加载器的名称
     */
    private String classLoaderName;

    private String path;

    /**
     * 加载二进制文件的后缀名
     */
    private final String fileExtension = ".class";

    /**
     * 将自定义类加载器时，此构造函数是必须的
     *
     * @param parent
     */
    /*public MyClassLoader16(ClassLoader parent) {
        super(parent);
    }*/

    /**
     * 将系统（应用）加载器作为该加载器的父加载器
     *
     * @param classLoaderName
     */
    public MyClassLoader16(String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader16(ClassLoader parent, String classLoaderName) {
        // 显示指定该加载器的父加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    /**
     * loadCLass->findClass->defineClass
     * 查找指定的名字对应的类,此方法将会被loadClass方法所调用，在检查完父加载器之后
     * 由于双亲委托机制，如果需要加载的类在classpath路径下，则会调用父类引用加载器，自定义加载器不执行
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("调用MyClassLoader加载器");
        byte[] bytes = loadClassData(name);
        return this.defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 将指定位置的文件读取成二进制数组并返回
     *
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        InputStream inputStream = null;
        byte[] data = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            name = name.replace(".", "/");
            inputStream = new FileInputStream(new File(this.path + name + fileExtension));
            int ch = 0;
            while (-1 != (ch = inputStream.read())) {
                outputStream.write(ch);
            }
            data = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return data;
    }

    /**
     * 当
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        String path = "C:\\Users\\pyh\\Desktop\\";

        MyClassLoader16 testLoader = new MyClassLoader16("testLoader");
        testLoader.setPath(path);
        // 把类的二进制数据加载进内存并在数据区的方法去生成的相应的class对象并返回
        Class<?> clazz = testLoader.loadClass("com.huazai.loading.Test1");
        System.out.println(clazz.hashCode());
        Object o = clazz.newInstance();
        System.out.println(o.getClass().getClassLoader());

        System.out.println("-----------");

        /*
//        MyClassLoader16 testLoader1 = new MyClassLoader16(null, "testLoader1");
        MyClassLoader16 testLoader1 = new MyClassLoader16(testLoader, "testLoader1");
        testLoader1.setPath(path);
        Class<?> clazz1 = testLoader1.loadClass("com.huazai.loading.Test1");
        System.out.println(clazz1.hashCode());
        Object o1 = clazz1.newInstance();
        System.out.println(o1.getClass().getClassLoader());

        // 同一个类只能被加载一次，如果已经被父类加载器加载了，由于一个类只能存在一个class对象（包括命名空间相同的才算同一个类），所以直接把已经加载的class对象返回，子类不会再去加载
        System.out.println(clazz == clazz1);

        MyClassLoader16 testLoader2 = new MyClassLoader16(null,"testLoader2");
        testLoader2.setPath(path);
        Class<?> clazz2 = testLoader2.loadClass("com.huazai.loading.Test1");
        System.out.println(clazz2.hashCode());
        Object o2 = clazz2.newInstance();
        System.out.println(o2.getClass().getClassLoader());
        */

        // 类的卸载,查看类卸载的两种方式，程序启动时添加参数：-XX:+TraceClassUnloading，或者cmd输入命令：jvisualvm调用工具查看情况

        testLoader = null;
        clazz = null;
        o = null;
        System.gc();

        Thread.sleep(20000);

        testLoader = new MyClassLoader16("testLoader3");
        testLoader.setPath(path);
        // 把类的二进制数据加载进内存并在数据区的方法去生成的相应的class对象并返回
        clazz = testLoader.loadClass("com.huazai.loading.Test1");
        System.out.println(clazz.hashCode());
        o = clazz.newInstance();
        System.out.println(o.getClass().getClassLoader());

    }

    @Override
    public String toString() {
        return "MyClassLoader16{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }

    public void setClassLoaderName(String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
