package meng.datastructures.array.sparsearray;

/**
 * 稀疏数组的基本使用
 * 1.二维数组转稀疏数组
 * 2.稀疏数组转二维数据
 */
public class SparseArray {
    public static void main(String[] args) {
        SparseArray demo = new SparseArray();
        int [][] array = new int[][]{{1,0,0,0},{0,0,0,1},{0,0,0,0},{1,0,0,1}};
        System.out.println("-------原数组-------");
        demo.print(array);
        System.out.println("-------稀疏数组-------");
        int[][] ans = demo.transform(array);
        demo.print(ans);
        System.out.println("-------原数组-------");
        int[][] restore = demo.restore(ans);
        demo.print(restore);
    }

    /**
     * 二维数组转稀疏数组
     * 1.统计二维数组的存在数据的个数sum
     * 2.创建稀疏数组new int[sum+1][3],第一行存放二维数组的 行数，列数，有效值数
     * 3.将二维数据中的有效数组存放到稀疏数组中
     * @param array 原数组
     * @return 稀疏数组
     */
    public int[][] transform(int[][] array){
        int sum = 0;
        for (int i = 0 ; i < array.length ; i++)
            for (int j =0 ; j < array[i].length ; j++)
                if (array[i][j] != 0)
                    sum++;
         int [][] ans = new int[sum+1][3];
         ans[0][0]=array.length;
         ans[0][1]=array[1].length;
         ans[0][2]=sum;
         int step = 0 ;
        for (int i = 0 ; i < array.length ; i++)
            for (int j =0 ; j < array[i].length ; j++)
                if (array[i][j] != 0){
                    step++;
                    ans[step][0]=i;
                    ans[step][1]=j;
                    ans[step][2]=array[i][j];
                }
        return ans;
    }

    /**
     * 稀疏数组转二维数据
     * 1.读取稀疏数组的第一行，确定该二维数组的维度和有效值的个数
     * 2.依次将稀疏数组其他行的数据还原到二维数组中
     * @param array
     * @return
     */
    public int[][] restore(int[][] array){
        int i = array[0][0],j=array[0][1],sum=array[0][2];
        int[][] ans = new int[i][j];
        for(int k = 1 ; k <=sum ;k++){
            ans[array[k][0]][array[k][1]] = array[k][2];
        }
        return ans;
    }
     public void print(int[][] array){
            for (int i = 0 ; i < array.length ; i++){
                for (int j =0 ; j < array[i].length ; j++){
                    System.out.print(array[i][j]+"\t");
                }
                System.out.println();
            }
     }
}
