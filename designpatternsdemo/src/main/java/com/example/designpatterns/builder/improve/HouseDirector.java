package com.example.designpatterns.builder.improve;
//具体的房子构造指挥者，指定盖房子的步骤
public class HouseDirector {
    private HouseBuilder houseBuilder=null;
    public  HouseDirector(){

    }
    public  HouseDirector(HouseBuilder houseBuilder){
        this.houseBuilder=houseBuilder;
    }
    public void setHouseBuilder(HouseBuilder houseBuilder){
        this.houseBuilder=houseBuilder;
    }
    public House createHouse(){
        //可以设置建造楼房的步骤
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}
