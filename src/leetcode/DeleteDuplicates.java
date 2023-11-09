package leetcode;

/**
 * @author swzhao
 * @date 2023/11/9 9:25 下午
 * @Discreption <> 删除排序链表中的重复元素 II
 */
public class DeleteDuplicates {





    public static ListNode solution(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {

            // 去cur的重
            ListNode next = cur.next;
            boolean flag = false;
            while (next != null && next.val == cur.val) {
                flag = true;
                next = next.next;
            }
            cur.next = next;
            if (flag) {
                // 删除cur节点
                if (pre == null) {
                    // 头结点
                    head = head.next;
                    cur = cur.next;
                }else {
                    pre.next = next;
                    cur = pre.next;
                }
            }else {
                pre = pre == null ? head : pre.next;
                cur = cur.next;
            }
        }
        return head;

    }
}


    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }


}
