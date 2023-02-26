package classespackage.arrayAndMartrix;

/**
 * @author swzhao
 * @data 2022/6/11 9:09
 * @Discreption <> 在行列都排好序的矩阵中找数
 * 描述：行列都从小到大的矩阵中判断k是否在矩阵中
 */
public class IsContainsInSortMatrix {


    /**
     * 主方法
     * @param array
     * @param k
     * @return
     */
    public static boolean isContains(int[][] array, int k){
        try {
            int row = 0;
            int col = array[0].length - 1;
            while (row < array.length && col > -1){
                if(k == array[row][col]){
                    return true;
                }else if (k < array[row][col]){
                    // 往里面走
                    col--;
                }else {
                    // 往下面走
                    row++;
                }
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 二轮测试：在排好序的矩阵中找数
     * @param arr
     * @param k
     * @return
     */
    public static boolean isContainsCp1(int[][] arr, int k) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int row = arr.length - 1;
        int col = 0;
        while (row >= 0 && col <= arr[0].length - 1) {
            int cur = arr[row][col];
            if (cur == k) {
                return true;
            }else if (cur > k) {
                // 当前行不会存在k
                row--;
            }else {
                // 当前列不会存在k
                col++;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(isContainsCp1(new int[][]{
                {0,1,2,5},
                {2,3,4,7},
                {4,4,4,8},
                {5,7,7,9}
        }, 7));
    }




}
