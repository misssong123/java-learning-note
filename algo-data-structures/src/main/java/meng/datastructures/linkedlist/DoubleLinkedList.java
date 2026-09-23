package meng.datastructures.linkedlist;

/**
 * 双向链表的简单使用
 */
public class DoubleLinkedList {
    //头结点
    private Hero2 head = new Hero2(0, "", "");
    // 返回头节点
    public Hero2 getHead() {
        return head;
    }
    public void list(){
        if (head.next == null){
            System.out.println("该链表为空~");
            return;
        }
        Hero2 temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
    public void add(Hero2 node){
        Hero2 temp = head;
        while (temp.next !=null)
            temp = temp.next;
        temp.next = node;
        node.pre = temp;
    }
    public void update(Hero2 node){
        if (head.next == null){
            System.out.println("该链表为空~");
            return;
        }
        Hero2 temp = head.next;
        while(temp != null){
            if (temp.no == node.no){
                temp.name = node.name;
                temp.nickname = node.nickname;
                return;
            }
            temp = temp.next;
        }
        System.out.println("未找到到更改的对象");
    }
    public void del(int no){
        if (head.next == null){
            System.out.println("该链表为空~");
            return;
        }
        Hero2 temp = head.next;
        while(temp != null){
            if (temp.no == no){
                temp.pre.next = temp.next;
                temp.next.pre = temp.pre;
                return;
            }
            temp = temp.next;
        }
        System.out.println("未找到到待删除的对象");
    }
    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        Hero2 hero1 = new Hero2(1, "宋江", "及时雨");
        Hero2 hero2 = new Hero2(2, "卢俊义", "玉麒麟");
        Hero2 hero3 = new Hero2(3, "吴用", "智多星");
        Hero2 hero4 = new Hero2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        Hero2 newHeroNode = new Hero2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}
