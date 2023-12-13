package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author swzhao
 * @date 2023/11/15 8:31 下午
 * @Discreption <>
 */
public class CloneGraph {





    public static Node solution(Node node) {

        Map<Node, Node> old2New = new HashMap<>();

        process(node, old2New);

        return old2New.get(node);
    }



    private static void process(Node node, Map<Node, Node> old2New) {
        if (node == null || old2New.containsKey(node)) {
            // 说明遍历过自己了
            return;
        }
        // 将自己复制
        Node newNode = new Node(node.val);
        // 先放入表示标记防止overflow
        old2New.put(node, newNode);
        List<Node> neighborsNew = new ArrayList<>();
        List<Node> neighbors = node.neighbors;
        for (Node n : neighbors) {
            process(n, old2New);
            neighborsNew.add(old2New.get(n));
        }
        newNode.neighbors = neighborsNew;
    }


    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
