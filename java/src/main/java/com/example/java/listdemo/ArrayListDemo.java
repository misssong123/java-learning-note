package com.example.java.listdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 集合不安全的问题
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        Collections.synchronizedList(list);
        for(int i = 0; i < 20; i++){
            new Thread(new Runnable() {
                public void run() {
                    list.add((UUID.randomUUID()+"").substring(1,8));
                    System.out.println(list);
                }
            }).start();
        }
    }
}
/**
 * java.util.ConcurrentModificationException
 * 	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
 * 	at java.util.ArrayList$Itr.next(ArrayList.java:859)
 * 	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
 * 	at java.lang.String.valueOf(String.java:2994)
 * 	at java.io.PrintStream.println(PrintStream.java:821)
 * 	at com.meng.collectiondemo.ArrayListDemo$1.run(ArrayListDemo.java:19)
 * 	at java.lang.Thread.run(Thread.java:748)
 */
