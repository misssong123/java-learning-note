package com.example.designpatterns.single;

public class SingleDemo {
    public static void main(String[] args) {
        Single4 single4=Single4.INSTANCE;
        Single4 single5=Single4.INSTANCE;
        System.out.println(single4==single5);
        System.out.println(single4.hashCode());
        System.out.println(single5.hashCode());
        /**
         * Runtime单例模式
         public static Runtime getRuntime () {
         private static Runtime currentRuntime = new Runtime();
         public static Runtime getRuntime() {
         return currentRuntime;
         }
          private Runtime() {
        }
         */
        Runtime runtime=Runtime.getRuntime();
        System.out.println(runtime.hashCode());
    }
}
class Single{
    //1.饿汉式，会影响一定空间的浪费
    private final static Single single=new Single();
    private Single(){//构造方法私有化，避免外部创建对象

    }
    public static Single getInstance(){
        return single;
    }
}
class Single1{
    //1.懒汉式,在多线程会造成错误
    private  static Single1 single1;
    private Single1(){//构造方法私有化，避免外部创建对象

    }
    public static Single1 getInstance(){
        if (single1==null)
            single1=new Single1();
        return  single1;
    }
}
class Single2{
    //1.懒汉式,线程安全，推荐使用
    private  static Single2 single2;
    private Single2(){//构造方法私有化，避免外部创建对象

    }
    public static Single2 getInstance(){
        if (single2==null){
            synchronized (Single2.class){
                if (single2==null){
                    single2=new Single2();
                }
            }
        }
        return  single2;
    }
}
class Single3{
    //1.静态内部类构建,推荐使用
    private Single3(){//构造方法私有化，避免外部创建对象

    }
    private static class Single3Instance{
        private final static Single3 SINGLE_3=new Single3();
    }
    public static Single3 getInstance(){
      return Single3Instance.SINGLE_3;
    }
}
enum  Single4{
    //1.枚举，进行实现
    INSTANCE;
}