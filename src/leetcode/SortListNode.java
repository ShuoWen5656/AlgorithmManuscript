package leetcode;

/**
 * @author swzhao
 * @date 2023/11/23 10:07 下午
 * @Discreption <> 排序链表
 */
public class SortListNode {



    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(19);
        head.next.next = new ListNode(14);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(-3);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next = new ListNode(11);
        head.next.next.next.next.next.next.next.next = new ListNode(15);

        solution(head);
    }

  public static ListNode solution(ListNode head) {
       return process(head);
  }

    private static ListNode process(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            // 两个节点
            ListNode next = head.next;
            head.next = null;
            return merge(head, next);
        }
        // 找中点
        ListNode cur = head, pre = head, curPre = null;
        while (cur != null && cur.next != null) {
            pre  = pre.next;
            cur = cur.next.next;
            curPre = curPre == null ? head : curPre.next;
        }
        // 断开
        curPre.next = null;
        // 排序前半段
        ListNode node1 = process(head);
        ListNode node2 = process(pre);
        return merge(node1, node2);
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
