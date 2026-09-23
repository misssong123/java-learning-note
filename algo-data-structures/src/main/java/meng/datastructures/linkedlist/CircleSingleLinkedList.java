package meng.datastructures.linkedlist;

/**
 * 约瑟夫问题的简单模拟和实现
 */
public class CircleSingleLinkedList {
    // 指向第一个节点,当前没有编号
    private Boy first = null;
    //创建指定人数的约瑟夫环
    public void addBoy(int num) {
        if (num < 1 ){
            System.out.println("人数过少，不能组成环");
            return;
        }
        //辅助节点
        Boy cur = null;
        for (int i = 1 ; i<=num ; i++){
            Boy boy = new Boy(i);
            if (i==1){
                first = boy;
                first.setNext(first);
                cur = first;
            }else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }
    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {// 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }

    /**
     *
     * @param startNo 从第几个小孩开始
     * @param countNum 每次报的数
     * @param nums 队列中共有多少个小孩
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }

        Boy cur = first , pre = null;
        //找到开始的男孩和开始的男孩的上一个男孩
        if (startNo == 1){
            cur = first;
            pre = first ;
            while (pre.getNext() != first)
                pre = pre.getNext();
        }else {
            pre = first;
            for (int i = 1 ; i < startNo-1 ;i++){
                pre = pre.getNext();
            }
            cur = pre.getNext();
        }
        //当环中只存在一个人时,退出
        System.out.println("出圈顺序为:");
       while (cur.getNext() != cur){
           for(int i = 1 ; i<countNum;i++){
               pre = pre.getNext();
               cur = cur.getNext();
           }
           System.out.print(cur.getNo()+"->");
           pre.setNext(cur.getNext());
           cur = cur.getNext();
       }
        System.out.println();
        System.out.print("最后留在圈中的小孩编号"+cur.getNo());
    }

    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(10);// 加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //测试一把小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 3, 10); // 2->4->1->5->3
        //String str = "7*2*2-5+1-5+3-3";
    }
}
