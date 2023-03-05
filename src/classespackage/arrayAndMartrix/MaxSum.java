package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/17 21:52
 * @Discreption <> 子数组的最大累加和问题
 * 题目二：子矩阵的最大累加和问题
 */
public class MaxSum {


    /**
     * 主方法
     * @param array
     * @return
     */
    public static int maxSum(int[] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            // 最大值结果
            int max = Integer.MIN_VALUE;
            // 当前候选数组相加值，可能比max小，如果比max小但是大于0，说明待定，可能后面有更大的数可以超过max
            // 如果比max大，则更新max，如果小于0，说明除非当前值是最大的负数，否则结果不可能包含当前值
            int cur = 0;
            for (int i = 0; i < array.length; i++){
                cur += array[i];
                max = Math.max(max, cur);
                cur = cur > 0 ? cur : 0;
            }
            return max;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 子矩阵的最大累加和问题
     * 从一第行开始往下将每一行相加，然后求和矩阵的最大累加和即可，需要循环求每一行开始的累加和
     * @param array
     * @return
     */
    public static int maxSumForMartrix(int[][] array){
        try {
            if (array == null || array.length == 0 || array[0].length == 0){
                return 0;
            }
            int row = array.length;
            int col = array[0].length;
            // 解释同上
            int max = Integer.MIN_VALUE;
            int cur = 0;
            // 求和数组
            int[] sumArray = null;
            for (int i = 0; i < row; i++){
                // 从i行开始往下加,先初始化求和数组
                sumArray = new int[col];
                for (int j = i; j < row; j++){
                    // 更换矩阵的时候cur需要清零
                    cur = 0;
                    // j为游标,当前应该累加j行
                    for (int k = 0; k < col; k++){
                        sumArray[k] = sumArray[k] + array[j][k];
                        cur += sumArray[k];
                        max = Math.max(max, cur);
                        cur = cur > 0 ? cur : 0;
                    }
                }
            }
            return max;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：子数组的最大累加和问题
     * @param arr
     * @return
     */
    public static int maxSumCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int cur = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            // 首先cur不会小于0，如果小于说明当前加了一个负数
            cur = cur <= 0 ? 0 : cur;
        }
        return max;
    }


    /**
     * 矩阵的最大累加和子矩阵
     * 行级别还是暴力循环
     * @param arr
     * @return
     */
    public static int maxMatrixSumCp1(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        // 长度为列数
        int[] sum = null;
        int max = Integer.MIN_VALUE;
        // 每一行开始
        for (int i = 0; i < arr.length; i++) {
            sum = new int[arr[0].length];
            // 往下每一行开始
            for (int i1 = i; i1 < arr.length; i ++) {
                // 这里在sum相加的时候嵌入求子数组小和逻辑少一次循环
                int cur = 0;
                // 先累加
                for (int j = 0; j < arr[0].length; i++) {
                    sum[j] += arr[i1][j];
                    cur += sum[j];
                    max = Math.max(max, cur);
                    cur = cur <= 0 ? 0 : cur;
                }
                // 加完以后求一次最大累加和
            }
        }
        return max;
    }

    public static void main(String[] args) {
        //System.out.println(maxSumCp1(new int[]{1,-2,3,5,-2,6,-1}));
        System.out.println(maxSumForMartrix(new int[][]{
                {-90, 48, 78},
                {64, -40, 64},
                {-81, -7, 66}
        }));


    }


}
