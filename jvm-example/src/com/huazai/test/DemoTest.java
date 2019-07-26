package com.huazai.test;

import java.util.ArrayList;
import java.util.List;

public class DemoTest {
    public DemoTest() {
    }

    public static void main(String[] args) {
        List<DemoTest> list = new ArrayList<DemoTest>();
        while (true) {
            list.add(new DemoTest());
        }
    }
}
