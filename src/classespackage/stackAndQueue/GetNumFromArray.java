package classespackage.stackAndQueue;

import sun.java2d.pipe.AATextRenderer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author swzhao
 * @date 2021/12/15 10:20 下午
 * @Discreption <>最大值减去最小值小于等于num的子数组数量
 *     数组array，获取满足最大值-最小值<num的子数组数量
 */
public class GetNumFromArray {

    LinkedList<Integer> qmax;
    LinkedList<Integer> qmin;

    public GetNumFromArray() {
        this.qmax = new LinkedList<>();
        this.qmin = new LinkedList<>();
    }


    public Integer getMinNum(int[] array, int num){
        try{
            // 头
            int i = 0;
            // 尾
            int j = 0;
            // 结果数量
            int res = 0;
            if(array.length == 0 || array == null){
                return 0;
            }
            // 头尾遍历
            while (i < array.length){
                while (j < array.length){
                    // 维护最小值队列
                    while (!qmin.isEmpty() && array[j] < qmin.getLast()){
                        // 挖出比自己大的
                        qmin.pollLast();
                    }
                    qmin.addLast(array[j]);
                    // 维护最大值队列
                    while (!qmax.isEmpty() && array[j] > qmax.getLast()){
                        qmax.pollLast();
                    }
                    qmax.addLast(array[j]);
                    // 判断此时的最大值和最小值差,如果满足条件，则子数组都满足，不满足则以此数组为数组的父数组都不满足，j就不用往下走了
                    if(qmax.peekFirst() - qmin.peekFirst() > num){
                        // 不满足条件了
                        break;
                    }
                    j++;
                }
                // i需要增加时，需要判断i是否是两个队列的最值，如果是，则已经过期
                if(array[i] == qmax.peekFirst()){
                    qmax.pollFirst();
                }
                if(array[i] == qmin.peekFirst()){
                    qmin.pollFirst();
                }
                // 这一轮一共 j- i 个子数组满足条件
                res += j - i;
                i++;
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public static int getNumByArr(int[] arr, int num){
        Deque<Integer> maxq = new LinkedList<>();
        Deque<Integer> minq = new LinkedList<>();
        int res = 0;
        if (arr == null || arr.length == 0){
            return 0;
        }
        int j = 0;
        for (int i = 0;i < arr.length;i++){
            for (; j < arr.length; j++){
                while (!maxq.isEmpty() && arr[j] > arr[maxq.getLast()]){
                    maxq.pollLast();
                }
                while (!minq.isEmpty() && arr[j] < arr[minq.getLast()]){
                    minq.pollLast();
                }
                maxq.offer(j);
                minq.offer(j);

                if (arr[maxq.getFirst()] - arr[minq.getFirst()] > num){
                    break;
                }
            }
            if (arr[i] == arr[maxq.getFirst()]){
                maxq.pollFirst();
            }
            if (arr[i] == arr[minq.getFirst()]){
                minq.pollFirst();
            }
            res+=j-i;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] ints = {3, 5, 4, 1, 2};
        int numByArr = getNumByArr(ints, 3);
        //GetNumFromArray getNumFromArray = new GetNumFromArray();
        //Integer numByArr = getNumFromArray.getMinNum(ints, 3);
        System.out.println(numByArr);
    }

}
