package com.example.designpatterns.proxy.staticproxy;
//代理对象
public class TeacherDAOProxy implements ITeacherDao {
    private ITeacherDao dao;
    public TeacherDAOProxy (ITeacherDao dao){
        this.dao=dao;
    }
    @Override
    public void teach() {
        System.out.println("方法执行前。。。。");
        dao.teach();
        System.out.println("方法执行后。。。。");
    }
}
