package com.demo.java_web.chapter003;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 字节码
 */
public class EncoderTest {
    public static void main(String[] args) throws Exception {
        String fileName = "1.txt";
        String charset = "UTF-8";
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter writer = new OutputStreamWriter(fos,charset);
        writer.write("测试字符写入");
        writer.flush();
        fos.close();
        writer.close();
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(fis,charset);
        char[] bytes = new char[1024];
        int read = reader.read(bytes);
        System.out.println(new String(bytes,0,read));
        fis.close();
        reader.close();

    }
}
