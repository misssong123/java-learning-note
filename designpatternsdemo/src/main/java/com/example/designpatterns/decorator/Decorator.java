package com.example.designpatterns.decorator;
//装饰父类
public class Decorator extends Drink {
    private  Drink drink;//聚合具体的Coffee
    public Decorator(Drink drink){
        this.drink=drink;
    }
    @Override
    public float cost() {
        //返回自己的价格和具体drink的价格
        return (getPrice()+drink.cost());
    }

    @Override
    public String getDesc() {
        return desc+","+getPrice()+","+drink.getDesc();
    }
}
