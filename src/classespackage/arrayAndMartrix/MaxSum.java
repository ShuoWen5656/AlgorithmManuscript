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

}
