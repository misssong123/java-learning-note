package com.example.java.threadlocaldemo;

public class ThreadLocalDemo {
    public static ThreadLocal<UserInfo> context = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(()->{
            context.set(new UserInfo());
        }
        ).start();
        new Thread(()->{
            System.out.println(context.get());
        }
        ).start();
    }
}
class UserInfo{
    //
}