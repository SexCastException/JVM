package com.huazai.bytecode.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {
    // 真实动态代理对象
    private Object subject;

    public DynamicSubject(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理前置通知");

        method.invoke(this.subject, args);

        System.out.println("动态代理后置通知");
        return null;
    }
}
