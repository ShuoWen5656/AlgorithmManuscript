package classespackage;

import java.util.LinkedList;

/**
 * @author swzhao
 * @date 2021/12/15 10:20 下午
 * @Discreption <>数组array，获取满足最大值-最小值<num的子数组数量
 */
public class getNumFromArray {

    LinkedList<Integer> qmax;
    LinkedList<Integer> qmin;

    public getNumFromArray(LinkedList<Integer> qmax, LinkedList<Integer> qmin) {
        this.qmax = new LinkedList<>();
        this.qmin = new LinkedList<>();
    }


    public Integer getMinNum(Integer[] array, int num){
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



}
