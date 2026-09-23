package com.example.designpatterns.bridge;

public class HUAWEI implements Brand {
    @Override
    public void open() {
        System.out.println("HUAWEI手机开机");
    }

    @Override
    public void close() {
        System.out.println("HUAWEI手机关机");
    }

    @Override
    public void call() {
        System.out.println("HUAWEI手机打电话");
    }
}
