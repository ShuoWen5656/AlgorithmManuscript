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
            int col = array.length - 1;
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





}
