package com.example.designpatterns.proxy.cglib;

public class Client {
    public static void main(String[] args) {
        TeacherDAO dao=new TeacherDAO();
        TeacherDAO proxy=(TeacherDAO)new ProxyFactory(dao).getInstance();
        proxy.teach();
    }
}
