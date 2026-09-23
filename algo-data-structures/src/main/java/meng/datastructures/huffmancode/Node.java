package meng.datastructures.huffmancode;

/**
 * 工具类
 */
public class Node implements Comparable<Node>{
    Byte data; // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
    int weight; //权值, 表示字符出现的次数
    Node left;
    Node right;
    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }
    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }
    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }
}
