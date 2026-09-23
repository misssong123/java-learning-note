package com.example.java.hashTabledemo;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}
//创建HashTab用来存储链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    public HashTab(int size){
        this.size =size;
        empLinkedListArray=new EmpLinkedList[size];
        for (int i=0;i<size;i++){//先将每条链表进行初始化，防止使用时报空指针异常
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }
    //添加雇员
    public void add(Emp emp) {
        //根据员工的id ,得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);

    }
    //遍历所有的链表,遍历hashtab
    public void list() {
        for(int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id,查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp != null) {//找到
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        }else{
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }
    //编写散列函数, 使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }
}
//创建链表用于存储人员信息
class EmpLinkedList{
    //头结点
    private Emp head;
    //添加员工
    public void add(Emp emp){
        if (head==null){
            head=emp;
        }
        Emp temp=head;
        while(true){
            if (temp.next==null)
                break;
            temp=temp.next;
        }
        temp.next=emp;
    }
    //遍历员工
    public void list(int no){
        if (head==null) {
            System.out.println("第" + (no + 1) + "条链表为空");
            return;
        }
        System.out.print("第 "+(no+1)+" 链表的信息为");
        Emp curEmp = head; //辅助指针
        while(true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if(curEmp.next == null) {//说明curEmp已经是最后结点
                break;
            }
            curEmp = curEmp.next; //后移，遍历
        }
        System.out.println();
    }
    //查找员工

    //根据id查找雇员
    //如果查找到，就返回Emp, 如果没有找到，就返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if(head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true) {
            if(curEmp.id == id) {//找到
                break;//这时curEmp就指向要查找的雇员
            }
            //退出
            if(curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//以后
        }

        return curEmp;
    }
}

class Emp{
    public  int id;
    public  String name;
    public Emp next;
    public Emp(int id,String name){
        this.id=id;
        this.name=name;
    }
}