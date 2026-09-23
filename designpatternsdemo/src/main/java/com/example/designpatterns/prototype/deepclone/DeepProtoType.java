package com.example.designpatterns.prototype.deepclone;

import java.io.*;

public class DeepProtoType implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    public String name; //String 属性
    public DeepCloneableTarget deepCloneableTarget;// 引用类型
    public DeepProtoType() {
        super();
    }
    //第一种克隆的方法，将对象属性在使用克隆的方法来克隆一份对应的对象
    //但是对于存在多个对象属性时，过于麻烦
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepProtoType deepProtoType=null;
        deepProtoType=(DeepProtoType) super.clone();
        deepProtoType.deepCloneableTarget=(DeepCloneableTarget) this.deepCloneableTarget.clone();
        return deepProtoType;
    }
    ////深拷贝 - 方式2 通过对象的序列化实现 (推荐),完成克隆不用在考虑是否存在多个对象
    public Object clone2(){
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {

            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); //当前这个对象以对象流的方式输出

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepProtoType copyObj = (DeepProtoType)ois.readObject();

            return copyObj;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        } finally {
            //关闭流
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }
        }

    }
}
