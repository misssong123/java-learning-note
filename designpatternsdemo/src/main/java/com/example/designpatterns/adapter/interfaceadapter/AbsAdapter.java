package com.example.designpatterns.adapter.interfaceadapter;

//在AbsAdapter 我们将 Interface4 的方法进行默认实现
//接口中的方法可能不一定都需要具体的实现，使用该抽象类进行默认实现，具体的方法在进行具体的实现
public abstract class AbsAdapter implements Interface4 {

    //默认实现
    public void m1() {

    }

    public void m2() {

    }

    public void m3() {

    }

    public void m4() {

    }
}
