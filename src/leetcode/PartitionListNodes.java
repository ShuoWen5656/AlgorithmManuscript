package leetcode;

/**
 * @author swzhao
 * @date 2023/11/9 9:55 下午
 * @Discreption <>分隔链表
 */
public class PartitionListNodes {


    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        ListNode cur = l1;
        l1.next = new ListNode(4);
        l1 = l1.next;
        l1.next = new ListNode(3);
        l1 = l1.next;
        l1.next = new ListNode(2);
        l1 = l1.next;
        l1.next = new ListNode(5);
        l1 = l1.next;
        l1.next = new ListNode(2);
//        for (int i = 0; i < 7; i++) {
//            if (l1 == null) {
//                l1 = new ListNode(9);
//                cur = l1;
//            }else {
//                cur.next = new ListNode(9);
//                cur = cur.next;
//            }
//        }
        System.out.println(solution(cur, 3));
    }
    

    public static ListNode solution(ListNode head, int x) {
        ListNode minHead = null;
        ListNode min = null;

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                if (pre == null) {
                   // 头结点
                    if (min == null) {
                       min = head;
                       minHead = head;
                    }else {
                       min.next = head;
                       min = min.next;
                    }
                    head = head.next;
                    cur = cur.next;
                    min.next = null;
                }else {
                    ListNode tem = cur.next;
                    if (min == null) {
                        min = cur;
                        minHead = cur;
                    }else {
                        min.next = cur;
                        min = min.next;
                    }
                    pre.next = tem;
                    cur = tem;
                    min.next = null;
                }
            }else {
                pre = pre == null? head : pre.next;
                cur = cur.next;
            }
        }
        // 合并
        if (minHead == null) {
            return head;
        }else {
//            cur = minHead;
//            while (cur.next != null) {
//                cur = cur.next;
//            }
            min.next = head;
        }
        return minHead;
    }



    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
