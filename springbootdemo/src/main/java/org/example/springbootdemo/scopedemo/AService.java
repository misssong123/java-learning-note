package org.example.springbootdemo.scopedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 循环依赖问题
 */
@Service
public class AService {
    /*AService(BService bService){
    }*/
    @Autowired
    private BService bService;
    public int add(int a,int b){
        System.out.println("add方法被调用");
        return a+b;
    }
    public int sub(int a,int b){
        System.out.println("sub方法被调用");
        return a-b;
    }
}
