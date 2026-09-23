package com.example.designpatterns.composite;

//共同的父类，将所有的共有属性和公有方法
public abstract class OrganizationComponent {
    private String desc;//描述
    private String name;//名称
    public OrganizationComponent(String name,String desc){
        super();
        this.name=name;
        this.desc=desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //子类需要的重写的可以重写
    public void add(OrganizationComponent demo){
        throw  new UnsupportedOperationException();
    }
    public void remove(OrganizationComponent demo){
        throw new UnsupportedOperationException();
    }
    //由继承的子类具体实现
    public abstract void printMessage();
}
