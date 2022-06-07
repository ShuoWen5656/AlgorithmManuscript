package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/7 19:32
 * @Discreption <> “之”字形打印矩阵
 */
public class PrintMartrixZigZag {

    public static void printMartrix(int[][] array){
        try {
            // 初始化两组坐标（topR, topC） (downR, downC)
            int topR = 0, topC = 0, downR = 0, downC = 0;
            // 控制方向 true斜向上走，false，斜向下
            boolean toWard = true;
            // top横向走，走到尽头再竖着走，down竖着走走到尽头再横向走
            while (topR != downR || topR != array.length-1){
                if(toWard){
                    // 斜向上
                    int temR = downR, temC = downC;
                    while (temR <= topR && temC <= topC){
                        System.out.println(array[temR][temC]);
                        temC++;
                        temR--;
                    }
                }else {
                    // 斜向下
                    int temR = topR, temC = topC;
                    while (temR >= downR && temC >= downC){
                        System.out.println(array[temR][temC]);
                        temC--;
                        temR++;
                    }
                }
                // 打印完毕后进行下一个斜线
                if(topC != array[0].length-1){
                    // 还没到尽头
                    topC++;
                }else {
                    topR++;
                }
                if(downR != array.length - 1){
                    downR++;
                }else{
                    downC++;
                }
                // 方向改变
                toWard = !toWard;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
