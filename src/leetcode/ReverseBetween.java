package leetcode;

import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2023/11/8 8:05 下午
 * @Discreption <> 反转链表
 */
public class ReverseBetween {


    public static void main(String[] args) {
        ListNode head = null;
        ListNode cur = null;
        for (int i = 1; i <= 5; i++) {
            if (head == null) {
                head = new ListNode(i);
                cur = head;
                continue;
            }else {
                cur.next = new ListNode(i);
                cur = cur.next;
            }
        }
        solution(head, 2,4);
    }

    public static ListNode solution(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        ListNode leftNode = head;
        // 头结点不计算
        ListNode leftBefore = left == 1 ? null : head;
        ListNode rightNode = head;
        ListNode cur = head;
        int count = 0;
        // 计算出left和right指针以及left左边的指针，如果left是head节点则为null
        while (cur != null) {
            if (leftBefore != null) {
                if (count == left - 2) {
                    leftBefore = cur;
                }
            }
            if (count == left-1) {
                leftNode = cur;
            }
            if (count == right - 1) {
                rightNode = cur;
            }
            count++;
            cur = cur.next;
        }
        ListNode rightAfter = rightNode.next;
        // 开始翻转
        cur = leftNode;
        ListNode next = leftNode.next;
        while (next != rightAfter) {
            ListNode tem = next.next;
            next.next = cur;
            cur = next;
            next = tem;
        }
        // 续接
        if (leftBefore == null) {
            // 头结点
            head = rightNode;
        }else {
            leftBefore.next = rightNode;
        }
        leftNode.next = rightAfter;
        return head;
    }



    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
