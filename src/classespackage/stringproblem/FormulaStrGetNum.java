package classespackage.stringproblem;

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


}
