package com.huazai.classloader;

import java.lang.reflect.Method;

/**
 * 类加载器的双亲委托模型的好处:
 * 1.可以确保Java核心库的类型安全所有的Java应用都至少会引用java.lang.object类，也就是说在运行期，java.lang.Object这个类
 * 会被加载到Java虚拟机中;如果这个加载过程是由Java应用自己的类加载器所完成的，那么很可能就会在JVM中存在多个版本的
 * java.lang.Object类，而且这些类之间还是不兼容的，相互不可见的(正是命名空间在发挥着作用)。
 * 借助于双亲委托机制，Java核心类库中的类的加载工作都是由启动类加载器来统一完成， 从而确保了Java应用所使用的都是同一个版本的
 * Java核心类库，他们之间是相互兼容的。
 * 2.可以确保Java核心类库所提供的类不会被自定义的类所替代。
 * 3.不同的类加载器可以为相同名称(binary name) 的类创建额外的命名空间。相同名称的类可以并存在Java虚拟机中，只需要用不同的
 * 类加载器来加载他们即可。不同类加载器所加载的类之间是不兼容的，这就相当于在Java虚拟机内部创建了一一个又-个相互隔离的Java
 * 类空间，这类技术在很多框架中都得到了实际应用。
 * <p>
 * <p>
 * 每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类组成
 */
public class Test20 {
    public static void main(String[] args) throws Exception {
        // 将classpath路劲下的MyPerson类文件删除并拷贝一份到桌面上

        // myClassLoader1和myClassLoader2没有直接的父子关系，ClassLoader1和ClassLoader2虽然加载都为同一份class文件，但是
        // 加载后生成的Class对象时不可见的，不兼容的，属于不同的class对象
        MyClassLoader16 myClassLoader1 = new MyClassLoader16("classLoader1");
        MyClassLoader16 myClassLoader2 = new MyClassLoader16("classLoader2");

        myClassLoader1.setPath("C:\\Users\\pyh\\Desktop\\");
        myClassLoader2.setPath("C:\\Users\\pyh\\Desktop\\");

        Class<?> clazz1 = myClassLoader1.loadClass("com.huazai.classloader.MyPerson");
        Class<?> clazz2 = myClassLoader2.loadClass("com.huazai.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object o1 = clazz1.newInstance();
        Object o2 = clazz2.newInstance();
        Method method = clazz1.getMethod("getMyPerson", Object.class);
        method.invoke(o1, o2);
    }
}
