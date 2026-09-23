package meng.algorithm.dac;

/**
 * 汉诺塔问题
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(10, 'A', 'B', 'C');
    }

    //汉诺塔的移动的方法
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1){
            System.out.println(num+":"+a+"->"+c);
        }else {
            hanoiTower(num-1,a,c,b);
            System.out.println(num+":"+a+"->"+c);
            hanoiTower(num-1,b,a,c);
        }
    }
}
