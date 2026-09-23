package com.example.designpatterns.template.improve;

public class PureSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {

    }
    //可以根据需要重写钩子方法
    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
