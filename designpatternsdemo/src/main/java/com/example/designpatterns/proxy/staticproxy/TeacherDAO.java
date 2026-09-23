package com.example.designpatterns.proxy.staticproxy;
//目标对象
public class TeacherDAO implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("授课。。。。。。。");
    }
}
