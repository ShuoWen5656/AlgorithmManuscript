package classespackage;

import java.util.LinkedList;

/**
 * @Author swzhao
 * @Date 2021/11/20 21:32
 * @Discription<> 窗口最大值：一个数组和一个窗口，窗口移动到数组尾部，打印出每一步的窗口最大值
 */
public class MaxWindow {

    /**
     * 返回每一步的窗口最大值
     * @param array 数组
     * @param windowSize 最大值
     * @return
     */
    public static int[] getMaxWindow(int[] array, int windowSize){
        try{
            // 拦截非法参数
            if(array == null || windowSize < 1 || array.length < windowSize){
                return null;
            }
            // 返回结果
            int[] maxArray = new int[array.length - windowSize + 1];
            // 维护最大值队列,只放下角标
            LinkedList<Integer> maxQueue = new LinkedList<>();
            int index = 0;
            for (int i = 0; i < array.length; i++){
                int curr = array[i];
                // 首先从队列尾巴比较开始，将比自己小的全部挖出去,直到空为止，放自己
                while (!maxQueue.isEmpty() && array[maxQueue.peekLast()] <= curr ){
                    // 挖出来比当前小的值
                    maxQueue.pollLast();
                }
                // 直到空或者把比自己小的值都挖出来之后，放入自己到队列尾巴
                maxQueue.addLast(i);
                // 判断队列头是否已经不在窗口内（因为窗口每一次循环都判断是否在窗口内，所以每一次只判断是否是窗口前一个值即可，不可能会有前前个值，不然一定会被上一次循环查到）
                if(maxQueue.peekFirst() == i - windowSize){
                    // 当前最大值过期了，需要弹出
                    maxQueue.pollFirst();
                }
                // 该输出最大值到结果了：先判断是否窗口已经走过了windowSize个变量了，没有走过则不用输出
                if(i >= windowSize - Constants.NUM_1){
                    // 放入当前队头
                    maxArray[index++] = array[maxQueue.peekFirst()];
                }
            }
            return maxArray;
        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }
    }


}
