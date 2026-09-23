package com.example.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

//大学，可以组合院系
public class University extends OrganizationComponent {
    List<OrganizationComponent> list=new ArrayList<>();
    public University(String desc, String name) {
        super(name,desc);
    }

    @Override
    public void add(OrganizationComponent demo) {
        list.add(demo);
    }

    @Override
    public void remove(OrganizationComponent demo) {
        list.remove(demo);
    }
    //只会打印其包含的类，不会管包含的类是否包含其他的类，也不会打印信息
    @Override
    public void printMessage() {
        for (OrganizationComponent demo:list)
            System.out.println(demo.getDesc());
    }
}
