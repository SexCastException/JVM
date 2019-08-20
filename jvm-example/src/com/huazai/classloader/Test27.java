package com.huazai.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DriverManager方法，aClass =  Class.forName(driver.getClass().getName(), true, classLoader);主要校验driver和aClass
 * 是不是同一个类加载器加载的，涉及到类加载器的命名空间的问题
 * <p>
 * <p>
 * <p>
 * private static boolean isDriverAllowed(Driver driver, ClassLoader classLoader) {
 * boolean result = false;
 * if(driver != null) {
 * Class<?> aClass = null;
 * try {
 * aClass =  Class.forName(driver.getClass().getName(), true, classLoader);
 * } catch (Exception ex) {
 * result = false;
 * }
 * <p>
 * result = ( aClass == driver.getClass() ) ? true : false;
 * }
 * <p>
 * return result;
 * }
 */

public class Test27 {
    /*
        深入数据库驱动加载源码了解SPI机制
     */
    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        // 模拟数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/beens", "root", "123456");
    }
}
