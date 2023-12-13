package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/9 21:33
 * @Discreption <> 需要排序的最短子数组长度
 */
public class MinSortLength {


    /**
     * 主方法
     * @param array
     * @return
     */
    public static int getMinLength(int[] array){
        try {

            int noMinIndex = -1;
            int noMaxIndex = -1;
            int min = array[array.length-1];
            int max = array[0];
            // 倒着找，如果当前比最小值小，说明整个序列从小到大没毛病，不用换位置
            // 如果比min大，说明最少从当前位置开始需要排序
            for (int i = array.length-1; i >= 0; i--){
                if(array[i] > min){
                    noMinIndex = i;
                }else {
                    min = array[i];
                }
            }
            if (noMinIndex == -1){
                return 0;
            }
            // 这里找到最右边有序的边界
            // 如果比max小，说明最少从这里往回需要排序
            for (int j = 0 ;j < array.length; j++){
                if(array[j] < max){
                    noMaxIndex = j;
                }else {
                    max = array[j];
                }
            }
            return noMaxIndex - noMinIndex + 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：需要排序的最短子数组长度
     * @param arr
     * @return
     */
    public static int sortLenCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 边界值，为遍历到目前，最值数
        int bound = 0;
        // 遍历到目前，在最值数另一边导致顺序乱序的最左边或者最右边的数
        int indexleft = -1, rightIndex = -1;
        int i = arr.length-1;
        bound = arr[arr.length-1];
        while (i >= 0) {
            if (arr[i] < bound) {
                // 最值更新
                bound = arr[i];
            }else if (arr[i] > bound){
                // 更新需要移动到i右边的最左边的数
                indexleft = i;
            }
            i--;
        }
        // 从左到右遍历
        i = 0;
        bound = arr[0];
        while (i < arr.length) {
            if (arr[i] > bound) {
                bound = arr[i];
            }else if (arr[i] < bound){
                rightIndex = i;
            }
            i++;
        }
        if (indexleft == -1 && rightIndex == -1){
//            整个数组都是有序的
            return 0;
        }else{
            return rightIndex - indexleft + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(sortLenCp1(new int[]{1,2,3,2,35,8,9}));
    }



}
