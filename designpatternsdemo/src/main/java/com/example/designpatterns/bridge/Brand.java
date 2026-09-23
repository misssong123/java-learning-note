package com.example.designpatterns.bridge;

//将品牌抽出抽象类，聚合到phone中
public interface Brand {
    void open();
    void close();
    void call();
}
