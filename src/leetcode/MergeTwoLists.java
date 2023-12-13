package leetcode;

/**
 * @author swzhao
 * @date 2023/11/7 9:34 下午
 * @Discreption <> 合并两个有序链表
 */
public class MergeTwoLists {

    public static ListNode solution(ListNode list1, ListNode list2) {

        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        // 识别大小链表
        ListNode max = list1.val > list2.val ? list1 : list2;
        ListNode min = list1.val > list2.val ? list2 : list1;
        // 大的往小的插入
        ListNode pre = min;
        ListNode cur = min.next;
        ListNode other = max;
        while (cur != null && other != null) {
            if (cur.val < other.val) {
                pre = cur;
                cur = cur.next;
            }else {
                // 插入到cur前面
                ListNode nextMax = other.next;
                pre.next = other;
                other.next = cur;
                pre = other;
                other = nextMax;
            }
        }
        // 剩余的需要拼接
        if (cur == null) {
            // 没完
            pre.next = other;
        }
        return min;
    }



    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
