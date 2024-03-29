package classespackage.arrayAndMartrix;

import java.util.Collections;

/**
 * @author swzhao
 * @data 2022/6/20 19:56
 * @Discreption <> 数组中子数组的最大累乘积
 */
public class GetMaxMulti {

    /**
     * 计算以每一个数结尾的最大乘积
     * @param array
     * @return
     */
    public static double getMax(double[] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            // max为以i结尾的最大值，min为以i结尾的最小值
            double max = array[0], min = array[0];
            // 结果
            double res = array[0];
            // 最大值*array[i]，以array[i]结尾的最值，但不是最大值
            double maxEnd = 0;
            // 最大值*array[i]，以array[i]结尾的最值，但不是最小值
            double minEnd = 0;
            for (int i = 0; i < array.length; i++){
                maxEnd = max * array[i];
                minEnd = min * array[i];
                // 最值可能是自己，比如[0.1, 0.1, 100]
                max = Math.max(Math.max(minEnd, maxEnd), array[i]);
                min = Math.min(Math.min(minEnd, maxEnd), array[i]);
                res = Math.max(res, max);
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：获取最大的累乘数组
     * @param arr
     * @return
     */
    public static double getMaxMulti(double[] arr) {
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("invalid arr");
        }
        double max = arr[0];
        double min = arr[0];
        double res = arr[0];
        for (int i = 1; i < arr.length; i ++) {
            double curMax = arr[i] * max;
            double curMin = arr[i] * min;
            max = Math.max(arr[i], Math.max(curMax, curMin));
            min = Math.min(arr[i], Math.min(curMax, curMin));
            res = Math.max(res, max);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(getMaxMulti(new double[]{0.1, 0.1, 100}));
    }

}
