package classespackage;

import javax.swing.*;
import java.util.*;

/**
 * @Author swzhao
 * @Date 2022/2/13 20:12
 * @Discription<>
 *     1、原地排序：不需要申请多余的存储空间
 *
 */
public class SortAlgorithm {


    /**
     * 选择排序算法：目前最通用的排序想法，时间O（n²），空间O(1),非稳定排序，原地排序
     * @param array
     */
    public static int[] selectSort(int [] array, byte order){
        // 最大值或最小值index,默认顺序
        int boundaryNum = 0;
        // 双重循环，每次都选择最小的或者最大的排进去
        for (int i = 0; i < array.length; i++){
            // 每一次大循环都会选出一个最小的/最大的替换当前指针
            for (int j = i; j <= array.length; j++){
                // i之前的都已经排好了，直接i之后开始，选最小值
                if(order == Constants.ORDER_ASC){
                    if (array[j] <= array[boundaryNum]){
                        boundaryNum = j;
                    }
                }else{
                    if(array[j] >= array[boundaryNum]){
                        boundaryNum = j;
                    }
                }
            }
            // 替换i
            int tem = array[i];
            array[i] = array[boundaryNum];
            array[boundaryNum] = tem;
        }
        return array;
    }


    /**
     * 选择排序
     * @param arr
     * @return
     */
    public static int[] selectSortCP(int[] arr){
        if (arr == null || arr.length == 1){
            return arr;
        }
        for (int i = 0; i < arr.length; i++){
            // 默认当前最大
            int maxIndex = i;
            for (int j = i; j < arr.length; j++){
                if (arr[j] > arr[maxIndex]){
                    maxIndex = j;
                }
            }
            // 交换maxIndex和i
            CommonUtils.swap(arr, i, maxIndex);
        }
        return arr;
    }



    /**
     * 插入排序:时间O(n²) 空间O(1) 、稳定排序、原地排序
     * 插入和冒泡排序不同的地方就是，插入排序往上插，一定有插入动作，不是交换，冒泡往下“沉”，一定有交换动作
     * @param array
     * @param order
     * @return
     */
    public static int[] InsertSort(int[] array, byte order){
        // 从第二个元素开始，往前比，顺序时，比当前大的往后稍
        int temp = 0;
        for (int i = 1; i < array.length; i++){
            for (int j = i-1; j >= 0; j --){
                if(order == Constants.ORDER_DESC){
                    if (array[j] >= array[i]){
                        // 遇到第一个比自己大的,插入j后面
                        temp = j;
                        break;
                    }
                }else{
                    if(array[j] <= array[i]){
                        // 遇到第一个比自己小的，插入j后面
                        temp = j;
                        break;
                    }
                }
                int swap = array[i];
                int k = i;
                // 插入到temp后面,先往后移动一格
                for (; k > temp; k--){
                    array[k] = array[k-1];
                }
                // 将swap放到temp后面
                array[temp+1] = swap;
            }
        }
        return array;
    }


    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static int[] insertSortCP(int[] arr){
        if (arr == null || arr.length == 1){
            return arr;
        }
        for (int i = 1; i < arr.length; i++){
            // 查询i应该放在哪？
            int index = i;
            int value = arr[i];
            // 往前找到第一个小于index的坐标
            while (index >= 0 && arr[index] >= arr[i]){
                index--;
            }
            // index可能是-1
            index++;
            int temIndex = i;
            while (temIndex > index){
                arr[temIndex] = arr[temIndex-1];
                temIndex--;
            }
            arr[index] = value;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 5, 6, 8, 9};
        int[] ints = InsertSort(arr, (byte) 0);
        CommonUtils.printArr(ints);
    }

    /**
     * 冒泡排序：时间O(n²)、空间O(1), 稳定，原地
     * 优化，如果中间某一次一次交换都没有，则该序列已经有顺序了，直接返回即可
     * @param array
     * @param order
     * @return
     */
    public static int[] BubbleSort(int[] array, byte order){
        for (int i = 0; i < array.length; i++){
            // 每一次期望没有swap
            boolean isSwap = false;
            for (int j = 0; j < array.length - i; j ++){
                if(order == Constants.ORDER_DESC){
                    // 倒叙：当前比后面的小，则交换,注意数组越界
                    if(j+1 != array.length && array[j] < array[j+1]){
                        // 交换
                        CommonUtils.swap(array, j, j+1);
                        isSwap = true;
                    }
                }else{
                    // 顺序：当前比后面大，交换
                    if(j+1 != array.length && array[j] > array[j+1]){
                        // 交换
                        CommonUtils.swap(array, j, j+1);
                        isSwap = true;
                    }
                }
            }
            // 判断本轮循环是否交换了，如果没有交换，说明已经拍好了
            if(!isSwap){
                return array;
            }
        }
        return array;
    }

    /**
     * 希尔排序：时间O(nlogn) 空间 O（1），不稳定、原地
     * 首选间隔数量 length/2 之后间隔元素之间进行插入排序
     * @param array
     * @param order
     * @return
     */
    public static int[] shellSort(int[] array, byte order){
        if(array == null || array.length < 2) return array;
        int length = array.length;
        // 一共循环logh
        for (int h = length/2; h > 0; h /= 2){
            // h是每一次/2后的分组内的第二个元素
            for (int i = h; i < length; i++){
                // 对每一个分组进行插入排序,注意这里不是直接排一个分组，是一个个分组轮流排一下
                insertSortForGroup(array, h, i, order);
            }
        }
        return array;
    }


    /**
     * 对每一个分组进行排序(从后往前)
     * @param array
     * @param h 间隔
     * @param i 分组的第二个元素
     */
    public static void insertSortForGroup(int[] array, int h, int i, byte order){
        // 将当前元素拿出来
        int temp = array[i];
        int k = 0;
        if(order == Constants.ORDER_ASC){
            // 起点在前一个元素
            for (k = i - h; k > 0 && temp < array[k]; k -= h){
                // 往后稍一个
                array[k + h] = array[k];
            }
        }else if(order == Constants.ORDER_DESC){
            // 起点在前一个元素
            for (k = i - h; k > 0 && temp > array[k]; k -= h){
                // 往后稍一个
                array[k + h] = array[k];
            }
        }

        // 这里解释一下，当往后稍一个后，k会自动减少h，到这里时多减少一个h
        array[k + h] = temp;
    }


    /**
     * 归并排序：递归归并，将数据二分若干份，合并排序
     * 时间 O(nlogn) 空间O(n) 稳定排序、非原地排序
     * @param array
     * @param order
     */
    public static int[] mergeSort(int[] array, byte order){
        int left = 0;
        int right = array.length -1;
        mergeS(array, left, right, order);
        return array;
    }


    /**
     * 归并排序1
     * @param array
     * @param left
     * @param right
     */
    public static void mergeS(int[] array, int left, int right, byte order){
        if(left < right){
            // 获取中点
            int mid = (left + right)/2;
            // 排序左边
            mergeS(array, left, mid, order);
            // 排序右边
            mergeS(array, mid + 1, right, order);
            // 将左边和右边合并
            merge(array, left, mid, right, order);
        }
    }

    /**
     * 归并2:合并左中右
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] array, int left, int mid, int right, byte order){
        // 容器
        int[] tem = new int[right - left + 1];
        // 左边起点
        int i = left;
        // 右边起点
        int j = mid+1;
        // 容器索引
        int k = 0;
        // 双方有一个到头了推出
        while (i <= mid && j <= right){
            if(order == Constants.ORDER_ASC){
                if(array[i] < array[j]){
                    tem[k++] = array[i++];
                }else{
                    tem[k++] = array[j++];
                }
            }else{
                if(array[i] > array[j]){
                    tem[k++] = array[i++];
                }else{
                    tem[k++] = array[j++];
                }
            }
        }
        // 有一方到头了,剩下的放入容器
        while (i <= mid) tem[k++] = array[i++];
        while (j <= mid) tem[k++] = array[j++];
        // 将tem复制到原数组中
        for (int q = left; q <= right; q++){
            array[q] = tem[q - left];
        }
    }



    public static int[] mergeSortCP(int[] arr) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        mergeReduceMemory(arr, 0, arr.length - 1);
        return arr;

        //return merge1(arr, 0, arr.length - 1);
    }

    private static int[] merge1(int[] arr, int left, int right) {
        if (left == right){
            return new int[]{arr[left]};
        }
        int mid = (left + right)/2;

        int[] leftArr = merge1(arr, left, mid);
        int[] rightArr = merge1(arr, mid + 1, right);
        int[] res = new int[right - left + 1];
        int index = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < leftArr.length && rightIndex < rightArr.length){
            res[index++] = leftArr[leftIndex] <= rightArr[rightIndex] ? leftArr[leftIndex++] : rightArr[rightIndex++];
        }
        if (leftIndex == leftArr.length){
            // 右边还有
            while (rightIndex < rightArr.length){
                res[index++] = rightArr[rightIndex++];
            }
        }else {
            // 左边还有
            while (leftIndex < leftArr.length){
                res[index++] = leftArr[leftIndex++];
            }
        }
        return res;
    }

    /**
     * 节省内存的版本，也没有节省多少
     * @param arr
     * @param left
     * @param right
     */
    private static void mergeReduceMemory(int[] arr, int left, int right) {
        if (left == right){
            return;
        }
        int mid = (left + right)/2;

        int[] tem = new int[right - left + 1];

        mergeReduceMemory(arr, left, mid);
        mergeReduceMemory(arr, mid + 1, right);
        int index = 0;
        int leftIndex = left;
        int rightIndex = mid+1;
        while (leftIndex <= mid && rightIndex <= right){
            tem[index++] = arr[leftIndex] <= arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        if (leftIndex > mid){
            // 右边还有
            while (rightIndex <= right){
                tem[index++] = arr[rightIndex++];
            }
        }else {
            // 左边还有
            while (leftIndex <= mid){
                tem[index++] = arr[leftIndex++];
            }
        }
        leftIndex = left;
        for (int v : tem){
            arr[leftIndex++] = v;
        }
    }







    /**
     * 快速排序
     * @param array
     * @param order
     * @return
     */
    public static int[] quickSortRoot(int[] array, byte order){
        int left = 0;
        int right = array.length - 1;
        quickSort(array, left, right, order);
        return array;
    }

    /**
     * 快排
     * @param array
     * @param left
     * @param right
     * @param order
     */
    public static void quickSort(int[] array, int left, int right, byte order){
        if(left < right){
            // 返回所谓的理想中间值位置
            int mid = quickSort2(array, left, right, order);
            quickSort(array, left, mid - 1, order);
            quickSort(array, mid + 1, right, order);
        }
    }

    public static int quickSort2(int[] array, int left, int right, byte order){
        // 这里的策略先拿第一个数当中间值
        int midValue = array[left];
        // 忽略中间值
        int i = left + 1;
        int j = right;
        while (true){
            // 找到左边第一个大于midValue的值的index
            while (i <= j && array[i] <= midValue) i++;
            // 找到右边第一个大于midValue的值得index
            while (i <= j && array[j] >= midValue) j--;
            // 左边是否超过了右边，超过break
            if(i < j){
                break;
            }
            // 如果没有超过，左右交换值，继续下一轮
            int tem = array[i];
            array[i] = array[j];
            array[j] = tem;
        }
        // 将midValue交换到中间来
        array[left] = array[j];
        array[j] = midValue;
        // 返回midValue的index
        return j;
    }


    /**
     * 快速排序（二轮） 非稳定排序，非原地排序
     * @param arr
     * @return
     */
    public static int[] quickSortCP(int[] arr){
        if (arr == null || arr.length == 1){
            return arr;
        }
        quickSortCP1(arr, 0, arr.length - 1);
        return arr;
    }

    private static void quickSortCP1(int[] arr, int left, int right) {
        if (left >= right){
            return;
        }
        int midIndex = getMidIndexForQuickSort(arr, left, right);
        quickSortCP1(arr, left, midIndex - 1);
        quickSortCP1(arr, midIndex+1, right);
    }

    private static int getMidIndexForQuickSort(int[] arr, int left, int right) {
        // 正常默认取第一个值
        int mid = arr[left];
        int leftIndex = left+1;
        int rightIndex = right;
        while (true){

            // 找到左边第一个大于等于mid的
            while (leftIndex <= rightIndex && arr[leftIndex] < mid){
                leftIndex++;
            }
            // 找到右边第一个小于mid的
            while (leftIndex <= rightIndex && arr[rightIndex] > mid){
                rightIndex--;
            }

            if (leftIndex > rightIndex){
                break;
            }
            CommonUtils.swap(arr, leftIndex, rightIndex);
            leftIndex++;
            rightIndex--;
        }
        CommonUtils.swap(arr, rightIndex, left);
        return rightIndex;
    }

    /**
     * 堆排序 时间 O(nlogn) 空间 O(1) 非稳定排序 原地排序
     * @param array
     * @param order
     * @return
     */
     public static int[] heapSort(int[] array, byte order){
         // 获取长度
         int len = array.length;
         // 构建最值堆：从最后一个父节点开始往回遍历，每一次遍历都是一次父节点下沉
         for (int i = (len-2)/2; i >= 1; i--){
             heapSort2(array, i, len-1);
         }
        // 将顶和最后一个元素交换，重新构建最值堆
         for (int j = len-1; j >= 1; j--){
             // 顶底交换
             int temp = array[0];
             array[0] = array[j];
             array[j] = temp;
            heapSort2(array, 0, len - 1);
         }
         return array;
     }

    /**
     * 构建最值堆
     * @param array
     * @param parent
     * @param len
     */
    public static void heapSort2(int[] array, int parent, int len){
        int temp = array[parent];
        // 左孩子
        int child = parent * 2 + 1;
        // 将当前的parent下沉
        while (child <= len){
            if(child + 1 <= len && array[child] <= array[child+1]){
                // 右孩子大一点，指针指向右孩子
                child ++;
            }
            // 如果父节点就是最大值，直接不用下沉
            if(array[parent] > array[child]) break;
            // 否则将父节点下沉
            array[parent] = array[child];
            // parent指针指向孩子
            parent = child;
            // child指向孩子的孩子
            child = child * 2 + 1;
        }
        // 结束以后将原parent值复制给现在的
        array[parent] = temp;
    }

    /**
     * 计数排序,将所有的元素出现次数保存下来重新排序 O（n+k）O（k), 稳定排序，非原地排序
     * @param array
     * @param order
     * @return
     */
    public static int[] countSort(int[] array, byte order){
        try{
            if(array == null || array.length < 2) return array;
            // 取最大值和最小值
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < array.length; i++){
                if (array[i] > max){
                    max  = array[i];
                }
                if (array[i] < min){
                    min = array[i];
                }
            }
            // 根据最值区间定义一个数组
            int[] temp = new int[max - min + 1];
            for (int j = 0; j < array.length; j++){
                temp[array[j] - min]++;
            }
            // 统计完成，将数组返回到原来的数组中
            int[] result = new int[array.length];
            int k = 0;
            for (int q = 0; q < temp.length; q++){
                if(temp[q] > 0){
                    for (int t = 0; t < temp[q]; t++){
                        // 循环t次，将数字放入数组
                        result[k++] = q + min;
                    }
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return array;
        }
    }


    /**
     * 桶排序：通过定义若干桶，桶范围连续，将数据分到桶内，通过桶内排序再合并
     * O(n+k) O(n+k) 稳定排序，非原地排序
     * @param array
     * @param order
     * @return
     */
    public static int[] BucketSort(int[] array, byte order){
        if(array == null || array.length < 2) return array;
        // 获取最值
        // 取最大值和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++){
            if (array[i] > max){
                max  = array[i];
            }
            if (array[i] < min){
                min = array[i];
            }
        }
        // 计算应该需要多少个桶，当前定义每个桶的范围是5
        int range = 5;
        int arrayRange = max - min;
        int bucketNum = arrayRange/5 + 1;
        List<LinkedList<Integer>> bucket = new ArrayList<>(bucketNum);
        // 初始化
        for (int i = 0; i < bucketNum; i++){
            bucket.add(new LinkedList<>());
        }
        // 将数组值按照范围放入桶
        for (int j = 0; j < array.length; j++){
            int temp = array[j];
            bucket.get((temp - min)/5).add(temp);
        }
        int q = 0;
        int[] result = new int[array.length];
        // 桶内各自排序,并放入result
        for (int i = 0; i < bucketNum; i++){
            LinkedList<Integer> integers = bucket.get(i);
            Collections.sort(integers);
            // 放进result
            for (int k = 0; k < integers.size(); k++){
                result[q++] = integers.get(k)*5 + min;
            }
        }
        return result;
    }

    /**
     * 基数排序
     * 1、时间复杂度：O(kn)  2、空间复杂度：O(n+k)  3、稳定排序  4、非原地排序
     * @param array
     * @param order
     * @return
     */
    public static int[] radioSort(int[] array, byte order){
        if(array == null || array.length < 2) return array;
        // 取最大值多少位
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++){
            if(array[i] > max) max = array[i];
        }
        // 最大数的位数
        int num = 0;
        while ((max = max/10) > 0){
            num ++;
        }
        // 造桶，造10个桶
        List<LinkedList<Integer>> bucket = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
            bucket.add(new LinkedList<>());
        }
        // 将数组按照个位数开始，依次放入桶中排序
        for (int i = 0; i < num; i++){
            // 将数组按照某位数，进行装桶
            for (int j = 0; j < array.length; j++){
                // 取出当前的某一位
                int tem = (int)(array[i]/Math.pow(10, i))%10;
                bucket.get(tem).add(array[i]);
            }
            int index = 0;
            // 将桶中的数据重新放入array
            for (int k = 0; k < 10; k++){
                LinkedList<Integer> integers = bucket.get(k);
                for (Integer integer : integers){
                    array[index++] = integer;
                }
                // 将当前桶清空
                integers.clear();
            }
        }
        return array;
    }







}
