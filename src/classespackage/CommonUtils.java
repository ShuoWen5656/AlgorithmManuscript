package classespackage;

/**
 * @Author swzhao
 * @Date 2022/2/13 21:02
 * @Discription<>公共方法
 */
public class CommonUtils {

    /**
     * 交换1、2索引值
     * @param array
     * @param index1
     * @param index2
     */
    public static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * 获取中间值，如果为偶数，则取前一个
     * @param left
     * @param right
     */
    public static int getMid(int left, int right){
        return (left + right) /2;
    }




}
