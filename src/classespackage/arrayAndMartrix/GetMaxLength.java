package classespackage.arrayAndMartrix;

import classespackage.stackAndQueue.catDogQueue.Pet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author swzhao
 * @date 2022/3/20 4:37 下午
 * @Discreption <> 未排序数组中累加和为给定值的最长子数组
 */
public class GetMaxLength {


    /**
     * 未排序正数数组中累加和为给定值的最长子数组长度
     * 1、滑动窗口或者队列的形式记录当前窗口中最大值和当前最长长度，遍历一次结束
     * @param array
     * @param num
     */
    public static int getMaxLength(int[] array, int num){
        if(array == null || array.length == 0 || num == 0){
            return 0;
        }
        int resLen = 0;
        int curSum = array[0];
        int left = 0;
        int right = 0;
        while (array.length != right){
            if(curSum == num){
                // 相等时需要更新当前的最大长度，left走一个
                resLen = Math.max(resLen, right - left + 1);
                curSum -= array[left++];
            }else if(curSum < num){
                // 判断是否越界
                if(right == array.length){
                    break;
                }
                right ++;
                curSum += array[right];
            }else if(curSum > num){
                // 大于，left走一个
                curSum -= array[left++];
            }
        }
        return resLen;
    }

    public static int maxLen2K(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 两个游标从开始游走left<= right
        int left = 0, right = 0;
        // sum为了实时保存left到right的和
        int sum = arr[0];
        // 结果
        int len = 0;
        while (left <= right && right <= arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                // 这里right尽可能的远
                right++;
                // 更新sum
                sum += right > arr.length ? 0 : arr[right];
            }else if (sum < k) {
                // 小了，需要拓展
                right++;
                sum += right >= arr.length ? 0 : arr[right];
            }else {
                // 大了，需要缩小
                sum -= arr[left];
                left++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] ints = {1, 3, 2, 5, 4, 7, 8};
        int[] ints1 = {-3, -1, -4, 6, 3, -3, 5, 6, 0};
        int[] ints2 = {0, 1, 1, 0, 0, 0, 0, 1, 0};

        int[] ints3 = {3, -2, -4, 0, 6};

        //System.out.println(myGetMaxLenth(ints, 8));
        //System.out.println(myGetMaxLengthFromPG(ints1));
        //System.out.println(myGetMaxLengthFrom01(ints2));
        System.out.println(maxLen2K(ints, 10));

    }




    /**
     * 未排序数组中累加和为给定值的最长子数组系列问题1
     * 1、利用array[i...j] = array[j] - array[j+1]计算子数组
     * 2、定义map存储当前位置的sum和当前位置（value），如果hash碰撞，则忽略
     * 3、遍历到i时，计算当前sum-k是否在之前的遍历中出现过，即map.get(sum - k),如果有，说明存在子数组和为k，更新len
     * @return
     */
    public static int getMaxLength2(int[] array, int k){
        try{
            if(array == null){
                return 0;
            }
            // 当前位置最长子数组长度
            int len = 0;
            // 当前位置的总体和
            int sum = 0;
            // 存储map<当前位置和，当前和的第一个出现的位置>
            Map<Integer, Integer> sumFirstIndex = new HashMap<>();
            // 先放入第一个元素，因为算法没有从第一个开始遍历，所以，需要将第一个位置的和先放入
            sumFirstIndex.put(0, -1);
            int index = 0;
            while (index != array.length){
                int curValue = array[index];
                // 先加上当前值
                sum += curValue;
                // 减去目标值
                int sub = sum - k;
                if (sumFirstIndex.get(sub) != null){
                    // 说明从0-index存在一个子数组累加和为k
                    Integer j = sumFirstIndex.get(sub);
                    // 更新长度
                    len = Math.max(len, index - j + 1);
                }
                // 将当前sum放入map中
                sumFirstIndex.putIfAbsent(sum, index++);
            }
            return len;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取最大子数组，求正数和负数数目相等的最长子数组长度
     * 1、负数变为-1，正数变为1，0不变，问题变为，求和为0的最长子数组问题
     * @param array
     * @return
     */
    public static int getMaxArray3(int[] array){
        try{
            // 将数组变为-1，1，0的数组
            int index = 0;
            int[] temArray = new int[array.length];
            while (index != array.length){
                if(array[index] == 0){
                    temArray[index] = 0;
                }else if(array[index] > 0){
                    temArray[index] = 1;
                }else if(array[index] < 0){
                    temArray[index] = -1;
                }
            }
            int len = 0;
            int sum = 0;
            index = 0;
            Map<Integer, Integer> sumFirstIndex = new HashMap<>();
            sumFirstIndex.putIfAbsent(0, -1);
            while (index != temArray.length){
                int curValue = temArray[index];
                sum += curValue;
                int sub = sum - 0;
                if(sumFirstIndex.containsKey(sub)){
                    Integer j = sumFirstIndex.get(sub);
                    len = Math.max(len, index - j + 1);
                }
                sumFirstIndex.putIfAbsent(sum, index);
                index++;
            }
            return len;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 未排序数组中累加和小于或等于给定值的最大子数组长度
     * 时间 O（nlogn）空间 O（n）
     * 1、和上面的区别：范围需要找到第一个大于sum-k出现的index
     * 2、准备工作：
     *      · 遍历数组，获取每一个位置的累加和并跟上一个累加和对比（为了二分排序），比上一个小的话，则等于上一个值
     *      · 得到第二个数组arrayHelper
     *      · 注意第一个值需要从0开始，累加和为0
     * 3、二次遍历累加和：
     *      · 遍历每一个元素，看作以该元素作为最后一个元素的子数组中，如果存在累加和比给定k小的话，一定存在某个位置的累加和大于sum-k
     *      · 如果该位置存在，则i-j+1就是最大值，更新最长长度即可
     * @param array
     * @param num
     * @return
     */
    public static int getMaxArray4(int[] array, int num){
        try{
            // 获取累加和与累加和helper,需要计算第0个元素，所以多一个
            int[] sumArray = new int[array.length + 1];
            int[] arrayHelper = new int[array.length + 1];
            // 按照上面的逻辑应该是（-1， 0），表示一个数都不加的情况的和
            sumArray[0] = 0;
            arrayHelper[0] = 0;
            int index = 0;
            int sum = 0;
            while (index != array.length){
                int cur = array[index];
                sum += cur;
                if(index > 0 && sum < sumArray[index-1]){
                    // 不是第一个值并且当前和比前面的小
                    arrayHelper[index++] = sumArray[index-1];
                }else{
                    arrayHelper[index++] = sum;
                }
                // 位移一个单位
                sumArray[index + 1] = sum;
                index++;
            }
            // 遍历sumarray
            index = 0;
            int len = 0;
            while (index != sumArray.length){
                int cur = sumArray[index];
                int sub = cur - num;
                // 找出第一个和比sub大的index
                int firstMaxIndex = getIndexByBinary(arrayHelper, sub);
                // firstMaxIndex这个位置需要在index的前面
                if(firstMaxIndex <= index){
                    // 这里位移抵消了，两个数组都右移动了一个
                    len = Math.max(len, index - (firstMaxIndex) + 1);
                }
                index++;
            }
            return len;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 求和小于等于num的最长子数组长度
     * 方法一：map
     * @param arr
     * @param num
     * @return
     */
    public static int[] myGetMaxLenthLessNum(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return null;
        }
        int[] ints = new int[3];
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
            int sub = sum - num;
            // map中的和应该大于等于sub，子数组和才能小于num
            for (Integer key : map.keySet()){
                if (key >= sub){
                    if (i - map.get(key) > maxLen){
                        maxLen = i - map.get(key);
                        ints[0] = map.get(key);
                        ints[1] = i;
                        ints[2] = maxLen;
                    }
                }
            }

        }
        return ints;
    }
    /**
     * 求和小于等于num的最长子数组长度
     * 方法二：二分法查找
     * @param arr
     * @param num
     * @return
     */
    public static int[] myGetMaxLenthLessNum2(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return null;
        }
        int[] ints = new int[3];
        int maxLen = 0;
        // 长度多一个1用来存（-1,0）
        int[] helper = new int[arr.length + 1];
        // 以0为底的和为0
        helper[0] = 0;
        int sum = 0;
        // 将和处理一下放入arr中，比如原和arr[1,2,1,4,3] = [1,2,2,4,4]
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
            helper[i+1] = sum > helper[i]? sum : helper[i];
        }
        sum = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
            int sub = sum - num;
            // 二分法找到一个大于等于sub的数字返回位置
            int index = find(helper, sub, i);
            if (index != -1){
                while (index > 0 && helper[index] == helper[index-1]){
                    index--;
                }
                if (i - index + 1 > maxLen){
                    maxLen = i - index+1;
                    ints[0] = index;
                    ints[1] = i;
                    ints[2] = maxLen;
                }
            }

        }
        return ints;
    }


    private static int find(int[] helper, int sub, int end) {
        if (helper == null || helper.length == 0 || end < 0){
            return -1;
        }
        if (end == 0){
            if (helper[0] >= sub){
                return 0;
            }else {
                return -1;
            }
        }
        int left = 0;
        int right = end;
        while (left < right){
            int mid = (left + right)/2;
            if (helper[mid] >= sub){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }





    /**
     * 求累加和为指定值的最长子数组长度
     * @param arr
     * @param num
     * @return 1:起点index，2、终点index 3、最大长度
     */
    public static int[] myGetMaxLenth(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return null;
        }
        int res = 0;
        // 以某个数为底的和 -> 出现这个和的第一次出现的index
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化需要添加一个以和为0的index为-1，也就是sum本身
        map.put(0, -1);
        int[] headAndTail = new int[3];
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++){
            sum += arr[i];
            int key = sum - num;
            // 先检验0
            if (map.containsKey(key)){
                if (i - map.get(key) > maxLen){
                    headAndTail[0] = map.get(key);
                    headAndTail[1] = i;
                    maxLen = i - map.get(key);
                    headAndTail[2] = maxLen;
                }
            }
            map.putIfAbsent(sum, i);
        }
        return headAndTail;
    }


    /**
     * 获取数组中正负数数量相同的最长子数组长度
     * @param arr
     * @return
     */
    public static int[] myGetMaxLengthFromPG(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        for (int i = 0; i < arr.length; i++){
            if (arr[i] < 0){
                arr[i] = -1;
            }else if (arr[i] > 0){
                arr[i] = 1;
            }
        }
        return myGetMaxLenth(arr, 0);
    }


    /**
     * 数组只有0和1，求0和1个数相同的最长子数组长度
     * @param arr
     * @return
     */
    public static int[] myGetMaxLengthFrom01(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == 0){
                arr[i] = -1;
            }
        }
        return myGetMaxLenth(arr, 0);
    }








    /**
     * 二分法查找左边第一个比当前数大或等于的index
     * @param array
     * @param num
     * @return
     */
    public static int getIndexByBinary(int[] array, int num) throws Exception{
        int left = 0;
        int right = array.length-1;
        int eqIndex = -1;
        while (left != right){
            // 注意：如果这里只除以2的情况,需要考虑left每次赋值都要-1，否则会无限循环
            int mid = array[(left + right) >>> 1];
            int midIndex = (left + right) >>> 1;
            if(num < mid){
                // 前半段
                right = midIndex;
                continue;
            }else if(num > mid){
                left = midIndex-1;
            }else if (num == mid){
                eqIndex = midIndex;
            }
        }
        // 到这里结束后，right==left或者遇到相等的了
        if(eqIndex == -1){
            // 说明没有遇到相等的，并且left和right相等值比当前值小
            return left + 1;
        }else{
            // 有相等的找到从左往右边第一个相等的
            while (eqIndex >= 0 && array[eqIndex] == num){
                eqIndex--;
            }
            return eqIndex + 1;
        }
    }

}
