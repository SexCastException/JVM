package com.huazai.itcast.t1;

/**
 * @author pyh
 * @datetime 2022/11/4 23:01
 * @description
 * @className ConstantPoolDemo1
 */
public class ConstantPoolDemo1 {
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        // s1和s2是变量，在代码运行期间，值可能发生变化，例如根据用户的输入，来改变s1和s2的值。所以只有从局部变量表中读取它们的值，并用StringBuilder添加。
        String s4 = s1 + s2;    // 等价于：new StringBuilder("a").append("b").toString();  =>  new String("ab");
        // 字面量字符串"ab"在常量池中已经存在，所以"a" + "b"在常量池中存在，所以直接从常量池中获取复制给s5变量，所以s5就是指向s3
        String s5 = "a" + "b";
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
    }
}
