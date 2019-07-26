package com.huazai.classloader;

import com.huazai.loading.Test1;

public class Test22 {
    static {
        System.out.println("Test22 init");
    }

    public static void main(String[] args) {
        // 运行期间修改系统属性
        // 需要打包成jar包才会去加载，扩展类加载器才会加载
        System.out.println(Test22.class.getClassLoader());
        System.out.println(Test1.class.getClassLoader());
    }
}
