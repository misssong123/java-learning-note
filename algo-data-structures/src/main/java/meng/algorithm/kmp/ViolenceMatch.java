package meng.algorithm.kmp;

/**
 * 暴力匹配
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 硅尚谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    private static int violenceMatch(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int a = 0 , b = 0;
        while(a<len1 && b<len2){
            if (str1.charAt(a) == str2.charAt(b)){
                a++;
                b++;
            }else {
                a= a-b+1;
                b=0;
            }
        }
        if (b == len2)
            return a-b;
        return -1;
    }
}
