package com.example.designpatterns.decorator;
//具体的调料，即装饰实现类
public class Chocolate extends Decorator {
    public Chocolate(Drink drink) {
        super(drink);
        setDesc("巧克力");
        setPrice(1.0f);
    }
}
