package classespackage.arrayAndMartrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swzhao
 * @data 2022/6/25 9:38
 * @Discreption <> 求最短通路值
 * 用一个整型矩阵array，只有0和1,1表示此路通，0表示此路不通，现在求从左上角到右下角最短路径长度
 */
public class MinPathValue {

    /**
     * 主方法
     * 广度优先算法
     * @param array
     * @return
     */
    public static int minPathValue(int[][] array){
        try {
            // array左上角和右下角不能不通
            if (array == null || array.length == 0 || array[0].length == 0
                    || array[0][0] == 0 || array[array.length-1][array[0].length-1] == 0){
                return 0;
            }
            // 行列变量初始化
            int r = 0;
            int c = 0;
            // map[i][j] 表示从array[0][0] 到array[i][j]的最短通路距离
            int[][] map = new int[array.length][array[0].length];
            // 两个队列保存坐标，rq保存行，cq保存列，组合一起为一个坐标
            Queue<Integer> rq = new LinkedList<>();
            Queue<Integer> cq = new LinkedList<>();
            // 初始时左上角的坐标先进入队列
            rq.add(0);
            cq.add(0);
            while (!rq.isEmpty()){
                // 取出当前值
                r = rq.poll();
                c = cq.poll();
                // 已经遍历到右下角则直接返回结果即可
                if (r == array.length-1 && c == array[0].length-1){
                    return map[r][c];
                }
                // 如果需要继续遍历，则通过上下左右的顺序进行遍历，这个顺序不需要保证，因为当前层的所有值都会被遍历（广度优先）因此先到达右下角返回的必然是最小值
                walkTo(map[r][c], r-1, c, array, map, rq, cq);
                walkTo(map[r][c], r+1, c, array, map, rq, cq);
                walkTo(map[r][c], r, c-1, array, map, rq, cq);
                walkTo(map[r][c], r, c+1, array, map, rq, cq);
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取周边路径和
     * @param mValue
     * @param toR
     * @param toC
     * @param araray
     * @param map
     * @param rq
     * @param cq
     */
    private static void walkTo(int mValue, int toR, int toC, int[][] araray,int[][] map, Queue<Integer> rq, Queue<Integer> cq) {
        // 1、周边不能越界 2、array的值不能为0 3、map的值必须为0，也就是没有计算过最短路径才可以
        if (toR < 0 || toC < 0
                || toR == araray.length || toC == araray[0].length
                || araray[toR][toC] != 1
                || map[toR][toC] != 0){
            return;
        }
        // 从左上角到（toR,toC）的位置的最短路径
        map[toR][toC] = mValue + 1;
        rq.add(toR);
        cq.add(toC);
    }

}
