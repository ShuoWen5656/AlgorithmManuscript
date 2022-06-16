package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/14 22:34
 * @Discreption <> 自然数数组的排序
 * 介绍：一个数组长度为n保存了1..n的数字，并且不重复，将数组进行排序
 */
public class SortNNumArray {


    /**
     * 方法一：
     * 替换
     * @param array
     */
    public static void sort1(int[] array){
        try {
            int tem = 0;
            for (int i = 0; i < array.length; i++){
                while (array[i] != i+1){
                    int index = array[i] - 1;
                    tem = array[index];
                    array[index] = array[i];
                    array[i] = tem;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 方法二：有点不好理解
     * 将要被替换的值保存起来，while里面不能出现i，因为i不会变，
     * 一直循环替换直到替换到array[i]正确为止
     * @param array
     */
    public static void sort2(int[] array){
        try {
            int tem = 0;
            int next = 0;
            for (int i = 0; i < array.length; i++){
                tem  = array[i];
                while (array[i] != i + 1){
                    // 要被替换的数字
                    next = array[tem-1];
                    array[tem-1] = tem;
                    tem = next;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
