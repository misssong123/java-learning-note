package com.example.designpatterns.proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
        ITeacherDao dao=new TeacherDAO();
        TeacherDAOProxy proxy=new TeacherDAOProxy(dao);
        proxy.teach();
    }
}
