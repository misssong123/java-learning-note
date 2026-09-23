package com.example.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

//院系，组合学院
public class College extends OrganizationComponent{
    List<OrganizationComponent> list=new ArrayList<>();
    public College(String name, String desc) {
        super(name, desc);
    }

    @Override
    public void add(OrganizationComponent demo) {
        list.add(demo);
    }

    @Override
    public void remove(OrganizationComponent demo) {
        list.remove(demo);
    }

    @Override
    public void printMessage() {
        for (OrganizationComponent demo:list)
            System.out.println(demo.getDesc());
    }
}
