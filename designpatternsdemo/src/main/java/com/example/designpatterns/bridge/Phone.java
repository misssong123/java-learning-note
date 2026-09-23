package com.example.designpatterns.bridge;
//聚合品牌接口
public class Phone {
    private Brand brand;
    public Phone(Brand brand){
        this.brand=brand;
    }
    protected void open() {
        this.brand.open();
    }
    protected void close() {
        brand.close();
    }
    protected void call() {
        brand.call();
    }
}
