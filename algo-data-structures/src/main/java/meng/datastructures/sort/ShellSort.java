package meng.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        // 创建要给80000个的随机的数组
        int[] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        //shellSort(arr); //交换式
        shellSort2(arr);//移位方式
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
        //System.out.println(Arrays.toString(arr));
    }
    // 希尔排序时， 对有序序列在插入时采用交换法,
    public static void shellSort(int[] arr) {
        int temp =0;
        for (int gap = arr.length/2;gap>=1 ;gap=gap/2){
            for(int i = gap ; i< arr.length;i++){
                for (int j = i-gap;j>=0;j-=gap){
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = arr[j];
                    }
                }
            }
        }
    }
    //对交换式的希尔排序进行优化->移位法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length/2;gap>=1 ;gap=gap/2){
            for(int i = gap ; i< arr.length;i++){
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
