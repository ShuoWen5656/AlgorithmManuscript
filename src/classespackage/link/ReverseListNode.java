package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.DoubleNode;
import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2021/12/27 10:20 下午
 * @Discreption <>反转链表和反转部分链表
 */
public class ReverseListNode {


    /**
     * 翻转部分单链表
     * @param head
     * @param from
     * @param to
     * @return
     */
    public static LinkNode reverseNodesFromTo(LinkNode head, int from, int to){
        int length = 0;
        LinkNode cur = head;
        LinkNode pre = head;
        LinkNode fromNode = null;
        LinkNode toNode = null;
        while (cur != null){
            length ++;
            if (length+1 == from){
                // from的前一个
                pre = cur;
            }
            if (length == from){
                fromNode = cur;
            }
            if (length == to){
                toNode = cur;
            }
            cur = cur.getNext();
        }
        if (fromNode == null || toNode == null){
            return head;
        }
        LinkNode toNext = toNode.getNext();
        if (fromNode == head){
            if (toNext == null){
                return reverse(fromNode);
            }else {
                toNode.setNext(null);
                reverse(fromNode);
                fromNode.setNext(toNext);
                head = toNode;
            }
        }else {
            toNode.setNext(null);
            reverse(fromNode);
            pre.setNext(toNode);
            fromNode.setNext(toNext);
        }
        return head;
    }


    /**
     * 翻转单链表
     * @param head
     * @return
     */
    public static LinkNode reverse(LinkNode head){
        if (head == null || head.getNext() == null){
            return head;
        }
        LinkNode cur = head;
        LinkNode next = head.getNext();
        while (next != null){
            LinkNode tem = next.getNext();
            next.setNext(cur);
            cur = next;
            next = tem;
        }
        head.setNext(null);
        head = cur;
        return head;
    }


    /**
     * 翻转双链表
     * @param head
     * @return
     */
    public static DoubleNode reverseD(DoubleNode head){
        if (head == null || head.getNext() == null){
            return head;
        }
        DoubleNode cur = head;
        DoubleNode next = head.getNext();
        // 先做这一步
        head.setLast(head.getNext());
        while (next != null){
            DoubleNode tem = next.getNext();
            next.setNext(cur);
            next.setLast(tem);
            cur = next;
            next = tem;
        }
        head.setNext(null);
        head = cur;
        return head;
    }


    public static void main(String[] args) {
        //ReverseListNode reverseListNode = new ReverseListNode();
        LinkNode linkNodeListByArr = CommonUtils.getLinkNodeListByArr(new int[]{5, 2, 4, 6, 7});
        LinkNode reverse = reverse(linkNodeListByArr);
        System.out.println(reverse);

        //DoubleNode doubleNodeListByArr = CommonUtils.getDoubleNodeListByArr(new int[]{5, 2, 4, 6, 7});
        //DoubleNode lastKDoubleNode = reverseD(doubleNodeListByArr);
        //System.out.println(lastKDoubleNode.getValue());


    }

}
