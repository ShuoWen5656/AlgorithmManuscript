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






}
