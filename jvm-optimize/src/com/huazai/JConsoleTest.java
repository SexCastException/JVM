package com.huazai;

import java.util.ArrayList;
import java.util.List;

public class JConsoleTest {
    public static void main(String[] args) {
        System.out.println("start....");
        List<JConsoleTest> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new JConsoleTest());
        }
        System.out.println("end...");
    }
}
