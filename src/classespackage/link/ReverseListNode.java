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
        LinkNode headFrom = ReverseListNode.reverse(fromNode);
        // 先取出反转前第一个存起来
        LinkNode lastNode = fromNode.getNext();
        // 将反转后的第一个位置设置为fromNode的next
        fromNode.setNext(headFrom.getNext());
        // 反转前第一个现在是尾巴，尾巴设置next为to的后一个即可
        lastNode.setNext(toNode);
        return head;
    }


    public static LinkNode reverseNodesFomeTo(LinkNode head, int from, int to){
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
            return null;
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
        //LinkNode reverse = reverse(linkNodeListByArr);
        //LinkNode lastKNode = findLastKNode(linkNodeListByArr, 4);
        //System.out.println(reverse);

        //DoubleNode doubleNodeListByArr = CommonUtils.getDoubleNodeListByArr(new int[]{5, 2, 4, 6, 7});
        //DoubleNode lastKDoubleNode = reverseD(doubleNodeListByArr);
        //System.out.println(lastKDoubleNode.getValue());


        LinkNode linkNode = reverseNodesFomeTo(linkNodeListByArr, 2, 3);
        CommonUtils.printLinkNode(linkNode);


    }

}
