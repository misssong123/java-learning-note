package com.demo.java_web.chapter003;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncodeWay {
    public static void main(String[] args) {
        String name = "I am 君山";
        try{
            System.out.println(Arrays.toString(name.getBytes(StandardCharsets.ISO_8859_1)));

            System.out.println(Arrays.toString(name.getBytes(Charset.forName("GB2312"))));

            System.out.println(Arrays.toString(name.getBytes(Charset.forName("GBK"))));

            System.out.println(Arrays.toString(name.getBytes(StandardCharsets.UTF_16)));

            System.out.println(Arrays.toString(name.getBytes(StandardCharsets.UTF_8)));

        }catch (Exception e){

        }
    }
}
