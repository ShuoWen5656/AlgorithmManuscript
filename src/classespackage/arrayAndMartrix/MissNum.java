package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/6/27 21:56
 * @Discreption <> 数组中未出现的最小正整数
 * 给定一个无序整型数组，找到数组中未出现的最小正整数
 */
public class MissNum {

    public static int missNum(int[] array){
        try {
            int l = 0;
            int r = array.length;
            while (l < r){
                if (array[l] == l+1){
                    l++;
                }else if (array[l] < l || array[l] > r || array[array[l] - 1] == array[l]){
                    array[l] = array[--r];
                }else {
                    CommonUtils.swap(array, l, array[l] - 1);
                }
            }
            return l+1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


}
