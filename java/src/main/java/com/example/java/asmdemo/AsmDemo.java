package com.example.java.asmdemo;

public class AsmDemo {
    public static void main(String[] args) {
        byte[] classData = HelloWorldGenerator.generateHelloWorldClass();
        CustomClassLoader loader = new CustomClassLoader();
        Class<?> helloWorldClass = loader.defineClass("HelloWorld", classData);
        try {
            // Invoke the main method
            helloWorldClass.getMethod("main", String[].class).invoke(null, (Object) args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Custom class loader to define a class from byte array
    static class CustomClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }
}
