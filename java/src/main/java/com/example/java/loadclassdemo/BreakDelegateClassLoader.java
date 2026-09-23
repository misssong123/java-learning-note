package com.example.java.loadclassdemo;

import java.io.*;


public class BreakDelegateClassLoader extends ClassLoader {
    private String classPath;

    public BreakDelegateClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 关键点：重写 loadClass 逻辑
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // 1. 先检查是否已经加载过该类
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                // 2. 如果是 Java 核心库（如 java.lang.*），必须交给启动类加载器
                // 否则会报 SecurityException，且保证了基础类型的一致性
                if (name.startsWith("java.")) {
                    c = getSystemClassLoader().loadClass(name);
                }
            }

            if (c == null) {
                // 3. 【打破双亲委派的核心】：先尝试自己加载
                try {
                    c = findClass(name);
                } catch (ClassNotFoundException e) {
                    // 4. 如果自己找不到（比如当前类依赖了别的库），再委派给父类
                    c = super.loadClass(name);
                }
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 将包名转换为文件路径，例如 com.test.User -> com/test/User.class
            String fileName = name.replace(".", "/") + ".class";
            File file = new File(classPath, fileName);
            if (!file.exists()) {
                throw new ClassNotFoundException(name);
            }

            byte[] data = loadByte(file);
            // 将字节数组转化为 Class 对象
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    private byte[] loadByte(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
    }
}
