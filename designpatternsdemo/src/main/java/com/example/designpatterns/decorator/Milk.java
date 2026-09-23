package com.example.designpatterns.decorator;
//具体的调料，即装饰实现类
public class Milk extends  Decorator {
    public Milk(Drink drink) {
        super(drink);
        setDesc("牛奶");
        setPrice(1.0f);
    }
}
