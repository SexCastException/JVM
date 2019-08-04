package com.huazai.loading;

/**
 * 对于数组实例来说，其类型是由JVM在运行期动态生成的，格式为[L+类名，动态生成的类型其父类就是Object。
 *
 * 对于数组来说，JavaDoc经常将构成的数组元素称为Component，实际上就是将数组降低一个维度后的表现。
 * 二维数组的Component为一维数组，一维数组的Component为数组里面的每个类型
 *
 * 助记符：
 * anewarray：表示创建一个引用类型的（如类、接口、数组）数组，并将其压入栈顶。
 * newarray：表示创建一个指定的原始类型（如int、char、float等）的数组，并将其压入栈顶。
 */
public class Test4 {
    public static void main(String[] args) {
//        System.out.println(new MyParent4());
        MyParent4[] myParent4s = new MyParent4[12];
        System.out.println(MyParent4.class);
        System.out.println(myParent4s.getClass());
        System.out.println(myParent4s.getClass().getSuperclass());
        System.out.println("------------");
        MyParent4[][] myParent4s1 = new MyParent4[5][5];
        System.out.println(myParent4s1.getClass());
        System.out.println(myParent4s1.getClass().getSuperclass());
        System.out.println("------------");
        char[] chars = new char[1];
        System.out.println(chars.getClass());
        System.out.println(chars.getClass().getSuperclass());
        System.out.println("-------------");
        Character[] characters = new Character[1];
        System.out.println(characters.getClass());
        System.out.println(characters.getClass().getSuperclass());
    }
}

class MyParent4 {
    static {
        System.out.println("MyParent4 class block");
    }
}