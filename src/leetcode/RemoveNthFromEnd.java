package leetcode;

/**
 * @author swzhao
 * @data 2023/11/8 21:38
 * @Discreption <> 删除链表的倒数第 N 个结点
 */
public class RemoveNthFromEnd {


    public static void main(String[] args) {

    }

    public static ListNode solution(ListNode head, int n) {
        ListNode pre = null;
        ListNode cur = head;

        int count = 0;
        while (cur != null) {
            if (count > n) {
                pre = pre == null ? head : pre.next;
            }
            count++;
            cur = cur.next;
        }
        if (pre == null) {
            // 头结点
            head = head.next;
            return head;
        }
        // 删除pre后面的元素
        ListNode next = pre.next;
        if (next == null) {
            return head;
        }
        ListNode nnext = next.next;
        pre.next = nnext;
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
