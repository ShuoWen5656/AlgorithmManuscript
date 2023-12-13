package leetcode;

import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/23 11:25 下午
 * @Discreption <> 合并 K 个升序链表
 */
public class MergeKLists {


    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = new ListNode(1);
        listNodes[0].next = new ListNode(4);
        listNodes[0].next.next = new ListNode(5);
        listNodes[1] = new ListNode(1);
        listNodes[1].next = new ListNode(3);
        listNodes[1].next.next = new ListNode(4);
        listNodes[2] = new ListNode(2);
        listNodes[2].next = new ListNode(6);

        solution(listNodes);
    }

  public static ListNode solution(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return process(lists, 0, lists.length-1);
  }

    private static ListNode process(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start > end) {
            return null;
        }
        int midIndex = (start + end)/2;

        ListNode left = process(lists, start, midIndex);

        ListNode right = process(lists, midIndex + 1, end);

        return merge(left, right);

    }



    /**
     * merge两个单链表排序并返回新的头结点
     * @param head
     * @param next
     * @return
     */
    private static ListNode merge(ListNode head, ListNode next) {
        if (head == null) {
            return next;
        }
        if (next == null) {
            return head;
        }
        // 假节点站位
        ListNode res = new ListNode(0);
        ListNode resPre = res;
        ListNode n1 = head, n2 = next;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                resPre.next = n1;
                n1 = n1.next;
            }else {
                resPre.next = n2;
                n2 = n2.next;
            }
            resPre = resPre.next;
        }
        if (n1 == null) {
            resPre.next = n2;
        }else {
            resPre.next = n1;
        }
        return res.next;
    }
}
