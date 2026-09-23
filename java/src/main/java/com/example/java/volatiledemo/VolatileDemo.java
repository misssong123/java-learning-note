package com.example.java.volatiledemo;

public class VolatileDemo {
    /*
    *
    深度剖析：为什么必须加 volatile？
instance = new Singleton(); 并不是一个原子操作，它分为三步：

memory = allocate(); // 分配内存

ctorSingleton(memory); // 初始化对象

instance = memory; // 设置 instance 指向刚分配的地址

如果没有 volatile，指令可能重排为 1 -> 3 -> 2。

后果：线程 A 执行了 1 和 3，此时 instance 已经非 null，但对象还未初始化。

风险：线程 B 执行到外层的 if (instance == null)，发现不为 null，直接返回了一个半初始化对象，导致空指针或逻辑异常。
    * */
}
class Singleton{
    // 必须加 volatile
    private static volatile Singleton instance;
    public static Singleton getInstance() {
        if (instance == null) { // 第一次检查
            synchronized (Singleton.class) {
                if (instance == null) { // 第二次检查
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}