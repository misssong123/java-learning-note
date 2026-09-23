package meng.datastructures.linkedlist;

/**
 * 模拟双向链表的工具类
 */
public class Hero2 {
        public int no;
        public String name;
        public String nickname;
        public Hero2 next; // 指向下一个节点, 默认为null
        public Hero2 pre; // 指向前一个节点, 默认为null

        public Hero2(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
        }

}
