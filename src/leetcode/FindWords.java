package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author swzhao
 * @date 2023/11/19 12:04 下午
 * @Discreption <> 单词搜索 II
 */
public class FindWords {

    public static void main(String[] args) {
        solution(new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        }, new String[]{"oath","pea","eat","rain"});
    }




    public static List<String> solution(char[][] board, String[] words) {
        Trie trie = new Trie();
        // 形成前缀树便于查找
        for (String s : words){
            trie.insert(s);
        }

        // 结果集合
        Set<String> res = new HashSet<>();

        // 遍历每一个点作为出发点，将匹配到的结果集合放入res
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, "",trie.node.nodes, res);
            }
        }

        return new ArrayList<>(res);
    }

    /**
     * 以[row, col]点出发，判断是否能够存在对应单词放入res
     * @param board
     * @param row
     * @param col
     * @param nodes
     * @param res
     */
    private static void dfs(char[][] board, int row, int col, String perFix, Trie.Node[] nodes, Set<String> res) {
        if (board[row][col] == '#') {
            // 已经遍历过的不再遍历
            return;
        }
        char curChar = board[row][col];
        int curIndex = curChar - 'a';
        if (nodes[curIndex] == null || nodes[curIndex].pass == 0) {
            // word中不存在当前节点的前缀，直接剪掉
            return;
        }
        // 当前str
        String curStr = perFix + new String(new char[]{board[row][col]});
        if (nodes[curIndex] != null && nodes[curIndex].tail > 0) {
            // 存在以当前字符结尾的情况
            res.add(curStr);
        }
        if (nodes[curIndex] != null && nodes[curIndex].pass > 0) {
            // 说明还有单词前缀匹配可以继续递归
            // 1、将当前点标记
            board[row][col] = '#';
            // 上
            if (row - 1 >= 0 && board[row - 1][col] != '#') {
                dfs(board, row - 1, col, curStr, nodes[curIndex].nodes, res);
            }
            // 下
            if (row + 1 < board.length && board[row + 1][col] != '#') {
                dfs(board, row + 1, col, curStr, nodes[curIndex].nodes, res);
            }
            // 左
            if (col - 1 >= 0 && board[row][col-1] != '#') {
                dfs(board, row, col-1, curStr, nodes[curIndex].nodes, res);
            }
            // 右
            if (col + 1 < board[0].length && board[row][col+1] != '#') {
                dfs(board, row, col+1, curStr, nodes[curIndex].nodes, res);
            }
        }
        // 将当前点还原
        board[row][col] = curChar;
    }



}
