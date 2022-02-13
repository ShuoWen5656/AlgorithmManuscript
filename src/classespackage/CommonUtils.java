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


}
