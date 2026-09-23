package meng.algorithm.binarysearchnorecursion;

/**
 * 二分查找非递归
 */
public class BinarySearchNoRecur {
    //二分查找的非递归实现
    /**
     *
     * @param arr 待查找的数组, arr是升序排序
     * @param target 需要查找的数
     * @return 返回对应下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {
        int len = arr.length;
        if (len == 0 || target < arr[0] || target>arr[len-1] )
            return -1;
        int left = 0 , right = len-1;
        while (left <= right){
            int mid = (left+right)/2;
            if (arr[mid] == target){
                return mid;
            }else if(arr[mid]<target){
                left = mid +1;
            }else {
                right = mid -1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        //测试
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 100);
        System.out.println("index=" + index);//
    }
}
