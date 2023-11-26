package leetcode;

/**
 * @author swzhao
 * @date 2023/11/26 1:33 下午
 * @Discreption <> 搜索二维矩阵
 */
public class SearchMatrix {


    public static void main(String[] args) {

    }


    public static boolean solution(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        boolean res = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] > target || matrix[i][matrix[0].length-1] < target) {
                continue;
            }
            res = res ? res : find(matrix[i], target);
        }
        return res;
    }

    private static boolean find(int[] curRow, int target) {
        boolean res = false;
        int left = 0;
        int right = curRow.length;
        while (left < right) {
            int mid = (left + right)/2;
            if (target == curRow[mid]) {
                res = true;
                break;
            }else if (target > curRow[mid]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return res;
    }


}
