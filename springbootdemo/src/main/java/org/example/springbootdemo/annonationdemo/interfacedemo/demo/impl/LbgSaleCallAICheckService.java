package org.example.springbootdemo.annonationdemo.interfacedemo.demo.impl;

import org.example.springbootdemo.annonationdemo.interfacedemo.demo.ILbgSaleCallAICheckService;
import org.springframework.stereotype.Service;

@Service
public class LbgSaleCallAICheckService implements ILbgSaleCallAICheckService {
    @Override
    public void checkSaleCallAIData(String data) {
        System.out.println("检查销售调用AI数据：" + data);
    }
}
