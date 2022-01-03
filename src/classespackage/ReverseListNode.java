package classespackage;

import dataConstruct.DoubleNode;
import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2021/12/27 10:20 下午
 * @Discreption <>反转链表和反转部分链表
 */
public class ReverseListNode {

    /**
     * 反转全链表
     * @param head
     * @return
     */
    public LinkNode reverseList(LinkNode head){
        LinkNode temp = null;
        LinkNode next = head.getNext();
        LinkNode pre = null;
        while (next != null){
            // 将下一个先放好
            pre = next.getNext();
            // 上一个设置为next
            next.setNext(temp);
            // 上一个进一位，这个时候next应改了，所以千万不能getnext
            temp = next;
            // next进一位，不能用getNext
            next = pre;
        }
        head.setNext(next);
        return head;
    }

    /**
     * 反转双向链表
     * @param head
     * @return
     */
    public DoubleNode reverseList(DoubleNode head){
        DoubleNode temp = null;
        DoubleNode next = head.getNext();
        DoubleNode pre = null;
        while (next != null){
            // 将下一个先放好
            pre = next.getNext();
            // 上一个设置为next
            next.setNext(temp);
            // 下一个设置为last
            next.setLast(pre);
            // 上一个进一位，这个时候next应改了，所以千万不能getnext
            temp = next;
            // next进一位，不能用getNext
            next = pre;
        }
        head.setNext(next);
        return head;
    }

    /**
     * 反转部分链表
     * @return
     */
    public LinkNode reversePartList(LinkNode head, int from, int to){
        int length = 0;
        LinkNode fromNode = null;
        LinkNode toNode = null;
        LinkNode tem = head;
        // 遍历：1、找出长度2、找出from前一个节点和to的后一个节点
        while (tem.getNext() != null){
            length ++;
            if(length == from){
                // from前面的节点
                fromNode = tem;
            }
            if(length == to){
                toNode = tem.getNext().getNext();
            }
        }
        if(to - from + 1 > length){
            return head;
        }
        // 反转from-to,返回部分链表的前一个node
        LinkNode headFrom = reverseList(fromNode);
        // 先取出反转前第一个存起来
        LinkNode lastNode = fromNode.getNext();
        // 将反转后的第一个位置设置为fromNode的next
        fromNode.setNext(headFrom.getNext());
        // 反转前第一个现在是尾巴，尾巴设置next为to的后一个即可
        lastNode.setNext(toNode);
        return head;
    }


}
