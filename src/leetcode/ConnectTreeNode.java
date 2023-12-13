package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author swzhao
 * @data 2023/11/11 15:35
 * @Discreption <> 填充每个节点的下一个右侧节点指针 II
 */
public class ConnectTreeNode {






    public Node solution(Node root) {
        if (root == null) {
            return null;
        }
        // 双端队列层序便利
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        Node last = root;
        while (!deque.isEmpty()) {
            Node cur = deque.pollFirst();
            if (cur.left != null) {
                deque.addLast(cur.left);
            }
            if (cur.right != null) {
                deque.addLast(cur.right);
            }
            if (cur == last) {
                // 说明这层结束了,新的last为队尾
                last = deque.peekLast();
            }else {
                // 不是最后一个元素，则将next指针指向对头的元素
                cur.next = deque.peekFirst();
            }

        }
        return root;
    }




    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
