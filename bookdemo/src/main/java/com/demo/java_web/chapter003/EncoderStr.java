package com.demo.java_web.chapter003;


import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncoderStr {
    public static void main(String[] args) throws Exception{
        String str = "测试字符写入";
        byte[] bytes = str.getBytes("UTF-8");
        String s = new String(bytes);
        System.out.println(s);

        Charset utf8 = StandardCharsets.UTF_8;
        ByteBuffer encode = utf8.encode(str);
        System.out.println(encode);
        CharBuffer decode = utf8.decode(encode);
        System.out.println(decode);

    }
}
