package leetcode;

import java.util.*;

/**
 * @author swzhao
 * @data 2023/10/3 10:27
 * @Discreption <> H指数
 */
public class HIndex {


    public static void main(String[] args) {
        System.out.println(solution1(new int[]{3,0,6,1,5}));
    }


    public static int solution1(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        // 计算每一轮中比上一轮中的i大的值，也就是候选者
        List<Integer> list = new ArrayList<>();
        // 初始化全部加进来
        for (int i = 0; i < citations.length; i++) {
            list.add(citations[i]);
        }
        // 从1开始
        for (int i = 1; i <= citations.length; i++) {
            // 淘汰
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (next < i) {
                    iterator.remove();
                }
            }
            // 每次淘汰结束后判断是否满足了退出条件
            if (list.size() < i) {
                return i-1;
            }
        }
        return list.size();
    }
}
