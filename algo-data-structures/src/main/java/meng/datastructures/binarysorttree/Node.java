package meng.datastructures.binarysorttree;

/**
 * 工具类
 */
public class Node {
    int value;
    Node left;
    Node right;
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }
    //中序遍历
    public void midOrder(){
        if (this.left != null)
            this.left.midOrder();
        System.out.println(this.value);
        if (this.right != null)
            this.right.midOrder();
    }
    //添加节点
    public void add(Node node){
        if (node == null)
            return;
        if (this.value <= node.value){
            if (this.left == null)
                this.left = node;
            else
                this.left.add(node);
        }else {
            if (this.right == null)
                this.right = node;
            else
                this.right.add(node);
        }
    }
    //查找要删除的结点
    /**
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if(value == this.value) { //找到就是该结点
            return this;
        } else if(value < this.value) {//如果查找的值小于当前结点，向左子树递归查找
            //如果左子结点为空
            if(this.left  == null) {
                return null;
            }
            return this.left.search(value);
        } else { //如果查找的值不小于当前结点，向右子树递归查找
            if(this.right == null) {
                return null;
            }
            return this.right.search(value);
        }

    }
    //查找要删除结点的父结点
    /**
     *
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回null
     */
    public Node searchParent(int value) {
        //如果当前结点就是要删除的结点的父结点，就返回
        if((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if(value < this.value && this.left != null) {
                return this.left.searchParent(value); //向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); //向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }

    }

}
