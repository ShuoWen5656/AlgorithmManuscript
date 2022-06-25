package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/6/24 21:16
 * @Discreption <> 数组的partition调整
 */
public class ArrayPartitionMove {

    /**
     * 问题一：将有序数组变成右半边不重复且有序，左边不管
     * @param array
     */
    public static void partitionMove1(int[] array){
        try {
            if (array == null || array.length < 2){
                return;
            }
            // u为当前有序不重复序列的最后一个元素
            int u = 0;
            // i为游标
            int i = 1;
            while (i < array.length){
                // 如果等于，则i懂，u不懂，如果不等于，则将i位置和u+1的位置互换，i++
                if (array[i++] != array[u]){
                    CommonUtils.swap(array, i-1, ++u);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 问题二：将0,1,2组成的数组进行排序，0,1,2变成有序如0000,1111,22222
     * 变种：将红黄蓝色的球进行分类，顺序为红黄蓝
     * @param array
     */
    public static void partitionMove2(int[] array){
        try {
            if (array == null || array.length < 2){
                return;
            }
            int left = -1;
            int right = array.length;
            int index = 1;
            while (index != right){
                if (array[index] == 0){
                    // 应该在左边
                    CommonUtils.swap(array, ++left, index);
                }else if (array[index] == 1) {
                    // 应该在中间
                    index++;
                }else {
                    // 应该在右边
                    CommonUtils.swap(array, --right, index);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }










    //写日报!!!!!!!!!!

}
