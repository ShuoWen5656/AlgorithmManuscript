package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

import java.util.Arrays;

/**
 * @author swzhao
 * @data 2022/6/8 21:22
 * @Discreption <> 找到无序数组中最小的k个数
 * 整体时间：O（nlogn）
 */
public class GetMinKNums {

    public static int[] getMinK1(int[] array, int k){
        try {
            if (array == null || array.length <= k){
                return array;
            }
            // 保存前k个最小值
            int[] res = new int[k];
            // 先扔进去k个数字
            for (int i = 0; i < k; i++){
                res[i] = array[i];
            }
            // 第一次进行大根堆的调整
            maxNumHeap(res);
            for (int j = k; j < array.length - 1; j++){
                if(array[j] < res[0]){
                    res[0] = array[j];
                    maxNumHeap(res);
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return new int[0];
        }
    }


    /**
     * 重新整理大根堆
     * @param res
     * @return
     */
    public static void maxNumHeap(int[] res){
        int len = res.length;
        // 从倒数第一个非子节点开始往回走
        for (int i = len/2 - 1; i >= 0; i--){
            processHeap(res, i);
        }
    }

    public static void processHeap(int[] res, int index){
        int tem = res[index];
        int parent = index;
        // 左孩子节点索引
        int lcIndex = index * 2 + 1;
        // 只要不是孩子节点就继续往下遍历
        while (lcIndex < res.length){
            int child = res[lcIndex];
            // 判断右孩子是否比左孩子小
            if(lcIndex+1 < res.length && res[lcIndex+1] < res[lcIndex]){
                child = res[lcIndex+1];
                // 转移到右节点
                lcIndex++;
            }
            // 判断是否父节点需要下沉
            if(res[parent] < child){
                res[parent] = child;
            }
            // 父节点更新
            parent = lcIndex;
            // lcIndex变成自己的左孩子
            lcIndex = lcIndex*2 + 1;
        }
        // 结束以后lcIndex 停留在
        res[parent] = tem;
    }

    /**
     * 方法二：
     * 时间O（BfPrt算法）
     * @param array
     * @param k
     * @return
     */
    public static int[] getMinK2(int[] array, int k){
        try {
            if (array == null || array.length <= k){
                return array;
            }
            int[] res = new int[k];
            int index = 0;
            int minK = BFPRT(array, k);
            for (int i = 0; i < array.length - 1; i++){
                if(array[i] < minK){
                    res[index++] = array[i];
                }
            }
            // 可能会有相等的数，并且长度只能是k的时候
            for (; index < res.length; ){
                res[index++] = minK;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return array;
        }
    }

    /**
     * BFPart算法
     * 返回array中第k个小的数
     * @param array
     * @param k
     * @return
     */
    private static int BFPRT(int[] array, int k) {
        // 复制一份
        int[] newArray = Arrays.copyOf(array, array.length);
        // 找到第k个小的数x
        int x = select(newArray, 0, newArray.length-1, k-1);
        return x;
    }

    /**
     * 获取第k个小的数
     * @param newArray
     * @param start
     * @param end
     * @param i
     * @return
     */
    private static int select(int[] newArray, int start, int end, int i) {
        // 只剩下一个
        if(end == start){
            return newArray[start];
        }
        int pivot = medianOfMedian(newArray, start, end);
        int[] pivotRange = partition(newArray, start, end ,pivot);
        if(i >= pivotRange[0] && i <= pivotRange[1]){
            return newArray[i];
        }else if(i < pivotRange[0]){
            // 前半段继续递归
            return select(newArray, start, pivotRange[0] - 1, i);
        }else {
            // 后半段继续递归
            return select(newArray, pivotRange[i] + 1, end, i);
        }
    }


    /**
     * 获取中位数的中位数
     * @param newArray
     * @param start
     * @param end
     * @return
     */
    private static int medianOfMedian(int[] newArray, int start, int end) {
        // 长度
        int num = start - end + 1;
        // 是否有余数，有的话需要多一个数组空间
        int offset = num%5 == 0? 0: 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++){
            int beginI = start + i * 5;
            int endI = beginI+4;
            // Math.min(endI, end)的意思是如果最后一个数组不够5位就按照end作为最后一个数
            mArr[i] = getMedian(newArray, beginI, Math.min(endI, end));
        }
        // 获取mArr的中位数
        return select(mArr, 0, mArr.length-1, mArr.length/2);
    }

    /**
     * 获取中位数
     * @param newArray
     * @param beginI
     * @param endI
     * @return
     */
    private static int getMedian(int[] newArray, int beginI, int endI) {
        // 插入排序后将中间数取出来
        for (int i = beginI; i < endI; i++){
            for (int j = i; j < endI; j++){
                if(newArray[j] < newArray[i]){
                    int tem = newArray[j];
                    newArray[j] = newArray[i];
                    newArray[i] = tem;
                }
            }
        }
        // 返回后中间值
        return newArray[endI+beginI/2+1];
    }


    /**
     * 按照pivot的值将newArray进行左右划分
     * @param newArray
     * @param start
     * @param end
     * @param pivot
     * @return 返回int[0]和int[1]是pivot所在位置,如果pivot不止一个，0在左边，1在右边
     */
    private static int[] partition(int[] newArray, int start, int end, int pivot) {
        int small = start-1;
        int big = end + 1;
        int cur = start;
        while (cur != big){
            if(newArray[cur] < pivot){
                // 小的放左边
                CommonUtils.swap(newArray, ++small, cur++);
            }else if (newArray[cur] > pivot){
                CommonUtils.swap(newArray, cur, --big);
            }else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small+1;
        range[1] = big-1;
        return range;
    }



}
