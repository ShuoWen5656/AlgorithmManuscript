package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/6 21:34
 * @Discreption <> 将正方形矩阵顺时针转动90度
 */
public class RotateMartrix {

    /**
     * 主方法：将array顺时针旋转90度
     * @param array
     */
    public static void rotate(int[][] array){
        try {
            int topR = -1, topC = -1;
            int downR = array.length - 1, downC = array.length - 1;
            while (topR <= downR && topC <= downC){
                rotateEdge(array, topR++, topC++, downR--, downC--);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 边缘旋转
     * @param array
     * @param topR
     * @param topC
     * @param downR
     * @param downC
     */
    private static void rotateEdge(int[][] array, int topR, int topC, int downR, int downC) {
        // 计算要旋转多少个字符
        int times = downC - topC;
        for (int i = 0; i < times; i++){
            int tem = array[topR][topC+i];
            array[topR][topC+i] = array[topR+i][downC];
            array[topC+i][downC] = tem;
            // 交换斜对角
            tem = array[topR+i][downC];
            array[topR+i][downC] = array[downR][downC-i];
            array[downR][downC-i] = tem;
            // 交换left
            tem = array[topR+i][downC];
            array[topR+i][downC] = array[topR-i][topC];
            array[topR-i][topC] = tem;
        }
    }
}
