package leetcode;

/**
 * @author swzhao
 * @date 2023/11/9 9:43 下午
 * @Discreption <> 旋转链表
 */
public class RotateRight {





    public ListNode solution(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 成环
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        cur.next = head;

        // 取第k个值
        int count = 0;
        cur = head;
        while (count < len - (k%len) - 1) {
            cur = cur.next;
            count++;
        }
        // 断开
        head = cur.next;
        cur.next = null;
        return head;
    }

  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
