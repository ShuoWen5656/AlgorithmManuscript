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


    /**
     * 二轮测试：之字形打印矩阵
     * @param arr
     */
    public static void printMartrixZCp1(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int len1 = arr.length;
        int len2 = arr[0].length;
        // 循环次数
        int count = len1 + len2 - 1;
        int index = 0;
        // 方向：true为向上
        boolean forward = true;
        // 初始化起点,start[0]纵坐标i,start[1]横坐标j
        int[] start = new int[]{0,0};
        while (index < count) {
            process(arr, start, forward);
            // 计算下一个起点
            if (forward) {
                if (start[1] + 1 >= len2) {
                    start[0] = start[0]+1;
                }else if (start[0] - 1 < 0) {
                    start[1] = start[1] + 1;
                }
            }else {
                // 向下
                if (start[0] + 1 >= len1) {
                    start[1] = start[1]+1;
                }else if (start[1] - 1 < 0) {
                    start[0] = start[0] + 1;
                }
            }
            // 转换方向
            forward = !forward;
            index ++;
        }
    }

    /**
     * 从arr的ij开始，按照forward方向打印到边界
     * @param arr
     * @param forward
     */
    private static void process(int[][] arr, int[] start, boolean forward) {
        int len1 = arr.length;
        int len2 = arr[0].length;
        while (start[0] < len1 && start[0] >=0 && start[1] >= 0 && start[1] < len2) {
            System.out.print(arr[start[0]][start[1]] + "   ");
            if (forward) {
                if (start[0] - 1 < 0 || start[1] + 1 >= len2) {
                    // 越界退出
                    break;
                }
                start[0] = start[0] - 1;
                start[1] = start[1] + 1;
            }else {
                if (start[0] + 1 >= len1 || start[1] - 1 < 0) {
                    // 越界退出
                    break;
                }
                start[0] = start[0] + 1;
                start[1] = start[1] - 1;
            }
        }
    }


    public static void main(String[] args) {
        printMartrixZCp1(new int[][] {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        });
    }

}
