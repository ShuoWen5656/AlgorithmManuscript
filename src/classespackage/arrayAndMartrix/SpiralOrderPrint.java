package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/5 9:28
 * @Discreption <> 转圈打印矩阵
 */
public class SpiralOrderPrint {

    /**
     * 主方法：一层一层打印，每一层使用左上角坐标和右下角坐标展示
     * @param array
     */
    public static void spiralOrderPrint(int[][] array){
        try {
            int topR = 0, topC = 0;
            int downR = array.length-1, downC = array[0].length-1;
            // 从外往内进行顺时针遍历
            while (topR <= downR && topC <= downC){
                process(array, topR++, topC++, downR--, downC--);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 适用于非正方形的遍历
     * @param array
     * @param topR
     * @param topC
     * @param downR
     * @param downC
     */
    private static void process(int[][] array, int topR, int topC, int downR, int downC) {
        if (topR == downR){
            // 只有一行，打印一行即可
            while (topC <= downC){
                System.out.println(array[topR][topC++]);
            }
        }else if (topC == downC){
            // 只有一列，打印一列即可
            while (topR <= downR){
                System.out.println(array[topR++][topC]);
            }
        }else {
            int curC = topC;
            // 矩阵，从左上角开始打印
            while (curC <= downC){
                // 横向打印
                System.out.println(array[topR][curC++]);
            }
            // +1是排除角落重复打印
            curC = topR+1;
            while (curC <= downR){
                // 纵向打印
                System.out.println(array[curC++][downC]);
            }
            curC = downC-1;
            while (curC >= topC){
                System.out.println(array[downR][curC--]);
            }
            curC = downR-1;
            // 这里需要排除起点，所以没有=号
            while (curC > topR){
                System.out.println(array[curC--][topC]);
            }
        }


    }


    /**
     * 二轮测试：转圈打印
     */
    public static void spiralOrderPrintCp1(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        // 横向
        int len = arr.length;
        // 纵向
        int len2 = arr[0].length;
        // 打印边界
        int boundR = len/2 + 1;
        int boundL = len2/2 + 1;
        int i = 0;
        int j = 0;
        while (i < boundR && j < boundL) {
            processCp(arr , i, j, len2, len);
            len-=2;
            len2-=2;
            i++;
            j++;
        }
    }

    /**
     * 二轮测试：打印一圈，支持长方形
     * @param arr
     * @param i 起点横坐标
     * @param j 起点纵坐标
     * @param wide 宽度
     * @param height 高度
     */
    private static void processCp(int[][] arr, int i, int j, int wide, int height) {
        int i1 = i;
        int j1 = j;
        int wideBound = j + wide;
        int heightBound = i + height;
        // 横向
        while (j1 < wideBound) {
            System.out.println(arr[i1][j1]);
            j1++;
        }
        // j1此时超出去一个
        j1--;
        i1++;
        // 纵向
        while (i1 < heightBound) {
            System.out.println(arr[i1][j1]);
            i1++;
        }
        i1--;
        j1--;
        // 横向回
        while (j1 >= j) {
            System.out.println(arr[i1][j1]);
            j1--;
        }
        j1++;
        i1--;
        // 纵向回
        while (i1 > i) {
            System.out.println(arr[i1][j1]);
            i1--;
        }
    }


    public static void main(String[] args) {
        spiralOrderPrintCp1(new int[][]{
                {1,2,3},
                {5,6,7},
                {9,10,11},
                {13,14,15}
        });
    }

}
