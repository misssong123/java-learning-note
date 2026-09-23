package com.example.designpatterns.proxy.dynamic;

public class Client {
    public static void main(String[] args) {
        ITeacherDao dao=new TeacherDAO();
        ITeacherDao proxy=(ITeacherDao) new ProxyFactory(dao).getInstance();
        //
        proxy.teach();
        //
        proxy.sayHello("zhang");
    }
}
