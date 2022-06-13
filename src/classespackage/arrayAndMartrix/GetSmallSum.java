package classespackage.arrayAndMartrix;

import java.util.Arrays;

/**
 * @author swzhao
 * @data 2022/6/12 9:25
 * @Discreption <> 计算数组的小和
 */
public class GetSmallSum {

    /**
     * 主方法:
     * 归并方式计算
     * @param array
     * @return
     */
    public static int getSmallSun(int[] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            return func(Arrays.copyOf(array, array.length), 0, array.length-1);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 递归，主要返回当前层的小和
     * @param array
     * @param left
     * @param right
     * @return
     */
    private static int func(int[] array, int left, int right) {
        if (left == right){
            // 当前层小和为0
            return 0;
        }
        // 取中间值,偶数取后面的
        int mid = (left + right) / 2 + 1;
        //左组小和，右组小和，组间小和
        return func(array, left, mid) + func(array, mid+1, right) + merge(array, left, mid, right);

    }

    /**
     * 计算[left-mid]和[mid+1-right]之间的小和
     * @param array
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private static int merge(int[] array, int left, int mid, int right) {
        // 复制一份左右起点索引
        int i = left;
        int j = mid;
        // 创建一个容器装当前left-right的从小到达排序结果
        int[] contains = new int[right - left + 1];
        // 容器索引
        int index = 0;
        // 记录小和
        int smallSum = 0;
        while (i < mid+1 && j < right+1){
            if(array[i] < array[j]){
                // 此时array的j后面都比j大，所以如果计算小和j后面有几个数，i就会被加几次
                smallSum += array[i] * (right-j+1);
                contains[index++] = array[i];
                i++;
            }else {
                // 左边大说明相对于j当前值，i一次都不用加
                contains[index++] = array[j];
                j++;
            }
        }
        // 将剩下的部分再装进容器
        while (i < mid+1 || j < right+1){
            contains[index++] = i < mid+1? array[i++] : array[j++];
        }
        // 将排序结果写入array中提供给接下来的递归使用
        index = 0;
        while (index < contains.length){
            array[left++] = contains[index++];
        }
        return smallSum;
    }


}
