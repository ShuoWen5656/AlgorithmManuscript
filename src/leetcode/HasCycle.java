package leetcode;

/**
 * @author swzhao
 * @date 2023/11/7 9:27 下午
 * @Discreption <> 判断单链表是否有环
 */
public class HasCycle {


    public static boolean solution(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 下两个
        ListNode cur = head.next.next;
        ListNode pre = head;
        while (cur != null) {
            if (cur == pre) {
                // 说明相遇了 有环
                return true;
            }
            pre = pre.next;
            if (cur.next == null) {
                return false;
            }else {
                cur = cur.next.next;
            }
        }
        return false;
    }





    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }

    }

}
