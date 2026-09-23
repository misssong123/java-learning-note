package com.example.designpatterns.template;
//抽象父类，实现共有的方法，将需要特殊实现的方法设置成抽象，由子类去实现
public abstract class SoyaMilk {
    //将模板方法可以做成final , 不让子类去覆盖.
    final void make() {
        select();
        addCondiments();
        soak();
        beat();

    }

    //选材料
    void select() {
        System.out.println("第一步：选择好的新鲜大豆  ");
    }

    //添加不同的配料， 抽象方法, 子类具体实现
    abstract void addCondiments();

    //浸泡
    void soak() {
        System.out.println("第三步， 大豆和配料开始浸泡， 需要3小时 ");
    }

    void beat() {
        System.out.println("第四步：大豆和配料放到豆浆机去打碎  ");
    }
}
