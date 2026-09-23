package com.example.java.volatiledemo;
import java.util.concurrent.TimeUnit;
public class VolatileVisibilityDemo {
    public static void main(String[] args) {
        Demo1 demo=new Demo1();
        new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo.changeData();
                System.out.println(Thread.currentThread().getName()+"将Demo1中data进行改变");
            }
        }).start();
        while(demo.data==0){
        }
        System.out.println("Demo1中data数字被改变");
    }
}
class Demo1{
    volatile int data=0;
    public void changeData(){
        this.data=60;
    }
}