package leetcode;

/**
 * @author swzhao
 * @date 2023/11/22 1:43 下午
 * @Discreption <> N皇后 II
 */
public class TotalNQueens {

    public static void main(String[] args) {
        System.out.println(solution(1));

    }


    public static int solution(int n) {
        // 结果放这里
        int[] res = new int[1];

        // 记录所有皇后的左斜线
        boolean[] rowLeft = new boolean[n];
        // 记录当前状态下，哪些列有皇后
        boolean[] col = new boolean[n];
        // 记录所有皇后的右斜线
        boolean[] rowRight = new boolean[n];
        process(0, rowLeft, col, rowRight, res, n);
        return res[0];
    }

    private static void process(int level, boolean[] rowLeft, boolean[] col, boolean[] rowRight, int[] res, int length) {
        if (level == length) {
            // 最后一层放置结束,n皇后归位，加入结果
            res[0]++;
            return;
        }
        // 循环当前行
        for (int i = 0; i < length; i++) {
            // 判断当前位置是否能够放置
            if (col[i] || rowLeft[i] || rowRight[i]) {
                continue;
            }
            // 留一下可能会溢出的值
            boolean l = rowLeft[0];
            boolean r = rowRight[rowRight.length-1];
            // 可以放置，更新下一层的数组状态
            col[i] = true;
            rowLeft[i] = true;
            rowRight[i] = true;
            change(rowLeft, true);
            change(rowRight, false);
            process(level + 1, rowLeft, col, rowRight, res, length);
            // 还原状态
            col[i] = false;
            change2(rowLeft, true);
            change2(rowRight, false);
            rowLeft[i] = false;
            rowRight[i] = false;
            rowLeft[0] = l;
            rowRight[rowRight.length-1] = r;
        }
    }

    /**
     * 数组反向还原
     * @param rowLeft
     * @param isLeft
     */
    private static void change2(boolean[] rowLeft, boolean isLeft) {
        if (isLeft) {
            for (int i = rowLeft.length-2; i >=0; i--) {
                rowLeft[i+1] = rowLeft[i];
            }
        }else {
            for (int i = 1; i < rowLeft.length; i++) {
                rowLeft[i-1] = rowLeft[i];
            }
        }
    }

    /**
     * 更新左右数组,左数组左移，右数组右移动
     * @param rowLeft
     * @param isLeft
     */
    private static void change(boolean[] rowLeft, boolean isLeft) {
        if (isLeft) {
            for (int i = 1; i < rowLeft.length; i++) {
                rowLeft[i-1] = rowLeft[i];
            }
            rowLeft[rowLeft.length - 1] = false;
        }else {
            for (int i = rowLeft.length-2; i >=0; i--) {
                rowLeft[i+1] = rowLeft[i];
            }
            rowLeft[0] = false;
        }
    }

}
