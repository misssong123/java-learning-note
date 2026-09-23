package meng.datastructures.linkedlist;

/**
 * 模拟单链表的简单使用
 */
public class SingleLinkedList {
    //头结点
    public Hero head = new Hero(0,"","");
    //返回头节点
    public Hero getHead() {
        return head;
    }
    /**
     * 直接向后添加
     * @param hero 待添加的英雄
     */
    public void add(Hero hero) {
        //辅助节点
        Hero temp = head;
        //遍历链表，找到最后
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = hero;
    }
    /**
     * 根据order进行添加
     * @param hero
     */
    public void addByOrder(Hero hero) {
        //辅助节点
        Hero pre = head;
        //遍历链表，找到最后
        while(pre.next != null && pre.next.no < hero.no) {
            pre = pre.next;
        }
        if (pre.next == null)
            pre.next = hero;
        else {
            if (pre.next.no == hero.no){
                System.out.println("已经存在与之编号相同的英雄");
                return;
            }
            hero.next=pre.next;
            pre.next=hero;
        }
    }
    /**
     * 根据no进行更新
     * @param newHero
     */
    public void update(Hero newHero) {
        //判断是否空
        if(head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        Hero temp = head.next;
        boolean flag = false;
        while (temp!=null){
            if (temp.no == newHero.no){
                temp.nickname = newHero.nickname;
                temp.name = newHero.name;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag)
            System.out.println("未找到该对象");
    }
    /**
     * 根据no进行删除
     * @param no
     */
    public void del(int no) {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Hero temp = head;
        boolean flag = false;
        while (temp.next != null){
            if (temp.next.no == no){
                flag=true;
                System.out.println("删除的对象信息为:"+temp.next);
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        if (!flag)
            System.out.println("要删除的节点不存在");
    }
    /**
     * 展示链表信息
     */
    public void list() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Hero temp = head.next;
        while(temp !=null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
    public static void main(String[] args) {
        //先创建节点
        Hero hero1 = new Hero(1, "宋江", "及时雨");
        Hero hero2 = new Hero(2, "卢俊义", "玉麒麟");
        Hero hero3 = new Hero(3, "吴用", "智多星");
        Hero hero4 = new Hero(4, "林冲", "豹子头");
        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
/*        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        // 测试一下单链表的反转功能
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();*/
		//加入按照编号的顺序
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		//显示一把
		singleLinkedList.list();
		//测试修改节点的代码
		Hero newHeroNode = new Hero(2, "小卢", "玉麒麟~~");
		singleLinkedList.update(newHeroNode);
		System.out.println("修改后的链表情况~~");
		singleLinkedList.list();
		//删除一个节点
		singleLinkedList.del(1);
		singleLinkedList.del(4);
		System.out.println("删除后的链表情况~~");
		singleLinkedList.list();

    }
}
