package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @data 2022/9/8 22:08
 * @Discreption <> 在单链表中删除指定值的节点
 */
public class RemoveLinkByValue {






    public static LinkNode remove(LinkNode head, int num){
        if (head == null){
            return head;
        }
        while (head != null && head.getValue() == num){
            head = head.getNext();
        }
        if (head == null){
            return null;
        }
        LinkNode pre = head;
        LinkNode cur = head.getNext();
        while (cur != null){
            if (cur.getValue() == num){
                LinkNode next = cur.getNext();
                pre.setNext(next);
                cur = next;
            }else {
                cur = cur.getNext();
                pre = pre.getNext();
            }
        }
        return head;
    }


    public static void main(String[] args) {
        LinkNode head = CommonUtils.getLinkNodeListByArr(new int[]{1, 2, 2, 3, 2, 2, 2});
        LinkNode remove = remove(head, 3);
        CommonUtils.printLinkNode(remove);


    }


}
