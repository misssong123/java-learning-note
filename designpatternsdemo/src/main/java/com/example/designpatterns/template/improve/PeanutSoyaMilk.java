package com.example.designpatterns.template.improve;

public class PeanutSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("向花生豆浆中添加花生");
    }
}
