package com.example.designpatterns.decorator;

public class Client {
    public static void main(String[] args) {
        Drink coffee=new LongBlack();
        System.out.println("------单品咖啡------");
        System.out.println(coffee.getPrice());
        System.out.println(coffee.getDesc());
        System.out.println("------单品咖啡+Milk------");
        coffee=new Milk(coffee);
        System.out.println(coffee.cost());
        System.out.println(coffee.getDesc());
        System.out.println("------单品咖啡+Milk+Milk------");
        coffee=new Milk(coffee);
        System.out.println(coffee.cost());
        System.out.println(coffee.getDesc());
        System.out.println("------单品咖啡+Milk+Milk+Chocolate------");
        coffee=new Chocolate(coffee);
        System.out.println(coffee.cost());
        System.out.println(coffee.getDesc());
    }
}
