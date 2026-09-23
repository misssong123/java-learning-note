package meng.datastructures.linkedlist;

/**
 * 为实现单链表的工具类
 */
public class Hero {
    //编号
    public int no;
    //姓名
    public String name;
    //别名
    public String nickname;
    public Hero next;
    public Hero(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
