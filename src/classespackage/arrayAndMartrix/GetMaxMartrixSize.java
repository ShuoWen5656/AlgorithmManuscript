package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/22 21:57
 * @Discreption <>边界都是1的最大正方形大小
 * NxN的矩阵求最大的边界都是1的矩阵边长
 */
public class GetMaxMartrixSize {


    /**
     * 主方法
     * @param array
     */
    public static int getMaxSize(int[][] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            int r = array.length;
            int c = array[0].length;
            // right[i][j] 表示从ij位置开始往右边最长有多少个1
            int[][] right = new int[r][c];
            // down[i][j] 表示从ij位置开始往下最长有多少个1
            int[][] down = new int[r][c];
            // 预处理array
            preDealArray(array, right, down);
            // 从最小的边开始计算每一种大小的矩阵是否有满足条件的，满足的直接放回size即可
            for (int size = Math.min(array.length, array[0].length); size >= 0; size--){
                // 根据right和down判断是否存在size大小的1
                if (hasSizeOfBorder(size, right, down)){
                    return size;
                }
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 判断是否存在size大小的边界为1的矩阵
     * @param size
     * @param right
     * @param down
     * @return
     */
    private static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i < right.length-size+1; i++){
            for (int j = 0; j < right[0].length-size+1; j++){
                if (right[i][j] >= size && down[i][j] >= size
                        && right[i+size-1][j] >= size
                        && down[i][j+size-1] >= size){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 预处理array,从最后一个字符开始
     * @param array
     * @param right
     * @param down
     */
    private static void preDealArray(int[][] array, int[][] right, int[][] down) {
        int r = array.length;
        int c = array[0].length;
        // r-1,c-1开始
        if (array[r-1][c-1] == 1){
            right[r-1][c-1] = 1;
            down[r-1][c-1] = 1;
        }else {
            right[r-1][c-1] = 0;
            down[r-1][c-1] = 0;
        }
        // 右边界
        for (int i = r-2; i >= 0; i--){
            // right
            if (array[i][c-1] == 1){
                right[i][c-1] = 1;
                down[i][c-1] = down[i-1][c-1] + 1;
            }else {
                right[i][c-1] = 0;
                down[i][c-1] = 0;
            }
        }

        // 下边界
        for (int j = c-2; j >= 0; j--){
            if (array[r-1][j] == 1){
                down[r-1][j] = 1;
                right[r-1][j] = right[r-1][j-1] + 1;
            }
        }
        // 中间开始
        for (int i = r-2; i >= 0; i--){
            for (int j = c-2; j >=0; j--){
                if (array[i][j] == 1){
                    right[i][j] = right[i][j+1] + 1;
                    down[i][j] = down[i+1][j] + 1;
                }else {
                    right[i][j] = 0;
                    down[i][j] = 0;
                }
            }
        }
    }




}
