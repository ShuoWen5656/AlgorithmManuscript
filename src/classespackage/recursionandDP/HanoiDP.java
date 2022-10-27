package classespackage.recursionandDP;

import classespackage.Constants;

/**
 * @author swzhao
 * @date 2022/4/22 10:06 上午
 * @Discreption <>汉诺塔问题
 */
public class HanoiDP {


    /**
     * 递归解法
     * @param n
     */
    public static void hanoi(int n){
        if(n == 0){
            return;
        }
       func1(n, Constants.HANOI_LEFT, Constants.HANOI_MID, Constants.HANOI_RIGHT);
    }

    /**
     * 递归方式解决问题
     * 方法意义：n个块从from到to的过程
     * @param n
     * @param from
     * @param mid
     * @param to
     */
    private static void func1(int n, String from, String mid, String to) {
        if(n == 1){
            // 直接移动
            System.out.println("move from" + from + "to" + to);
        }else{
            // 三步走，上面n-1个块先到mid，最底下的到to，n-1再到to
            func1(n-1, from, to, mid);
            func1(1, from, mid, to);
            func1(n-1, mid, from, to);
        }
    }


    /**
     *
     * @param num
     */
    public static void hanoiCP1(int num) {
        if (num == 0) {
            return;
        }
        processForhanoiCp1(num, Constants.HANOI_LEFT, Constants.HANOI_MID, Constants.HANOI_RIGHT);
    }

    /**
     * 汉诺塔这里只有起点、目标、其他个柱子，递归不认左中右，只认起点和中点
     * @param num
     * @param from
     * @param other
     * @param to
     */
    private static void processForhanoiCp1(int num, String from, String other, String to) {
        if (num == 1) {
            // 最后一个圆盘
            System.out.println(String.format("move form %s to %s", from, to));
        }else {
            processForhanoiCp1(num-1, from, to, other);
            processForhanoiCp1(1, from, other, to);
            processForhanoiCp1(num-1, other, from, to);
        }
    }



    /**
     * 进阶问题：如果给定array，index为从小到大的第几个圆盘，value为1：左，2：中：3右三个位置，用来表示当前中间状态，求到达当前中间状态的步数需要多少步
     * 方法一：递归方式
     * 判断最大的盘，
     *      ` 最大的盘在左边，则上面的n-1个盘正在从左边往中间移动
     *      ` 最大的盘在右边，则上面的n-1个盘正在从中间往右边移动
     *      ` 最大的盘在中间，不可能情况，直接放回-1
     * 总步数应该是2（s(n-1)） + 1
     * @return
     */
    public static int hanoi1(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        return process1(array, array.length - 1, 1, 2, 3);
    }

    /**
     * 递归方式
     * 1、结束点：最大盘结束，返回0
     * 2、如果最大盘在mid了，说明整个流程都不可能了，返回-1
     * @param array
     * @param index
     * @param from
     * @param mid
     * @param to
     * @return
     */
    private static int process1(int[] array, int index, int from, int mid, int to) {
        if(index == -1){
            return 0;
        }
        if(array[index] == mid){
            return -1;
        }
        if(array[index] == from){
            // 说明还在n-1个盘从左边到中间的过程
            return process1(array, index - 1, from, to, mid);
        }else{
            // 说明已经到n-1个盘从中间到右边的过程
            int res = process1(array, index - 1, mid, from, to);
            if (res == -1){
                return -1;
            }
            return (1 << index) + res;
        }
    }


    /**
     * 进阶问题
     * @param arr
     */
    public static int hanoiCPPlus(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return processForhanoiCpPlus(arr, arr.length-1, 1,3,2);
    }

    private static int processForhanoiCpPlus(int[] arr, int maxIndex, int from, int to, int other) {
        if (maxIndex < 0) {
            return 0;
        }
        int maxPos = arr[maxIndex];
        if (maxPos == other){
            // 不是最优解
            return -1;
        }else if (maxPos == from) {
            // 最大的盘在左边,说明目前在前半段
            return processForhanoiCpPlus(arr, maxIndex-1, from, other, to);
        }else {
            int rest = processForhanoiCpPlus(arr, maxIndex - 1, other, to, from);
            if (rest == -1) {
                return -1;
            }
            // 在后半段,这里递推公式留着
            return (1<<(maxIndex)) -1 + 1 + rest;
        }
    }

    public static void main(String[] args) {
        hanoiCPPlus(new int[]{3,3});
    }

    /**
     * 方法二：循环方式
     * 空间（1），时间（n）
     * @return
     */
    public static int hanoi2(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int index = array.length-1;
        int tmp = 0;
        int res = 0;
        while (index >= 0){
            if(array[index] != from && array[index] != to){
                return -1;
            }
            if(array[index] == to){
                // 最大盘已经到to了，说明最大盘能移动到这里已经走过了2^index了
                res += 1 << index;
                // 整体应该从中间往右边走
                tmp = from;
                from = mid;
            }else{
                // 最大盘在from,这层不需要计算步数,目标应该从from到mid
                tmp = to;
                to = mid;
            }
            // tmp赋值给mid
            mid = tmp;
            index--;
        }
        return res;
    }

}
