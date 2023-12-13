package utils;

import java.util.Comparator;

/**
 * @author swzhao
 * @data 2022/7/24 10:05
 * @Discreption <> 生成小根堆的比较器
 */
public class MinHeapComparater implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o2 > o1){
            return -1;
        }else {
            return 1;
        }
    }
}
