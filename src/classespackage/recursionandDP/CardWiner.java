package classespackage.recursionandDP;

import java.util.Map;

/**
 * @author swzhao
 * @date 2022/4/30 9:34 上午
 * @Discreption <>排成一条线的纸牌博弈问题
 * 1、array是排成一条线的纸牌，两个玩家可以拿最前的也可以拿最后的，但是要求保证自己最后获取最高的分数
 */
public class CardWiner {

    /**
     * 给定一个数组，获取玩这个游戏能够获取的最大数值
     * 暴力递归方法：时间O（2^n）空间O（n）
     * 1、作为第一个操作的玩家，能够获取的最大分数和作为第二个操作玩家能够获取最大分数中较大的那个
     * @param array
     * @return
     */
    public static int win1(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        return Math.max(f(array, 0, array.length-1), s(array, 0, array.length-1));
    }

    /**
     * 作为第一个开始的玩家
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int f(int[] array, int start, int end){
        if(start == end){
            // 只有一个数字了，最大值就是当前值
            return array[0];
        }
        // 两个以上的数组时，需要考虑拿左边还是右边让自己获取最大值
        return Math.max(array[start] + s(array, start+1, end), array[end] + s(array, start, end));
    }

    /**
     * 作为第二个开始的玩家
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int s(int[] array, int start, int end){
        if(start == end){
            return 0;
        }
        // 第二个开始的玩家需要考虑被拿掉一个元素后的最小值，因为对方也是聪明人一定会给自己留下最小值
        return Math.min(f(array, start+1, end), f(array, start, end-1));
    }

    /**
     * 动态规划方法
     * @param array
     * @return
     */
    public static int win2(int[] array){
        try {
            if(array == null || array.length == 0){
                return 0;
            }
            int[][] f = new int[array.length][array.length];
            int[][] s = new int[array.length][array.length];
            f[0][0] = array[0];
            s[0][0] = 0;
            // 这里j是行，i是列
            for (int i = 1; i < array.length; i++){
                // 先将斜角初始化
                f[i][i] = array[i];
                s[i][i] = 0;
                // 开始从斜角往上计算
                for (int j = i; j >= 0; j--){
                    f[j][i] = Math.max(array[j] + s[j+1][i], array[i] + s[j][i-1]);
                    s[j][i] = Math.min(f[j+1][i], f[j][i-1]);
                }
            }
            return Math.max(f[0][array.length-1], s[0][array.length-1]);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试：递归方法
     * @param arr
     * @return
     */
    public static int winCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 对a来说是先走，对b来说是后走,取最大的
        return Math.max(first(arr, 0, arr.length-1), second(arr, 0, arr.length - 1)) ;
    }

    /**
     * 先选的人最终获得的分数
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int first(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        return Math.max(arr[left] + second(arr, left+1, right), arr[right] + second(arr, left, right-1));
    }

    /**
     * 后选的人最终获得的分数
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int second(int[] arr, int left, int right) {
        if (left == right) {
            // 第二个选就么得选
            return 0;
        }
        return Math.min(first(arr, left+1, right), first(arr, left, right-1));
    }


    /**
     * 二轮测试：动态规划方法
     * @param arr
     * @return
     */
    public static int dpCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 先选dp
        int[][] firstDp = new int[arr.length][arr.length];
        // 后选dp
        int[][] secondDp = new int[arr.length][arr.length];
        // 初始化
        for (int i = 0; i < arr.length; i++) {
            firstDp[i][i] = arr[i];
            secondDp[i][i] = 0;
        }
        for (int j = 1; j < arr.length; j++) {
            for (int i = j-1; i >= 0; i--) {
                firstDp[i][j] = Math.max(arr[i] + secondDp[i+1][j], arr[j] + secondDp[i][j-1]);
                secondDp[i][j] = Math.min(firstDp[i+1][j], firstDp[i][j-1]);
            }
        }
        return Math.max(firstDp[0][arr.length-1], secondDp[0][arr.length-1]);
    }

    public static void main(String[] args) {
        System.out.println(dpCp1(new int[]{1,100,2}));
    }


}
