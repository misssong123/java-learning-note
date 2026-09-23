package meng.algorithm.recursion;

/**
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。
 * 该问题是国际西洋棋棋手马克斯·贝瑟尔于1848年提出：在8×8格的国际象棋上摆放八个皇后，
 * 使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 */
public class Queue8 {
    /**
     * 具体思路
     * 使用一维数组存放每次符合结果的数据并输出
     * 在判断时，只需要考虑当前皇后，是否位于其他皇后的同一列，或同一斜线即可
     * 数组的下标表示第几个皇后,具体的值表示当前皇后的位置
     */
    private int max = 8;
    private int [] array = new int[max];
    static int  count = 0 ;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("共有"+count+"中解法");
    }
    private void check(int n){
        //表示当前是第九个皇后，前八个皇后的位置合适，可以退出
        if (n == max){
            print(array);
            return;
        }
        for (int i = 0;i < max ;i++){
            array[n]=i+1;
            if (judge(n)){
                check(n+1);
            }

        }

    }
    //判断是否符合
    //判断当前皇后的位置是否位于其他皇后的同一列或者同意斜线
    private boolean judge(int n){
        for(int i =0 ;i <n ;i++){
            //array[i] == array[n] 表示位于同一列
            //Math.abs(i-n) == Math.abs(array[i]-array[n]) 表示位于同一斜线
            if (array[i] == array[n] || Math.abs(i-n) == Math.abs(array[i]-array[n])){
                return false;
            }
        }
        return true;
    }
    //打印结果
    private void print(int[] array){
        count ++;
        for (int i : array)
            System.out.print(i+" ");
        System.out.println();
    }
}