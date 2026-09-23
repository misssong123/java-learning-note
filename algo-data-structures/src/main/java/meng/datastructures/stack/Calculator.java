package meng.datastructures.stack;

/**
 * 对栈的简单使用
 * 暂时不支持括号及小数点
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "7*2*2-5+1-5+3-4";
        //创建数栈和操作符栈
        ArrayStackImp numStack = new ArrayStackImp(expression.length());
        ArrayStackImp operStack = new ArrayStackImp(expression.length());
        int len = expression.length();
        String keepNum = "";
        char c = ' ';
        int num1=0,num2=0;
        for(int i = 0 ; i < len ;i++){
            c = expression.charAt(i);
            //数(需要判断是不是多位数)直接入数栈，操作符需要比较操作
            if (operStack.isOper(c)){
                if (operStack.isEmpty()){
                    operStack.push(c);
                }else{
                    if (operStack.priority(c) > operStack.priority(operStack.peek())){
                        operStack.push(c);
                    }else {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        numStack.push(numStack.cal(num1,num2,operStack.pop()));
                        operStack.push(c);
                    }
                }
            }else {
                //判断是否为多位数
                keepNum += c;
                if (i == len-1)
                    numStack.push(Integer.parseInt(keepNum));
                else {
                    if (numStack.isOper(expression.charAt(i+1))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
        }
        while (!operStack.isEmpty()){
            numStack.push(numStack.cal(numStack.pop(),numStack.pop(),operStack.pop()));
        }
        System.out.println(expression+"表达式计算的结果为:"+numStack.pop());
    }
}
//扩展部分功能
class ArrayStackImp extends  ArrayStack{

    public ArrayStackImp(int maxSize) {
        super(maxSize);
    }
    //判断是否为指定运算符
    public boolean isOper(char oper){
        return oper == '+' || oper == '-' || oper == '*' || oper == 'x' ||  oper == '/';
    }
    public int priority(int oper) {
        if (oper == '+' || oper == '-')
            return 0;
        if (oper == '/' || oper == '*' || oper == 'x')
            return 1;
        return -1;
    }
    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;// 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}