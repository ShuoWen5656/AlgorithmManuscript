package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2023/11/8 7:52 下午
 * @Discreption <> 随机链表的复制
 */
public class CopyRandomList {


    public static void main(String[] args) {
    }

    /**
     * 回溯
     * @param head
     * @return
     */
    public static Node solution(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> old2New = new HashMap<>();
       return process2(head, old2New);
    }

    private static Node process2(Node head, Map<Node, Node> old2New) {
        if (head == null) {
            return head;
        }

        if (!old2New.containsKey(head)) {
            // 不存在创建新节点
            Node newNode = new Node(head.val);
            // 将新旧映射放入
            old2New.put(head, newNode);
            // 将各自的节点继续复制完成后返回头结点
            newNode.next = process2(head.next, old2New);
            newNode.random = process2(head.random, old2New);
        }
        return old2New.get(head);
    }


    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }



}
