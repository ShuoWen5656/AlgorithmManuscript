package leetcode;

/**
 * @author swzhao
 * @date 2023/11/18 3:34 下午
 * @Discreption <>实现 Trie (前缀树)
 */
public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("swzhao");
        trie.search("sw");
        trie.search("swzhao");
        trie.startsWith("sw");
    }



    Node node;


    public Trie() {
        node = new Node();
    }

    public void insert(String word) {
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
        Node[] nodes = node.nodes;
        for (int i = 0; i < chars.length; i++) {
            int curIndex = chars[i] - 'a';
            if (nodes[curIndex] == null) {
                return false;
            }
            if (nodes[curIndex].pass == 0) {
                return false;
            }
            if (i == chars.length - 1 && nodes[curIndex].tail == 0) {
                return false;
            }
            nodes = nodes[curIndex].nodes;
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Node[] nodes = node.nodes;
        for (int i = 0; i < chars.length; i++) {
            int curIndex = chars[i] - 'a';
            if (nodes[curIndex] == null) {
                return false;
            }
            if (nodes[curIndex].pass == 0) {
                return false;
            }
            nodes = nodes[curIndex].nodes;
        }
        return true;
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


