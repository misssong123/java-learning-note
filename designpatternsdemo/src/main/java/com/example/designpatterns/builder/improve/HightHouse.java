package com.example.designpatterns.builder.improve;

public class HightHouse extends  HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("高楼打地基7米");
    }

    @Override
    public void buildWalls() {
        System.out.println("高楼砌墙三十米");
    }

    @Override
    public void roofed() {
        System.out.println("高楼屋顶金色");
    }
}
