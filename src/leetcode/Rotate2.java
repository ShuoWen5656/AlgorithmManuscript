package leetcode;

/**
 * @author swzhao
 * @date 2023/11/1 8:06 下午
 * @Discreption <> 旋转图像
 */
public class Rotate2 {

    public static void main(String[] args) {


    }


    public static void solution(int[][] matrix) {
        int len = matrix.length;

        int count = (len & 1) == 1 ? len/2 + 1 : len / 2;

        for (int i = 0; i < len/2; i++) {
            for (int j = 0; j < (len + 1)/2 ; j ++) {
                int tem = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - j][i];
                matrix[len - 1 - j][i] = matrix[len - i - 1][len - j - 1];
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
                matrix[j][len - i - 1] = tem;
            }
        }

    }




}
