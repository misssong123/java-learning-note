package meng.datastructures.avl;

/**
 * 工具类
 */
public class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {
        this.value = value;
    }
    // 返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }
    // 返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }
    // 返回 以该结点为根结点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }
    //左旋转方法
    private void leftRotate() {
        //1.创建新节点，值为当前节点
        Node node = new Node(this.value);
        //2.新节点的左子树为当前节点的左子树
        node.left = this.left;
        //3.新节点的右子树为当前节点右子树的左子树
        node.right = this.right.left;
        //4.当前节点的值设置为当前节点右节点的值
        this.value = this.right.value;
        //5.当前节点的左子树指向新节点
        this.left = node;
        //6.当前节点的右子树指向当前节点右节点的右子树
        this.right = this.right.right;
    }
    //右旋转方法
    private void rightRotate() {
        //1.创建新节点，值为当前节点
        Node node = new Node(this.value);
        //2.新节点的左子树为当前节点的左节点的右子树
        node.left = this.left.right;
        //3.新节点的右子树为当前节点右子树
        node.right = this.right;
        //4.当前节点的值设置为当前节点左节点的值
        this.value = this.left.value;
        //5.当前节点的右子树指向新节点
        this.right = node;
        //6.当前节点的左子树指向当前节点左节点的左子树
        this.left = this.left.left;
    }
    // 添加结点的方法
    // 递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 判断传入的结点的值，和当前子树的根结点的值关系
        if (node.value < this.value) {
            // 如果当前结点左子结点为null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归的向左子树添加
                this.left.add(node);
            }
        } else { // 添加的结点的值大于 当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归的向右子树添加
                this.right.add(node);
            }

        }

        //当添加完一个结点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
        if(rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if(right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后在对当前结点进行左旋转
                leftRotate(); //左旋转..
            } else {
                //直接进行左旋转即可
                leftRotate();
            }
            return ; //必须要!!!
        }

        //当添加完一个结点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
        if(leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转即可
                rightRotate();
            }
        }
    }
    // 查找要删除的结点
    /**
     *
     * @param value
     *            希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) { // 找到就是该结点
            return this;
        } else if (value < this.value) {// 如果查找的值小于当前结点，向左子树递归查找
            // 如果左子结点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { // 如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }

    }

    // 查找要删除结点的父结点
    /**
     *
     * @param value
     *            要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回null
     */
    public Node searchParent(int value) {
        // 如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }

    }
}
