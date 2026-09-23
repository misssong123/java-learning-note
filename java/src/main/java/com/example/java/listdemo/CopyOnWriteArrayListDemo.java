package com.example.java.listdemo;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
        for(int i = 0; i < 10; i++){
            final int temp=i;
            new Thread(new Runnable() {

                public void run() {
                    list.add(""+temp);
                    System.out.println(Thread.currentThread().getName()+":"+list);
                }
            },""+temp).start();
        }
    }
}
/**
 *1:[1]
 * 5:[1, 2, 0, 3, 4, 5]
 * 6:[1, 2, 0, 3, 4, 5, 6]
 * 4:[1, 2, 0, 3, 4]
 * 3:[1, 2, 0, 3]
 * 2:[1, 2]
 * 0:[1, 2, 0]
 * 7:[1, 2, 0, 3, 4, 5, 6, 7]
 * 8:[1, 2, 0, 3, 4, 5, 6, 7, 8]
 * 9:[1, 2, 0, 3, 4, 5, 6, 7, 8, 9]
 */