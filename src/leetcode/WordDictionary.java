package leetcode;

/**
 * @author swzhao
 * @date 2023/11/19 10:45 上午
 * @Discreption <> 添加与搜索单词 - 数据结构设计
 */
public class WordDictionary {

    public static void main(String[] args) {

        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        wordDictionary.search("a");
        wordDictionary.search(".at");
        wordDictionary.addWord("bat");
        wordDictionary.search(".at");
        wordDictionary.search("an.");
        wordDictionary.search("a.d.");
        wordDictionary.search("b.");
        wordDictionary.search("a.d");
        wordDictionary.search(".");

    }


    Node node;


    public WordDictionary() {
        node = new Node();
    }

    public void addWord(String word) {
        char[] chars = word.toCharArray();
        Node[] nodes = node.nodes;
        for (int i = 0; i < chars.length; i++) {
            // 更新当前节点
            int curIndex = chars[i] - 'a';
            if (nodes[curIndex] == null) {
                nodes[curIndex] = new Node();
            }
            Node node = nodes[curIndex];
            node.pass++;
            if (i == chars.length-1) {
                node.tail++;
            }
            // 下一个
            nodes = node.nodes;
        }
        // 更新根节点
        node.pass++;
        if (word.equals("")) {
            node.tail++;
        }
    }

    public boolean search(String word) {
        char[] chars = word.toCharArray();
        return dfs(chars, node.nodes, 0);
    }

    /**
     * 深度遍历计算是否存在当前字符串
     * @param chars 当前字符串数组
     * @param nodes 当前层node数组
     * @param i 当前计算的位置i
     * @return
     */
    private boolean dfs(char[] chars, Node[] nodes, int i) {
        if (i > chars.length - 1) {
            // 越界
            return false;
        }
        if (chars[i] != '.') {
            // 说明是字母
            int index = chars[i] - 'a';
            Node nextNode = nodes[index];
            if (nextNode != null && i == chars.length-1 && nextNode.tail > 0) {
                // 存在结尾的
                return true;
            }else if (nextNode == null || nextNode.pass == 0 || (i == chars.length-1 && nextNode.tail <= 0)){
                // 不存在当前节点
                return false;
            }else {
                // 当前节点验证通过,验证下一个节点
                return dfs(chars, nextNode.nodes, i+1);
            }
        }else {
            // 当前点为. 需要遍历所有pass不为0的节点，只要有一个分支为true说明就存在
            boolean flag = false;
            for (int j = 0; j < nodes.length; j++) {
                if (flag) {
                    // 已经有分支为true就不再计算了
                    break;
                }
                if (nodes[j] == null || nodes[j].pass == 0) {
                    // 说明当前节点不需要进行dfs
                    continue;
                }
                if (i == chars.length - 1 && nodes[j].tail > 0) {
                    // 结尾值为. 并且树有结尾
                    return true;
                }
                flag = dfs(chars, nodes[j].nodes, i+1);
            }
            return flag;
        }
    }


    class Node {

        public Node() {
            this.nodes = new Node[26];
            this.pass = 0;
            this.tail = 0;
        }

        /**
         * 26个字母的map
         */
        Node[] nodes;

        /**
         * 经过这里的数量
         */
        int pass;

        /**
         * 以当前char结尾的数量
         */
        int tail;
    }
    
}
