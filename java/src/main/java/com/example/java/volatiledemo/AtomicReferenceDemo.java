package com.example.java.volatiledemo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用类的样例
 * 原子引用会比较对应的地址
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Demo4> demo=new AtomicReference<>();
        Demo4 t1=new Demo4("王",12);
        Demo4 t2=t1;
        demo.set(t1);
        System.out.println(demo.get());
        t2.setAge(15);
        System.out.println(demo.compareAndSet(t2,t1)+","+demo.get());
    }
}
class Demo4{
    private String name;
    private Integer age;

    public Demo4(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Demo4() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Demo4{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
