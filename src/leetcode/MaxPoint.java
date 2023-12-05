package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2023/12/5 9:31 下午
 * @Discreption <> 直线上最多的点数
 */
public class MaxPoint {


    public static int solution(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }
        int res = 0;
        // 遍历每一个点
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> xy2NumMap = new HashMap<>();
            if (res > points.length/2) {
                // 超过半数则直接返回
                break;
            }
            if (res >= points.length - i) {
                // 在一条直线上的点已经超过了剩下未遍历的数量直接返回
                break;
            }
            // 不回头
            for (int j = i + 1; j < points.length; j++) {
                // 坐标差值
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                // 处理垂直和水平情况
                if (x == 0) {
                    y = 1;
                }else if (y == 0) {
                    x = 1;
                }else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcd = gcd(Math.abs(x), Math.abs(y));
                    x = x/gcd;
                    y = y/gcd;
                }
                int key = y + ((200001) * x);
                xy2NumMap.put(key, xy2NumMap.getOrDefault(key, 0) + 1);
            }
            // 当前点为中心的所有直线中的最大值记录一下
            for (Map.Entry<Integer, Integer> entry : xy2NumMap.entrySet()) {
                res = Math.max(res, entry.getValue() + 1);
            }
        }
        return res;
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x%y);
    }

}
