package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @data 2022/9/9 20:14
 * @Discreption <>单链表的选择排序
 */
public class LinkSeclectSort {

    /**
     * 选择排序（单链表版本从小到大）
     * @param head
     * @return
     */
    public static LinkNode selectSort(LinkNode head){
        if (head == null || head.getNext() == null){
            return head;
        }
        LinkNode cur = head;
        LinkNode pre = null;
        LinkNode index = head;
        LinkNode indexPre = null;
        int min = Integer.MAX_VALUE;
        // 先找到头结点
        while (cur != null){
            if (min > cur.getValue()){
                index = cur;
                indexPre = pre;
            }
            if (pre == null){
                pre = cur;
            }else {
                pre = pre.getNext();
            }
            cur = cur.getNext();
        }
        indexPre.setNext(index.getNext());
        index.setNext(head);
        head = index;
        while (index != null){
            // 每一轮初始化变量
            min = Integer.MAX_VALUE;
            cur = index.getNext();
            pre = index;
            // 最小值的前后
            LinkNode minNode = null;
            LinkNode minNodePre = null;
            while (cur != null){
                if (min > cur.getValue()){
                    minNode = cur;
                    minNodePre = pre;
                }
                cur = cur.getNext();
                pre = pre.getNext();
            }
            if (minNode!= null){
                // 找到最小值
                minNodePre.setNext(minNode.getNext());
                LinkNode next = index.getNext();
                index.setNext(minNode);
                minNode.setNext(next);
            }
            index = index.getNext();
        }
        return head;
    }


    public static void main(String[] args) {
        LinkNode head = CommonUtils.getLinkNodeListByArr(new int[]{5, 4, 3, 2, 1});
        LinkNode linkNode = selectSort(head);
        CommonUtils.printLinkNode(linkNode);

    }


}
