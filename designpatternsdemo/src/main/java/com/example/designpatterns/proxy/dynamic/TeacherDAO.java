package com.example.designpatterns.proxy.dynamic;
//目标对象
public class TeacherDAO implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("授课。。。。。");
    }

    @Override
    public void sayHello(String name) {
        System.out.println("name:"+name);
    }
}
