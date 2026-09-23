package meng.datastructures.stack;

import java.util.Scanner;

/**
 * 模拟栈的简单实现
 */
public class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据就放在该数组
    private int top = -1;// top表示栈顶，初始化为-1
    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    public boolean isFull() {
        return top == maxSize - 1;
    }
    public boolean isEmpty() {
        return  top == -1;
    }
    public void push(int value) {
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        stack[++top] =value;
    }
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return stack[top--];
    }
    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }
    public void list(){
        if (isEmpty()){
            System.out.println("栈为空");
            return;
        }
        for (int i = top ; i>=0 ;i--)
            System.out.print(stack[i]+"->");
        System.out.println();
    }

    public static void main(String[] args) {
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(5);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");
    }
}
