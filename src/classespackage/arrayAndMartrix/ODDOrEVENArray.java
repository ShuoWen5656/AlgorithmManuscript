package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/6/16 21:55
 * @Discreption <> 奇数下标都是奇数或者偶数下表都是偶数
 */
public class ODDOrEVENArray {

    /**
     * 调整array，使得奇数下标都是奇数或者偶数下表都是偶数
     * 不断的判断最后一个数是奇数还是偶数，如果是奇数就放到奇数index，否则放到偶数index
     * @param array
     */
    public static void modify(int[] array){
        try {
            int even = 0;
            int odd = 1;
            int end = array.length-1;
            while (end >= even && end >= odd){
                if ((array[end]&1) == 0){
                    // 偶数
                    CommonUtils.swap(array, end, even);
                    even+=2;
                }else {
                    CommonUtils.swap(array, end, odd);
                    odd+=2;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
