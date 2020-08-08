package indi.tom.dataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Tom
 * @Date 2019/11/9 20:06
 * @Version 1.0
 * @Description 逆波兰表达式。算数表达式分为：
 * <p>
 * . 前序表达式(波兰表达式)：运算符在数字前边，或者说以运算符开始。并不是所有运算发在所有数字前边
 * . 中序表达式：我们常见的表达式，符号在数字中间
 * . 后续表达式(逆波兰表达式)：数字在前，符号在后。但是并不是所有数字在所有符号前边。
 * <p>
 * 中序表达式对我们人最友好，但是对计算机不友好。
 * 逆波兰表达式对计算机最友好。
 *
 * 下边的程序实现了如下功能：
 * 1. 将字符串转列表，方便操作表达式每一个元素
 * 2. 将中序表达式list转成后续表达式list
 * 3. 根据输入的后续表达式，计算出结果
 */
public class PolandNotation {
    public static void main(String[] args) {
        /**
         * 下边我们对一个逆波兰表达式求值。
         * (3+4)*5 -6 对应的逆波兰表达式是： 3 4 + 5 * 6 -
         * 为了方便，我们把每个数字和运算符用空格隔开，方便我们获取每个元素
         * 这里我们只进行整数运算，不考虑小数。
         */
        String expression = "1+((2+3)*4)-5";
        //将原始表达式字符串转换成列表，方便后续操作
        List<String> middleList = convertMiddleExpressionToList(expression);
        List<String> afterList = convertMiddleToBack(middleList);
        int result = calc(afterList);
        System.out.println("最终计算结果是---->" + result);
    }
    //将中缀表达式字符串转换成存放在list列表中的中缀表达式，方便我们以后操作。
    public static List convertMiddleExpressionToList(String expression) {
        //如果字符串为空串，抛出异常
        if (expression == null || "".equals(expression)) throw new IllegalArgumentException("传入的字符串为空");
        //定义一个List，用于依次存放表达式中的每个元素
        ArrayList<String> list = new ArrayList<>();
        //遍历字符串每个元素
        //存放遍历过程中当前字符
        char current;
        //存放遍历过程中，表达式中的数字
        String number;
        for (int i = 0; i < expression.length(); i++) {
            current = expression.charAt(i);
            if (current == '+' || current == '-' ||
                    current == '*' || current == '/' ||
                    current == '(' || current == ')') {
                list.add("" + current);
            } else if (current >= 48 && current <= 57) {
                //数字0到9，需要考虑多为数字的情况
                number = current + "";
                while (++i < expression.length() && (current = expression.charAt(i)) >= 48
                        && current <= 57) {
                    number += current + "";
                }
                //之所以要i--，是因为外层的for循环还要将i++一次，这样如果不做i--，相当于i+2了，跳过一个元素了。
                i--;
                list.add(number);
            } else {
                throw new IllegalArgumentException("表达式中含有非法字符");
            }
        }
        return list;
    }
    /**
     * 将中序表达式转换成逆波兰表达式
     * 1. 声明两个栈: 运算符栈s1和存放中间结果栈s2
     * 2. 从左只有扫描中序表达式
     * 3.遇到操作数时，将其压入s2
     * 4. 遇到运算符时，比较其与s1栈顶运算符的优先级
     *  4.1 如果s1为空，或者栈顶运算符为(，直接入栈
     *  4.2 否则，若优先级比栈顶运算符优先级高，也直接入栈
     *  4.3 否则，将s1栈顶运算符弹出，并压入到s2中，再次回到4.1，与s1新的栈顶运算符比较
     * 5. 遇到括号时:
     *  5.1 如果括号是左括号，直接压入s1
     *  5.2 如果括号是右括号，则依次弹出s1的栈顶运算符，并且压入s2，直到遇到左括号为止，
     *      此时将这一对括号丢弃
     * 6. 重复步骤2~5，直到表达式的最右边
     * 7. 将s1中剩余的运算符弹出并压入s2
     * 8. 一次弹出s2中的元素，结果的逆序就是中缀表达式对应的后缀表达式。
     */
    public static List convertMiddleToBack(List<String> middle) {
        //1. 声明两个栈: 运算符栈s1和存放中间结果栈s2
        Stack<String> s1 = new Stack();
        Stack<String> s2 = new Stack();
        //s1栈顶元素
        String top1;
        //2. 从左只有扫描中序表达式
        for (String s : middle) {
            //如果是数字
            if (s.charAt(0) >= 48 && s.charAt(0) <= 57) {
                s2.push(s);
            } else if (s.equals("(") || s.equals(")"))
            //如果是括号
            {
                if (s.equals("(")) {
                    s1.push(s);
                } else {
                    while (!(s1.peek().equals("("))) {
                        s2.push(s1.pop());
                        if(s1.size() == 0) break;
                    }
                    if(s1.size() != 0) s1.pop();
                }
            } else
            //如果是操作符
                {
                do {
                    if (s1.size() == 0 || s1.peek().equals("(")) {
                        s1.push(s);
                        break;
                    } else if ((s.equals("*") || s.equals("/")) &&
                            (s1.peek().equals("+") || s1.peek().equals("-"))) {
                        s1.push(s);
                        break;
                    } else {
                        s2.push(s1.pop());
                    }
                } while (true);
            }
        }
        //7. 将s1中剩余的运算符弹出并压入s2
        if(s1.size() != 0) {
            for (String s : s1) {
                s2.push(s);
            }
        }
        //8. 一次弹出s2中的元素，结果的逆序就是中缀表达式对应的后缀表达式。
        List<String> after = new ArrayList<>();
        //stack用for each遍历时，是从栈底到栈顶遍历，所以产生的list包含的元素就是我们需要的顺序，不需要再做反转了。
        for (String s : s2) {
            after.add(s);
        }
        return after;
    }
    /**
     * 根据传入的后续表达式列表，计算出表达式结果并返回
     */
    public static int calc(List<String> elements) {
//            Stack<String> operatorStack = new Stack();
        //没有必要做如下判断，编译器会帮我们检查类型是否合法。除非使用泛型？
//        if (!(elements instanceof String[])) throw new IllegalArgumentException("必须传入一个String数组");
        Stack<Integer> operandStack = new Stack();
        int operand1;
        int operand2;
        for (int i = 0; i < elements.size(); i++) {
            //运算符，取出两个操作数，计算结果并将结果入栈
            if (elements.get(i).equals("+")) {
                operandStack.push(operandStack.pop() + operandStack.pop());
            } else if (elements.get(i).equals("-")) {
                operand1 = operandStack.pop();
                operand2 = operandStack.pop();
                operandStack.push(operand2 - operand1);
            } else if (elements.get(i).equals("*")) {
                operandStack.push(operandStack.pop() * operandStack.pop());
            } else if (elements.get(i).equals("/")) {
                operand1 = operandStack.pop();
                operand2 = operandStack.pop();
                operandStack.push(operand2 / operand1);
            } else {
                //操作数，入栈
                operandStack.push(Integer.parseInt(elements.get(i)));
            }
        }
        return operandStack.pop();
    }
}
