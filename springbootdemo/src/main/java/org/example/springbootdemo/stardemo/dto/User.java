package org.example.springbootdemo.stardemo.dto;

public class User {
    private int age;
    private String name;
    public User(){

    }
    public User(String name, int age){
        this.age = age;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
