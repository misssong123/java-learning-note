package org.example.springbootdemo.scopedemo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Scope("singleton")
public class BService {
    /*BService(AService aService){
    }*/
    @Resource
    private AService aService;
}
