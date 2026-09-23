package com.example.java.loadclassdemo;

public class TestBreak {
    public static void main(String[] args) throws Exception {
        String path = "/Users/mengsong/IdeaProjects/springbootdemo/target/classes/";
        // 加载器1 加载 V1 版本
        BreakDelegateClassLoader loader1 = new BreakDelegateClassLoader(path);
        Class<?> clazz1 = loader1.loadClass("com.example.springbootdemo.stardemo.dto.User");

        // 加载器2 加载 V2 版本
        BreakDelegateClassLoader loader2 = new BreakDelegateClassLoader(path);
        Class<?> clazz2 = loader2.loadClass("com.example.springbootdemo.stardemo.dto.User");

        System.out.println("clazz1 hash: " + clazz1.hashCode());
        System.out.println("clazz2 hash: " + clazz2.hashCode());
        System.out.println("两者是否相等: " + (clazz1 == clazz2));

        // 结果：false。说明同一全限定名的类在 JVM 中存在了两份！

        loader1 = new BreakDelegateClassLoader(path);
        clazz1 = loader1.loadClass("java.lang.String");

        // 加载器2 加载 V2 版本
        loader2 = new BreakDelegateClassLoader(path);
        clazz2 = loader2.loadClass("java.lang.String");

        System.out.println("clazz1 hash: " + clazz1.hashCode());
        System.out.println("clazz2 hash: " + clazz2.hashCode());
        System.out.println("两者是否相等: " + (clazz1 == clazz2));
    }
}
