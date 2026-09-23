package com.example.designpatterns.composite;
//学院，叶子结点
public class Department extends OrganizationComponent {
    public Department(String name, String desc) {
        super(name, desc);
    }

    @Override
    public void printMessage() {
        System.out.println(super.getName()+":"+super.getDesc());
    }
}
