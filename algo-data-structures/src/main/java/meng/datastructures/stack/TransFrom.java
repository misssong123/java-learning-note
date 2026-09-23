package meng.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 转换，将中序表达式转换成后序表达式（逆波兰表达式）
 */
public class TransFrom {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 10+((20+3)×4)-5 => 转成  10 20 3 + 4 × + 5 –
        String s="10+((20+3)*4)-5";
        System.out.println(toInfixExpressionList(s));
        System.out.println(parseSuffixExpreesionList(toInfixExpressionList(s)));
    }

    /**
     * 中缀表达式转换为后缀表达式的规则
     * 1.初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2.从左至右扫描中缀表达式；
     * 3.遇到操作数时，将其压s2；
     * 4.遇到运算符时，比较其与s1栈顶运算符的优先级：
     *      1）如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *      2）否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     *      3）否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
     * 5.遇到括号时：  (1) 如果是左括号“(”，则直接压入s1 (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6.重复步骤2至5，直到表达式的最右边
     * 7.将s1中剩余的运算符依次弹出并压入s2
     * 8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * @param ls
     * @return
     */
    //方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); // 符号栈
        //由于需要逆序转换直接将结果放入到List中按照正序输出即可
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的Lists2
        //遍历ls
        for(String item: ls) {
            if(item.matches("\\d+")){//如果是数字直接将数字放入
                s2.add(item);
            }else if ("(".equals(item)){//如果是左括号也直接放入
                s1.push(item);
            }else if (")".equals(item)){//如果是右括号将s1中的符号依次弹出，放入到s2中
                while (!"(".equals(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.pop();//将左括号弹出
            }else{//进行符号比较
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
    }
    //方法：将 中缀表达式转成对应的List
    //  s="1+((2+3)×4)-5";
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<String>();
        int index=0;//记录遍历位置
        char c;//记录截取的字符
        String temp;//拼接多位数
        while (index<s.length()){
            c=s.charAt(index);
            if (c<48||c>57){//通过坐标记录是否为数字
                ls.add(""+c);
                index++;
            }else{
                temp="";//先将temp置空
                temp+=c;
                index++;
                while (index<s.length()&&s.charAt(index)>=48&&s.charAt(index)<=57){
                    temp+=s.charAt(index);
                    index++;
                }
                ls.add(temp);
            }
        }
        return ls;//返回
    }
}
//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "x":
                result = MUL;
                break;
            case "X":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }

}
