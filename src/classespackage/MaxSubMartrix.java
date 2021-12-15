package classespackage;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2021/12/8 9:58 下午
 * @Discreption <>最大子矩阵大小
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
            // 挖比自己大的或者相等的，直到遇到比自己小的
            while (!stack.isEmpty() && height[i] <= stack.peek()){
                Integer topIndex = stack.pop();
                max = Math.max(max, (i - stack.peek() - 1) * height[topIndex]);
            }
            // 挖完之后把自己放进去,放的不是值，是角标
            stack.push(i);
        }
        // 结束之后如果stack还有值，则继续执行
        while (!stack.isEmpty()){
            int topIndex = stack.pop();
            int k = stack.isEmpty()? -1 : stack.peek();
            // 这里的i - k - 1 里面的i不会再动了，因为永远都是数组右边界
            max = Math.max((height.length - k - 1) * height[topIndex] , max);
        }
        return max;
    }


}
