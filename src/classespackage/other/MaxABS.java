package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/7 21:31
 * @Discreption <> 最大的leftMax与rightMax之差的绝对值
 * 问题：给定一个数组，求某一次分割的两个子数组的最大值之差最大
 */
public class MaxABS {


    /**
     * 方法一：略，从左边到右边遍历，求两边的最大值，进行更新差值最大值即可
     * 时间：O（n^2）
     * @param array
     * @return
     */
    public static int maxABS1(int[] array){
        try {
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 方法二：
     * 时间O(n) 空间O(n)
     * @param array
     * @return
     */
    public static int maxABS2(int[] array){
        try {
            // 左右两个容器分别存储当k到i的地方，两边的最值
            int[] intl = new int[array.length];
            int[] intr = new int[array.length];
            intl[0] = array[0];
            intr[array.length-1] = array[array.length-1];
            // 从左到右更新左边的最大值
            for (int i = 1; i < array.length-1; i++){
                // 新成员大于原来子数组的最大值
                intl[i] = Math.max(intl[i-1], array[i]);
            }
            for (int i = array.length-2; i > -1; i--){
                intr[i] = Math.max(intr[i+1], array[i]);
            }
            int res = 0;
            // 注意，相减的时候intr需要错位
            for (int i = 0; i < intl.length; i++){
                res = Math.max(res, Math.abs(intl[i] - intr[i+1]));
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 方法三：最优解
     * 时间O（n），空间O(1)
     * 原理：最大值一定是结果中的被减数
     * 也就是说一定会是 max - 某个值 为最终结果，问题是求某个值x的过程
     * 首先，加入max在左边，则右边从头开始，进来一个新成员时，新成员大于array[array.length-1]的话，结果坑定不是max-新成员
     * 如果小于，则没用，最大值之间的减法小值不参与，
     * 因此，最终只需要判断两端中的最小值就是减数x！
     * @param array
     * @return
     */
    public static int maxABS3(int[] array){
        try {
            int max = Integer.MIN_VALUE;
            // 先求最大值
            for (int i = 0; i < array.length; i++){
                if (array[i] > max){
                    max = array[i];
                }
            }
            return max - Math.min(array[0], array[array.length-1]);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}
