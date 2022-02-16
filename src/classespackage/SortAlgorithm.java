package classespackage;

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





}
