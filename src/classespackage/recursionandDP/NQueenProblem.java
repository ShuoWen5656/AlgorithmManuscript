package classespackage.recursionandDP;

/**
 * @author swzhao
 * @data 2022/5/3 9:54
 * @Discreption <>
 */
public class NQueenProblem {


    /**
     * n皇后问题递归方法
     * 1、逐行进行递归
     *      · 后面的不能和前面的同行（递归可以保证）
     *      · 后面的不能和前面的同列，递归计算当前行的列是否被占用
     *      · 后面的不能和前面的同斜线，递归计算当前位置（a,b）是否满足|a-i| == |b-i|，如果等于，则当前位置和record中的当前位置在一个斜线上
     * @param num
     * @return
     */
    public static int getNum1(int num){
        try{
            if(num == 1){
                return 1;
            }else if (num == 2 || num == 3){
                return 0;
            }
            // 临时变量，index为行号，value为列号，表示该行占用了第几列
            int[] record = new int[num];
            for (int i = 0; i < record.length; i++){
                record[i] = -1;
            }
            return process1(0, record, num);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private static int process1(int i, int[] record, int num) {
        if(i >= num){
            // 行数便利完成
            return 1;
        }
        // 代表当前行的每一列是否能够放置，放置后，下面几行的总数之和是多少
        int res = 0;
        // 遍历每一列是否能够填充
        for (int j = 0; j < num; j++){
            // 判断列是否符合条件能够放置当前皇后
            if(isValid(i, record, j)){
                // 这行j占用了
                record[i] = j;
                res += process1(i+1, record, num);
            }
        }
        return res;
    }

    private static boolean isValid(int i, int[] record, int j) {
        for (int k = 0; k < i; k++){
            // 是否同列
            if(record[k] == j){
                return false;
            }
            // 是否同斜线,每一行占用的(k, k2)
            int k2 = record[k];
            if(Math.abs(i-k) == Math.abs(j-k2)){
                return false;
            }
        }
        return true;
    }


    /**
     * 方法二：
     * 递归位运算
     * 这里用int类型最多能够计算32皇后问题
     * @param num
     * @return
     */
    public static int getNum2(int num){
        try {
            if(num == 1){
                return 1;
            }else if (num == 2 || num == 3 || num > 32){
                return 0;
            }
            // 初始化常量位，1表示可以放置皇后，0表示不能放置皇后，-1为补码，32个1
            int upperLim = num == 32 ? -1 : (1 << num) - 1;
            return process2(upperLim, 0, 0, 0);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 递归方法
     * @param upperLim 1表示可以放置皇后，0表示不能放置
     * @param colLim 1表示已经放置皇后，0表示没有放置皇后
     * @param leftLim 1表示截至上一波，当前行在左斜线上已经有皇后的位置， 0表示截至上一波，当前行在左斜线上没有皇后的位置
     * @param rightLim 1表示截至上一波，当前行在右边斜线上已经有皇后的位置，0表示截至上一波，当前行在右斜线上没有皇后的位置
     * “<<”计算补位根据当前数的正负来走，负数补位1，正数补位0，“<<<”不管正负都补位0
     * @return
     */
    private static int process2(int upperLim, int colLim, int leftLim, int rightLim) {
        if(upperLim == colLim){
            // 当前列已经沾满了，直接返回
            return 1;
        }
        int pos = 0;
        int mostRightOne = 0;
        // 1表示能放置，0表示不能放置
        pos = upperLim & ~(colLim | leftLim | rightLim);
        // pos的最右边的1所在位置
        mostRightOne = pos & (~pos + 1);
        // 结果
        int res = 0;
        while (pos != 0){
            // 更新最右边第一个1的位置
            mostRightOne = pos & (~pos + 1);
            // 将最右边的1占用
            pos -= mostRightOne;
            // colLim的mostRightOne位置被占用,leftLim的mostRightOne位置被占用，并且对于下一次递归应该左移一位，
            // rightLim的mostRightOne位置被占用，并且对于下一次递归应该右移一位，并且补位应该强制为0，脱离符号位影响，所以使用>>>
            res += process2(upperLim, colLim | mostRightOne, leftLim|mostRightOne << 1, rightLim|mostRightOne>>>1);
        }
        return res;
    }


}
