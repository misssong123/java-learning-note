package com.example.java.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

//CAS样例的展示
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger demo=new AtomicInteger(5);
        System.out.println(demo.compareAndSet(5,2019)+","+demo.get());
        System.out.println(demo.compareAndSet(5,2020)+","+demo.get());
        /**
         * 结果
         * true,2019
         * false,2019
         */
    }
}
