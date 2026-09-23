package meng.algorithm.dynamic;

/**
 * 01背包问题
 */
public class KnapsackProblem01 {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值 这里val[i] 就是前面讲的v[i]
        int m = 4; //背包的容量
        int n = val.length; //物品的个数
        //存放价值情况
        int[][] v = new int[n+1][m+1];
        //根据前面得到公式来动态规划处理
        for(int i = 1; i < v.length; i++) { //不处理第一行 i是从1开始的
            for(int j=1; j < v[0].length; j++) {//不处理第一列, j是从1开始的
                //公式
                if(w[i-1]> j) { // 因为我们程序i 是从1开始的，因此原来公式中的 w[i] 修改成 w[i-1]
                    v[i][j]=v[i-1][j];
                } else {
                    v[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i - 1][j - w[i - 1]]);
                }
            }
        }
        //查看数组情况
        for(int i =0; i < v.length;i++) {
            for(int j = 0; j < v[i].length;j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("最大价值为:"+v[v.length-1][v[0].length-1]);
    }
}
