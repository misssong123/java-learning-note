package org.example.springbootdemo.annonationdemo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void printName() {
        System.out.println("我是用户服务");
    }
}
