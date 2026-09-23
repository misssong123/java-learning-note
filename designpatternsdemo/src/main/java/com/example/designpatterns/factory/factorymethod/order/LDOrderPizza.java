package com.example.designpatterns.factory.factorymethod.order;

import com.qust.factory.factorymethod.pizza.LDCheesePizza;
import com.qust.factory.factorymethod.pizza.LDPepperPizza;
import com.qust.factory.factorymethod.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        // TODO Auto-generated method stub
        return pizza;
    }
}
