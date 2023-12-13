package classespackage.stackAndQueue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.jar.JarEntry;

/**
 * @author swzhao
 * @date 2021/12/8 9:58 下午
 * @Discreption <>求最大子矩阵的大小
 */
public class MaxSubMartrix {

    /**
     * 给一个矩阵，里面都是0，1，返回1组成的最大子矩阵
     * @param martrix
     * @return
     */
    public int maxRecSize(int[][] martrix){
        // 遍历每一行，得出当前最大值
        int maxInt = 0;
        // null 或者行列有一个是0的直接返回
        if(martrix == null || martrix.length == 0 || martrix[0].length == 0){
            return maxInt;
        }
        // 实时记录当前行的柱形高度
        int[] height = new int[martrix[0].length];
        // 遍历每一行
        for (int i = 0; i < martrix.length; i++){
            // 遍历每一列，每一列如果是0，则当前列柱初始化成0
            for (int j = 0; j < martrix[0].length; j++){
                height[j] = martrix[i][j] == 0? 0 : height[j] + 1;
            }
            // 加完之后获取当前层最大矩阵值
            maxInt = Math.max(getMaxForCurrentLine(height), maxInt);
        }
        return maxInt;
    }

    /**
     * 获取当前层的最大值
     * @param height
     * @return
     */
    private int getMaxForCurrentLine(int[] height){
        if(height == null || height.length == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < height.length; i++){
            // 栈保持栈顶到栈底 大-小，相等必须进去，否则会影响比该值大的值计算
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]){
                Integer topIndex = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                // 这里在多个相等的情况下可能计算的不对，但是相等情况下当前计算的值也不会是最后的答案随意无关紧要
                max = Math.max(max, (i - k - 1) * height[topIndex]);
            }
            // 挖完之后把自己放进去,放的不是值，是角标
            stack.push(i);
        }
        // 结束之后如果stack还有值，说明右边没有比自己小的值了，就按照右边的边界减，则继续执行
        while (!stack.isEmpty()){
            int topIndex = stack.pop();
            int k = stack.isEmpty()? -1 : stack.peek();
            // 这里的i - k - 1 里面的i不会再动了，因为永远都是数组右边界
            max = Math.max((height.length - k - 1) * height[topIndex] , max);
        }
        return max;
    }




    public static int getMaxSubMaxSize(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0){
            return 0;
        }
        int[] helpeArr = new int[arr[0].length];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                helpeArr[j] = arr[i][j] == 0? 0: helpeArr[j] + arr[i][j];
            }
            max = Math.max(max, getMaxValueFromHelperArr(helpeArr));
        }
        return max;
    }

    private static int getMaxValueFromHelperArr(int[] helpeArr) {
        Stack<Integer> stack = new Stack<>();
        // linked为了好debug带顺序看,key 当前index，value为当前index左边第一个比自己小的数的index
        Map<Integer, Integer> leftMap = new LinkedHashMap<>();
        Map<Integer, Integer> rightMap = new LinkedHashMap<>();
        for (int i = 0; i < helpeArr.length; i++){
            while (!stack.isEmpty() && helpeArr[i] <= helpeArr[stack.peek()]){
                stack.pop();
            }
            if (stack.isEmpty()){
                leftMap.put(i, null);
            }else {
                leftMap.put(i, stack.peek());
            }
            stack.push(i);
        }
        stack.clear();
        for (int j = helpeArr.length-1; j >= 0; j--){
            while (!stack.isEmpty() && helpeArr[j] <= helpeArr[stack.peek()]){
                stack.pop();
            }
            if (stack.isEmpty()){
                rightMap.put(j, null);
            }else {
                rightMap.put(j, stack.peek());
            }
            stack.push(j);
        }
        int max = Integer.MIN_VALUE;
        for (int k = 0; k < helpeArr.length; k++){
            Integer left = leftMap.get(k);
            Integer right = rightMap.get(k);
            if (left == null && right == null){
                // 左右都没比它小的，就直接乘以长度即可
                max = Math.max(max, helpeArr[k] * helpeArr.length);
            }else if (left == null || right == null){
                if (left == null){
                    max = Math.max(max, helpeArr[k] * right);
                }else{
                    max = Math.max(max, helpeArr[k] * (helpeArr.length - left));
                }
            }else {
                max = Math.max(max, (right-left-1) * helpeArr[k]);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] ints = new int[][]{{1,0,1,1}, {1,1,1,1}, {1,1,1,0}};
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i);
            }
            System.out.println();
        }

        MaxSubMartrix maxSubMartrix = new MaxSubMartrix();
        int i = maxSubMartrix.maxRecSize(ints);

        System.out.println(i);

        // 我的方法
        //int maxSubMaxSize = getMaxSubMaxSize(ints);
        //System.out.println(maxSubMaxSize);

    }



}
