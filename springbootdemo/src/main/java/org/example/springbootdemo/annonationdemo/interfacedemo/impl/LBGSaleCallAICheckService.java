package org.example.springbootdemo.annonationdemo.interfacedemo.impl;


import org.example.springbootdemo.annonationdemo.interfacedemo.ILBGSaleCallAICheckService;
import org.springframework.stereotype.Service;

@Service
public class LBGSaleCallAICheckService implements ILBGSaleCallAICheckService {
    @Override
    public void printName() {
        System.out.println("LBGSaleCallAICheckService");
    }
}
