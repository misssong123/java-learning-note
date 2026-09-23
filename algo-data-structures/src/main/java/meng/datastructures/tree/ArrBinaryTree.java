package meng.datastructures.tree;

/**
 * 顺序存储二叉树
 */
public class ArrBinaryTree {
    private int[] arr;//存储数据结点的数组
    int len ;
    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
        len = arr.length;
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    private void preOrder(int index) {
        if (arr == null || len == 0)
            return;
        System.out.print(arr[index]+"\t");
        if (index*2+1<len)
            preOrder(index*2+1);
        if (index*2+2<len)
            preOrder(index*2+2);
    }
    //重载midOrder
    public void midOrder() {
        this.midOrder(0);
    }

    private void midOrder(int index) {
        if (arr == null || len == 0)
            return;
        if (index*2+1<len)
            midOrder(index*2+1);
        System.out.print(arr[index]+"\t");
        if (index*2+2<len)
            midOrder(index*2+2);
    }
    //重载sufOrder
    public void sufOrder() {
        this.sufOrder(0);
    }

    private void sufOrder(int index) {
        if (arr == null || len == 0)
            return;
        if (index*2+1<len)
            sufOrder(index*2+1);
        if (index*2+2<len)
            sufOrder(index*2+2);
        System.out.print(arr[index]+"\t");
    }
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        //创建一个 ArrBinaryTree
        ArrBinaryTree demo = new ArrBinaryTree(arr);
        demo.preOrder(); // 1,2,4,5,3,6,7
        System.out.println();
        demo.midOrder();
        System.out.println();
        demo.sufOrder();
    }
}
