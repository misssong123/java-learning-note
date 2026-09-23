package com.example.java.volatiledemo;

/**
 * 该类用于展示指令重排
 */
public class VolatileOrderDemo {
    int a = 0;
    boolean flag = false;
    public void method1(){
        a=1;//语句1
        flag=true;//语句2
    }
    public void method2(){
        if (flag){
            a=a+5;//语句3
            System.out.println("****:"+a);
        }
    }
    public static void main(String[] args) {
        VolatileOrderDemo demo=new VolatileOrderDemo();
        new Thread(()->demo.method1()).start();
        new Thread(()->demo.method2()).start();
    }
}
