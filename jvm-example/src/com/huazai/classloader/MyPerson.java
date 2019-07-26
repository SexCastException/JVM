package com.huazai.classloader;

public class MyPerson {
    public MyPerson getMyPerson(Object person) {
        System.out.println("get person");
        return (MyPerson) person;
    }

    public String getMyPerson(String person) {
        System.out.println(person);
        return person;
    }
}