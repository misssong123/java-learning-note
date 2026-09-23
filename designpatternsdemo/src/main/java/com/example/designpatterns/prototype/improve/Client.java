package com.example.designpatterns.prototype.improve;

public class Client {
    public static void main(String[] args) {
        Sheep sheep=new Sheep("蒙古羊",10,"黑色");
        sheep.friend=new Sheep("朋友",10,"白色");
        Sheep sheep1=(Sheep) sheep.clone();
        Sheep sheep2=(Sheep) sheep.clone();
        System.out.println(sheep+","+sheep.hashCode()+","+sheep.friend+","+sheep.friend.hashCode());
        System.out.println(sheep1+","+sheep1.hashCode()+","+sheep1.friend+","+sheep1.friend.hashCode());
        System.out.println(sheep2+","+sheep2.hashCode()+","+sheep2.friend+","+sheep2.friend.hashCode());
    }
}
