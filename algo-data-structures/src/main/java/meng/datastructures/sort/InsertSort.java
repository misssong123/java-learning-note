package meng.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1, -1, 89};
        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("插入排序前");
        //System.out.println(Arrays.toString(arr));
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        insertSort(arr); //调用插入排序算法
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }
    //插入排序
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        //使用for循环来把代码简化
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i; // 即arr[1]的前面这个数的下标
            while (insertIndex > 0 && insertVal < arr[insertIndex-1]) {
                arr[insertIndex ] = arr[insertIndex-1];// arr[insertIndex]
                insertIndex--;
            }
            if (insertIndex  != i) {
                arr[insertIndex ] = insertVal;
            }
        }
    }
}