package com.huazai.bytecode.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Subject subject = new RealSubject();
        InvocationHandler dynamicSubject = new DynamicSubject(subject);
        Proxy proxy = (Proxy) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), dynamicSubject);
//        aa.request();
        System.out.println(proxy);
        System.out.println(proxy.getClass().getClassLoader());
    }
}
