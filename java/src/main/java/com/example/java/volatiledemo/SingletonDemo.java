package com.example.java.volatiledemo;
//volatile在单利模式中的应用
public class SingletonDemo {
    private volatile static SingletonDemo instance=null;
    private SingletonDemo(){
    }
    public static SingletonDemo getInstance(){
        if (instance==null){
            synchronized (SingletonDemo.class){
                if (instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
