package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

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


    /**
     * 二轮测试：旋转n*n矩阵
     * @param arr
     */
    public static void retateCp1(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int len = arr.length;
        int bound = len/2 + 1;
        int i = 0;
        while (i < bound) {
            processCp1(arr, i, len);
            len-=2;
            i++;
        }




    }

    /**
     * 二轮测试：旋转边长
     * @param arr
     * @param start
     * @param len
     */
    private static void processCp1(int[][] arr, int start, int len) {
        // 找出四个边界
        int i1 = start;
        int j1 = start;
        int i2 = start;
        int j2 = start + len - 1;
        int i3 = start + len - 1;
        int j3 = start + len - 1;
        int i4 = start + len - 1;
        int j4 = start;
        // 开始旋转
        int count = 0;
        while (count < len-1) {
            int tem = arr[i1][j1];
            int tem2 = arr[i2][j2];
            arr[i2][j2] = arr[i1][j1];
            tem = arr[i3][j3];
            arr[i3][j3] = tem2;
            tem2 = arr[i4][j4];
            arr[i4][j4] = tem;
            tem = arr[i1][j1];
            arr[i1][j1] = tem2;
            // 改动坐标
            j1++;
            i2++;
            j3--;
            i4--;
            count++;
        }
    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        retateCp1(arr);

        CommonUtils.printArr2D(arr);


    }

}
