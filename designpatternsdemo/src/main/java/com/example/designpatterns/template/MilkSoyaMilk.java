package com.example.designpatterns.template;

public class MilkSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("向牛奶豆浆中添加牛奶");
    }
}
