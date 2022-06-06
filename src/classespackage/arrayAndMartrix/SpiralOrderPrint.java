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


}
