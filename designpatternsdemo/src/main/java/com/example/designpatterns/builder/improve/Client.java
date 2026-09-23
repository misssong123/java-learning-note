package com.example.designpatterns.builder.improve;

public class Client {
    public static void main(String[] args) {

        //盖普通房子
        CommonHouse commonHouse = new CommonHouse();
        //准备创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);

        //完成盖房子，返回产品(普通房子)
        House house = houseDirector.createHouse();

        //System.out.println("输出流程");

        System.out.println("--------------------------");
        //盖高楼
        HightHouse highBuilding = new  HightHouse();
        //重置建造者
        houseDirector.setHouseBuilder(highBuilding);
        //完成盖房子，返回产品(高楼)
        houseDirector.createHouse();



    }
}
