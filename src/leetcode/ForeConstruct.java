package leetcode;

/**
 * @author swzhao
 * @date 2023/11/24 11:02 下午
 * @Discreption <> 建立四叉树
 */
public class ForeConstruct {

    public static void main(String[] args) {
        solution(new int[][]{
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0}
        });
    }


    public static Node solution(int[][] grid) {
        return process(grid, 0, 0,grid.length);
    }

    private static Node process(int[][] grid, int row, int col, int length) {
        if (length == 1) {
            // 长度为1时，直接返回原来的值即可
            return new Node(grid[row][col] != 0, true);
        }
        int mid = length/2;
        // 左上
        Node onLeft = process(grid, row, col, mid);
        // 右上
        Node onRight = process(grid, row, col+mid, mid);
        // 左下
        Node downLeft = process(grid, row + mid, col, mid);
        // 右下
        Node downRight = process(grid, row + mid, col + mid, mid);
        // 如果全都是叶子节点，并且都是一个值才能，当前点才能作为left，否则就需要带四个叶子
        if (onLeft.isLeaf && onRight.isLeaf && downLeft.isLeaf && downRight.isLeaf
                && (onLeft.val == onRight.val && onLeft.val == downRight.val && onLeft.val == downLeft.val)) {
            return new Node(onLeft.val, true);
        }
        // 连接并返回
        Node cur = new Node(onLeft.val, false);
        cur.topLeft = onLeft;
        cur.topRight = onRight;
        cur.bottomLeft = downLeft;
        cur.bottomRight = downRight;
        return cur;
    }


    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    };


}
