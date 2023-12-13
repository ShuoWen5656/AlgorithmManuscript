package leetcode;

/**
 * @author swzhao
 * @date 2023/11/7 9:46 下午
 * @Discreption <>
 */
public class AddTwoNumbers {


    public static void main(String[] args) {
        ListNode l1 = null;
        ListNode cur = null;
        for (int i = 0; i < 7; i++) {
            if (l1 == null) {
                l1 = new ListNode(9);
                cur = l1;
            }else {
                cur.next = new ListNode(9);
                cur = cur.next;
            }
        }
        ListNode l2 = null;
        cur = null;
        for (int i = 0; i < 4; i++) {
            if (l2 == null) {
                l2 = new ListNode(9);
                cur = l2;
            }else {
                cur.next = new ListNode(9);
                cur = cur.next;
            }
        }
        System.out.println(solution(l1, l2));
    }





    public static ListNode solution(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode res = null;
        ListNode cur = null;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        // 进位
        int carry = 0;
        while (cur1 != null && cur2 != null) {
            int i1 = cur1.val;
            int i2 = cur2.val;
            int curRes = (i1 + i2 + carry)%10;
            carry = (i1 + i2 + carry) / 10;
            ListNode curNode = new ListNode(curRes);
            if (cur == null) {
                cur = curNode;
                res = curNode;
            }else {
                cur.next = curNode;
                cur = curNode;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        // 剩余的
        if (cur1 == null) {
            while (cur2 != null) {
                ListNode next2 = cur2.next;
                int i2 = cur2.val;
                int curRes = (i2 + carry)%10;
                carry = (i2 + carry) / 10;
                ListNode curNode = new ListNode(curRes);
                cur.next = curNode;
                cur = curNode;
                cur2 = next2;
            }
        }
        if (cur2 == null) {
            while (cur1 != null) {
                ListNode next2 = cur1.next;
                int i2 = cur1.val;
                int curRes = (i2 + carry)%10;
                carry = (i2 + carry) / 10;
                ListNode curNode = new ListNode(curRes);
                cur.next = curNode;
                cur = curNode;
                cur1 = next2;
            }
        }
        if (carry == 1) {
            ListNode curNode = new ListNode(carry);
            cur.next = curNode;
            cur = cur.next;
        }
        return res;
    }







    static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
