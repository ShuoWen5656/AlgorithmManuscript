package classespackage.stringproblem;

import classespackage.stackAndQueue.catDogQueue.Pet;
import classespackage.tree.PrintTreeDirect;
import javafx.scene.input.InputMethodTextRun;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author swzhao
 * @data 2022/5/16 21:32
 * @Discreption <> 公式字符串求值
 * 给定公式字符串1+（2+3）+4 求出结果
 */
public class FormulaStrGetNum {

    /**
     * 主方法
     * @param str
     * @return
     */
    public static int getValue(String str){
        if(str == null || str.length() == 0){
            return 0;
        }
        return value(str.toCharArray(), 0)[0];

    }

    /**
     * 通过递归方式计算结果
     * @param chars
     * @param i
     * @return int[0]为从i开始到遇到“）”或者遍历结束后的计算结果，int[1]为当前遍历的位置
     */
    private static int[] value(char[] chars, int i) {
        // 公式队列，如 3+4+，如果遇到乘除法，则会计算后将结果放入
        Deque<String> dq = new LinkedList<>();
        // 将char类型的连续数字字符转成int
        int pre = 0;
        // 保存递归结果的变量
        int[] bra = null;
        while (i < chars.length && chars[i] != ')'){
            if(chars[i] >= '0' && chars[i] <= '9'){
                // 数字,更新数值
                pre = pre * 10 + chars[i] - '0';
            }else if(chars[i] != '('){
                // 符号,将数据加入dq中，判断是否计算，完成后将符号再加入dq中
                addNum(dq, pre);
                dq.addLast(String.valueOf(chars[i]));
                pre = 0;
            }else{
                // ‘（’,递归从i+1开始计算新值
                bra = value(chars, i+1);
                // 将递归结果给pre
                pre = bra[0];
                // 将新的index复制给i
                i = bra[1];
            }
        }
        // 将最后的值加入到dq中
        addNum(dq, pre);
        return new int[]{getNum(dq), i};
    }


    /**
     * 计算结果，如果是乘法可以先计算出结果，如果是+-法，放入并继续
     * @param dq
     * @param pre
     */
    private static void addNum(Deque<String> dq, int pre) {
        int num = pre;
        int cur = 0;
        // 如果为空，先放入该数值即可
        if(!dq.isEmpty()){
            // 获取符号（最后一位）
            String top = dq.pollLast();
            if(top == "-" || top == "+"){
                // 放回去先不计算
                dq.addLast(top);
            }else{
                // 乘除法需要计算出结果再放入
                cur = Integer.valueOf(dq.pollLast());
                num = top == "*" ? cur * pre : cur/pre;
            }
        }
        // 最后将计算结果放入最后面
        dq.addLast(String.valueOf(num));
    }


    /**
     * dq最后只剩下加减法，顺序计算即可
     * @param dq
     * @return
     */
    private static int getNum(Deque<String> dq) {
        // 符号表示
        boolean add = true;
        // 数字类型时用于转换
        int cur = 0;
        // 结果
        int res = 0;
        while (!dq.isEmpty()){
            String first = dq.pollFirst();
            if(first.equals("-")){
                add = false;
            }else if(first.equals("+")){
                add = true;
            }else{
                // 数字,将结果加或者减掉当前值。
                cur = Integer.valueOf(first);
                res = add? res + cur : res - cur;
            }
        }
        return res;
    }

    /**
     * 二轮测试：计算字符串代表的值
     * @param str
     * @return
     */
    public static Integer getNumCp1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] chars = str.toCharArray();
        return valueCp1(chars, 0)[0];

    }

    private static Integer[] valueCp1(char[] chars, int start) {
        // 保存公式队列
        Deque<String> dq = new LinkedList<>();
        int index = start;
        int cur = 0;
        while (index < chars.length && chars[index] != ')') {
            if (chars[index] - '0' >= 0 && chars[index] - '0' <= 9) {
                // 数值
                cur = cur * 10 + chars[index] - '0';
                index++;
            }else if (chars[index] != '(') {
                // 不会触发递归的符号类
                addNumCp1(dq, cur);
                // 加符号
                dq.addLast(String.valueOf(chars[index]));
                cur = 0;
                index++;
            }else {
                // 触发递归
                Integer[] integers = valueCp1(chars, index + 1);
                cur = integers[0];
                index = integers[1]+1;
            }

        }
        addNumCp1(dq, cur);
        // 到这里全部变成只有加减的顺序执行串了
        return new Integer[] {calc2(dq), index};
    }

    /**
     * 添加当前字符到队列
     * @param dq
     * @param cur
     */
    private static void addNumCp1(Deque<String> dq, int cur) {
        if (dq.isEmpty() || dq.getLast().equals("+") || dq.getLast().equals("-")) {
            dq.addLast(String.valueOf(cur));
            return;
        }
        String poll1 = dq.pollLast();
        Integer poll2 = Integer.parseInt(dq.pollLast());
        if (poll1.equals("*")) {
            dq.addLast(String.valueOf(poll2*cur));
        }
        if (poll1.equals("/")) {
            dq.addLast(String.valueOf(poll2/cur));
        }
    }

    private static int calc2(Deque<String> dq) {
         boolean add = true;
         int cur = 0;
         int res = 0;
         while (!dq.isEmpty()){
             String s = dq.pollFirst();
             if (s.equals("+")) {
                 add = true;
             }else if (s.equals("-")) {
                 add = false;
             }else {
                 res = add? res + Integer.parseInt(s) : res - Integer.parseInt(s);
             }
         }
         return res;
    }

    public static void main(String[] args) {
        System.out.println(getNumCp1("3*(4+5)+7"));
    }


    ///**
    // * 二轮测试：计算字符串代表的值
    // * @param str
    // * @return
    // */
    //public static Integer getNumCp1(String str) {
    //    if (str == null || str.length() == 0) {
    //        return null;
    //    }
    //    char[] chars = str.toCharArray();
    //    // 主
    //    Deque<Character> dq1 = new LinkedList<>();
    //    // 备用
    //    Deque<Character> dq2 = new LinkedList<>();
    //    // 1、第一轮先去掉括号，使用栈的特性
    //    for (int i = 0; i < chars.length; i++) {
    //        if (chars[i] == ')') {
    //            // 计算到上一个'（'内的值
    //            char ch;
    //            while ((ch = dq1.pollFirst()) != '(') {
    //                dq2.addLast(ch);
    //            }
    //            //计算dp2中的公式的值
    //            int v = calc(dq2);
    //            add2Dq(v, dq1);
    //        }else {
    //            dq1.addFirst(chars[i]);
    //        }
    //    }
    //    // 2、最后按照正常优先级计算即可
    //    return calc(dq1);
    //}
    //
    //private static void add2Dq(int v, Deque<Character> dq1) {
    //    char[] chars = null;
    //    chars = String.valueOf(v).toCharArray();
    //    if (v < 0) {
    //        dq1.addFirst('(');
    //    }
    //    for (int i = 0; i < chars.length; i ++) {
    //        dq1.addFirst(chars[i]);
    //    }
    //    dq1.addFirst(')');
    //}
    //
    ///**
    // * 计算队列中的值
    // * @param
    // * @return
    // */
    //private static int calc(Deque<Character> dq) {
    //    // 辅助队列
    //    Deque<Character> dqCp = new LinkedList<>();
    //    Character c;
    //    int cur = 0;
    //    // 符号
    //    boolean pos = true;
    //    if((c = dq.pollLast()) == '-') {
    //        // 开头没有括号的负数
    //        pos = false;
    //    }
    //    while (!dq.isEmpty()) {
    //        char ct = dq.pollLast();
    //        if (ct - '0' >= 0 && ct - '0' <= 9) {
    //            // 数值直接计算
    //            cur = cur * 10 + (ct - '0');
    //        }else if (ct == '(' && dq.pollLast() == '-') {
    //            // 负数
    //            pos = false;
    //        }else {
    //            // 符号
    //            if (dqCp.isEmpty() || dqCp.getFirst() == '+' || dqCp.getFirst() == '-') {
    //                // 空的直接加
    //                add2Dq(cur, dqCp);
    //            }else {
    //                // 优先计算
    //                char ci = dqCp.pollFirst();
    //                int cur2 = 0;
    //                while (dqCp.getFirst() - '0' >= 0 && dqCp.getFirst() - '0' <= 9) {
    //                    cur2 += cur2 * 10 + (dqCp.pollFirst() - '0');
    //                }
    //                int resTmp = ci == '*' ? cur2 * cur : cur2 / cur;
    //                add2Dq(resTmp, dqCp);
    //            }
    //            dqCp.addFirst(ct);
    //        }
    //    }
    //    // 最后顺序计算
    //    int res = 0;
    //    cur = 0;
    //    char ci = 0;
    //    while (!dqCp.isEmpty()) {
    //
    //        if (dqCp.pollLast() == '(' && dqCp.pollLast() == '-') {
    //            pos = false;
    //        }else{
    //            char cx = dqCp.pollLast();
    //            if (cx - '0' >= 0 && cx - '0' <= 9) {
    //                cur = cur * 10 + (cx - '0');
    //            }else {
    //                ci = cx;
    //            }
    //        }
    //        if (ci != 0) {
    //            cur = pos ? cur : -cur;
    //            res += ci == '+' ? cur : -cur;
    //        }
    //    }
    //    return res;
    //}
















}
