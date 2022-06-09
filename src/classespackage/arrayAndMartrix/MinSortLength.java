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
}
