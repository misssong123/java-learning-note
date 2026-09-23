package com.example.designpatterns.template.improve;

public class Client {
    public static void main(String[] args) {
        //花生豆浆
        SoyaMilk demo=new PeanutSoyaMilk();
        demo.make();
        System.out.println("-------------------------------------------------------");
        //牛奶豆浆
        demo=new MilkSoyaMilk();
        demo.make();
        System.out.println("-------------------------------------------------------");
        //纯豆浆
        demo=new PureSoyaMilk();
        demo.make();
    }
}
