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

    public static int[] getMinK1(int[] array, int k) {
        try {
            if (array == null || array.length <= k) {
                return array;
            }
            // 保存前k个最小值
            int[] res = new int[k];
            // 先扔进去k个数字
            for (int i = 0; i < k; i++) {
                res[i] = array[i];
            }
            // 第一次进行大根堆的调整
            maxNumHeap(res);
            for (int j = k; j < array.length - 1; j++) {
                if (array[j] < res[0]) {
                    res[0] = array[j];
                    maxNumHeap(res);
                }
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return new int[0];
        }
    }


    /**
     * 重新整理大根堆
     *
     * @param res
     * @return
     */
    public static void maxNumHeap(int[] res) {
        int len = res.length;
        // 从倒数第一个非子节点开始往回走
        for (int i = len / 2 - 1; i >= 0; i--) {
            processHeap(res, i);
        }
    }

    public static void processHeap(int[] res, int index) {
        int tem = res[index];
        int parent = index;
        // 左孩子节点索引
        int lcIndex = index * 2 + 1;
        // 只要不是孩子节点就继续往下遍历
        while (lcIndex < res.length) {
            int child = res[lcIndex];
            // 判断右孩子是否比左孩子小
            if (lcIndex + 1 < res.length && res[lcIndex + 1] < res[lcIndex]) {
                child = res[lcIndex + 1];
                // 转移到右节点
                lcIndex++;
            }
            // 判断是否父节点需要下沉
            if (res[parent] < child) {
                res[parent] = child;
            }
            // 父节点更新
            parent = lcIndex;
            // lcIndex变成自己的左孩子
            lcIndex = lcIndex * 2 + 1;
        }
        // 结束以后lcIndex 停留在
        res[parent] = tem;
    }

    /**
     * 方法二：
     * 时间O（BfPrt算法）
     *
     * @param array
     * @param k
     * @return
     */
    public static int[] getMinK2(int[] array, int k) {
        try {
            if (array == null || array.length <= k) {
                return array;
            }
            int[] res = new int[k];
            int index = 0;
            int minK = BFPRT(array, k);
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < minK) {
                    res[index++] = array[i];
                }
            }
            // 可能会有相等的数，并且长度只能是k的时候
            for (; index < res.length; ) {
                res[index++] = minK;
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return array;
        }
    }

    /**
     * BFPart算法
     * 返回array中第k个小的数
     *
     * @param array
     * @param k
     * @return
     */
    private static int BFPRT(int[] array, int k) {
        // 复制一份
        int[] newArray = Arrays.copyOf(array, array.length);
        // 找到第k个小的数x
        int x = select(newArray, 0, newArray.length - 1, k - 1);
        return x;
    }

    /**
     * 获取第k个小的数
     *
     * @param newArray
     * @param start
     * @param end
     * @param i
     * @return
     */
    private static int select(int[] newArray, int start, int end, int i) {
        // 只剩下一个
        if (end == start) {
            return newArray[start];
        }
        int pivot = medianOfMedian(newArray, start, end);
        int[] pivotRange = partition(newArray, start, end, pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return newArray[i];
        } else if (i < pivotRange[0]) {
            // 前半段继续递归
            return select(newArray, start, pivotRange[0] - 1, i);
        } else {
            // 后半段继续递归
            return select(newArray, pivotRange[i] + 1, end, i);
        }
    }


    /**
     * 获取中位数的中位数
     *
     * @param newArray
     * @param start
     * @param end
     * @return
     */
    private static int medianOfMedian(int[] newArray, int start, int end) {
        // 长度
        int num = start - end + 1;
        // 是否有余数，有的话需要多一个数组空间
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = start + i * 5;
            int endI = beginI + 4;
            // Math.min(endI, end)的意思是如果最后一个数组不够5位就按照end作为最后一个数
            mArr[i] = getMedian(newArray, beginI, Math.min(endI, end));
        }
        // 获取mArr的中位数
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    /**
     * 获取中位数
     *
     * @param newArray
     * @param beginI
     * @param endI
     * @return
     */
    private static int getMedian(int[] newArray, int beginI, int endI) {
        // 插入排序后将中间数取出来
        for (int i = beginI; i < endI; i++) {
            for (int j = i; j < endI; j++) {
                if (newArray[j] < newArray[i]) {
                    int tem = newArray[j];
                    newArray[j] = newArray[i];
                    newArray[i] = tem;
                }
            }
        }
        // 返回后中间值
        return newArray[endI + beginI / 2 + 1];
    }


    /**
     * 按照pivot的值将newArray进行左右划分
     *
     * @param newArray
     * @param start
     * @param end
     * @param pivot
     * @return 返回int[0]和int[1]是pivot所在位置, 如果pivot不止一个，0在左边，1在右边
     */
    private static int[] partition(int[] newArray, int start, int end, int pivot) {
        int small = start - 1;
        int big = end + 1;
        int cur = start;
        while (cur != big) {
            if (newArray[cur] < pivot) {
                // 小的放左边
                CommonUtils.swap(newArray, ++small, cur++);
            } else if (newArray[cur] > pivot) {
                CommonUtils.swap(newArray, cur, --big);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }


    /**
     * 二轮测试：求arr中前k个小的数
     *
     * @param arr
     * @return
     */
    public static int[] printKMinCp1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return null;
        }
        // 维护一个k大小的大根堆
        int[] res = new int[k];
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                // 说明堆没有满
                heapInsertCp1(res, i, arr[i]);
            } else {
                // 说明堆满了,比较头节点
                if (arr[i] < res[0]) {
                    // 替换顶
                    res[0] = arr[i];
                    // 进行一波下沉,因为这里堆已经满了，所以就不写通用的推下沉方法了，正常来说应该要给一个size
                    heapify(res, 0);
                }
            }
        }
        return res;
    }

    /**
     * 从i处开始下城
     *
     * @param res
     * @param i
     */
    private static void heapify(int[] res, int i) {
        int size = res.length;
        // 申请内存
        int parent = i;
        int left = parent * 2 + 1;
        while (left < size) {
            // 先跟左节点比较出大值
            int maxIndex = res[parent] < res[left] ? left : parent;
            // 判断右节点是否存在，是否右节点更大
            int right = left + 1;
            if (right < size && res[right] > res[maxIndex]) {
                // parent需要和right交换
                maxIndex = right;
            }
            // 判断是否交换
            if (maxIndex == parent) {
                // 不用交换直接退出
                break;
            }
            // 需要交换
            CommonUtils.swap(res, parent, maxIndex);
            // 更新parent和left
            parent = maxIndex;
            left = parent * 2 + 1;
        }
    }

    /**
     * 将value插入到arr的index处并进行一波上浮操作
     *
     * @param res
     * @param index
     * @param value
     */
    private static void heapInsertCp1(int[] res, int index, int value) {
        res[index] = value;
        int i = index;
        while (i != 0) {
            // 计算出父节点
            int parent = (i - 1) / 2;
            if (res[parent] < value) {
                // 交换
                CommonUtils.swap(res, parent, i);
                // 继续下一轮
                i = parent;
            } else {
                break;
            }
        }
    }


    public static void main(String[] args) {
        CommonUtils.printArr(printKMinCp2(new int[]{5, 3, 6, 1, 2, 7, 4}, 3));
    }


    /**
     * 二轮测试：方法2，BFPRT 算法求arr中前k个小的数
     *
     * @param arr
     * @return
     */
    public static int[] printKMinCp2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return null;
        }
        // arr中第k小的数的位置
        int kMin = BFPRTCP2(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (index == k) {
                break;
            } else {
                if (arr[i] < kMin) {
                    res[index++] = arr[i];
                }
            }
        }
        // 将剩下的多余位置都变成kmin
        while (index < k) {
            res[index++] = kMin;
        }
        return res;
    }


    private static int BFPRTCP2(int[] arr, int k) {
        return selectCp1(arr, 0, arr.length - 1, k - 1);
        // 分数组
        // 各数组插入排序，选出中位数并组成一个新的arr
        // 求新的arr中第arr.lenth/2 小的数,即为midIndex
        // 重新调整数组，算出中间值mid的新位置
        // k==mid，返回，小于 求左边，大于求右边
    }

    /**
     * 递归主体
     *
     * @param arr
     * @param start
     * @param end
     * @param k
     * @return
     */
    private static int selectCp1(int[] arr, int start, int end, int k) {
        if (start == end) {
            return arr[start];
        }
        int mid = getMidOfMid(arr, start, end);
        int[] range = getRange(arr, start, end, mid);
        if (k >= range[0] && k <= range[1]) {
            return arr[k];
        } else if (k <= range[0]) {
            // 在左边
            return selectCp1(arr, 0, range[0] - 1, k);
        } else {
            // 在右边
            return selectCp1(arr, range[1] + 1, end, k - range[1]);
        }
    }

    private static int[] getRange(int[] arr, int start, int end, int mid) {
        // 两个边界
        int small = start - 1;
        int big = end + 1;
        int cur = start;
        while (cur < big) {
            if (arr[cur] < mid) {
                CommonUtils.swap(arr, ++small, cur++);
            } else if (arr[cur] > mid) {
                CommonUtils.swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        return new int[]{small + 1, big - 1};
    }

    /**
     * 获取中位数的中位数
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int getMidOfMid(int[] arr, int start, int end) {
        // 中位数个数计算
        int len = (end - start + 1) / 5 + ((end-start+1) % 5 == 0 ? 0 : 1);
        // 中位数容器
        int[] midArr = new int[len];
        for (int i = 0; i < midArr.length; i++) {
            // 循环计算中位数
            midArr[i] = getMid(arr, i * 5, i * 5 + 5 > end ? end : i * 5 + 4);
        }
        // 计算中位数的中位数
        return selectCp1(midArr, 0, midArr.length-1, midArr.length / 2);
    }

    /**
     * 获取midarr的中位数
     *
     * @param midArr
     * @param start
     * @param end
     * @return
     */
    private static int getMid(int[] midArr, int start, int end) {
        // 插入排序
        insertSortCp2(midArr, start, end);
        // 找到中位数,偶数为下中位数
        return midArr[(start + end) / 2 + ((end - start+1) % 2 == 0 ? 1 : 0)];
    }

    /**
     * 插入排序
     *
     * @param midArr
     * @param start
     * @param end
     */
    private static void insertSortCp2(int[] midArr, int start, int end) {
        for (int i = start; i <= end; i++) {
            for (int j = i; j > start; j--) {
                if (midArr[j] < midArr[j - 1]) {
                    CommonUtils.swap(midArr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }


}