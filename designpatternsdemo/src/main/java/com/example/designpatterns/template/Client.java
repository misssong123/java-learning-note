package com.example.designpatterns.template;

public class Client {
    public static void main(String[] args) {
        //花生豆浆
        SoyaMilk demo=new PeanutSoyaMilk();
        demo.make();

        //牛奶豆浆
        demo=new MilkSoyaMilk();
        demo.make();
    }
}
