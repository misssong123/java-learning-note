package meng.datastructures.queue;

import java.util.Scanner;

/**
 * 单次使用
 *
 */
public class ArrayQueue {
    //队伍的后端
    int rear ;
    //队伍的前端
    int front ;
    //存放数据的数组
    int [] queue = null;
    //队列大小
    int size ;
    public ArrayQueue(){
        //默认设置为10
        this(10);
    }
    public ArrayQueue(int size){
        this.rear = -1;
        this.front = -1;
        this.size = size;
        queue = new int[size];
    }
    //出队列
    public int removeQueue(){
        if (isNull())
            throw new RuntimeException("当前队列为空");
        int n = queue[++front] ;
        return n;
    }
    //入队列
    public boolean addQueue(int n){
        if (isFull())
            throw new RuntimeException("当前队列已满");
        queue[++rear] = n;
        return true;
    }
    //查看头元素
    public int head(){
        if (isNull())
            throw new RuntimeException("当前队列为空");
        return queue[front];
    }
    //队列是否为空
    public boolean isNull(){
        return rear == front;
    }
    //队列是否满员
    public boolean isFull(){
        return rear == size-1;
    }
    //打印当前队列
    public void print(){
        if (isNull())
            throw new RuntimeException("当前队列为空");
        for(int i = front+1; i<=rear;i++)
            System.out.print(queue[i]+"->");
        System.out.println();
    }
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    try{
                    queue.print();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    try {
                        int value = scanner.nextInt();
                        queue.addQueue(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.removeQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.head();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }
}

