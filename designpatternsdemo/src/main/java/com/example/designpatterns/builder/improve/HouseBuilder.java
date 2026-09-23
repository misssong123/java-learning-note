package com.example.designpatterns.builder.improve;

//抽象类，定义盖楼所需要的方法
public abstract  class HouseBuilder {
    House house=new House();
    //将建造的流程写好, 抽象的方法
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();

    //建造房子好， 将产品(房子) 返回
    public House buildHouse() {
        return house;
    }
}
