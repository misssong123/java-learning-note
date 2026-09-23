package meng.datastructures.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        // 测试构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(10);// 加入10个小孩节点
        circleSingleLinkedList.showBoy();


        circleSingleLinkedList.countBoy(1, 3, 10);
    }
}
class CircleSingleLinkedList{
    private Boy first=null;//创建first节点
    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums){
        if(nums<1){
            System.out.println("请输入大于1的数字");
            return;
        }
        Boy curBoy=null;
        for (int i =1;i<=nums;i++){
            Boy boy=new Boy(i);
            if(i==1){
                first=boy;
                curBoy=first;
                curBoy.setNext(first);
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }
    // 遍历当前的环形链表
    public void showBoy() {
        if (first.getNext()==null){
            System.out.println("当前链表中还未存储数据");
            return;
        }
        Boy curBoy=first;
        while (true){
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext()==first){
                break;
            }
            curBoy=curBoy.getNext();
        }
    }

    // 根据用户的输入，计算出小孩出圈的顺序
    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //思路:需要一个辅助节点，记录前一个小孩，否则当前小孩出环后，无法形成环状，先让其指向最后一个小孩
        if (startNo<1||countNum<1||startNo>nums){
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        if (first.getNext()==null){
            System.out.println("链表为空");
            return;
        }
        //获取最后一个小孩
        Boy preBoy=first;
        for (int i=1;i<nums;i++){
            preBoy=preBoy.getNext();
        }
        //从第几个小孩开始
        for (int i=1;i<startNo;i++){
            first=first.getNext();
            preBoy=preBoy.getNext();
        }
        while(true){
            if (preBoy==first){
                break;
            }
            //指定小孩出队
            for (int i=1;i<countNum;i++){
                first=first.getNext();
                preBoy=preBoy.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            first=first.getNext();
            preBoy.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}
class Boy{
    private int no;// 编号
    private Boy next; // 指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}