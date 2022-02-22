package classespackage;

import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2022/2/22 10:01 下午
 * @Discreption <>复制随机链表节点到新节点
 * 1、一种O（n）空间解法，使用map进行链表一一对应，再将random和next写入
 */
public class CopyRandomLink {

    /**
     * 空间O（1）时间O（n）解法：将复制的节点插入该节点的后面，使用到链表修改简单特性
     * @param head
     * @return
     */
    public static LinkNode copyRandomLink(LinkNode head){
        try{
            LinkNode cur = head;
            while (cur != null){
                // 造一个新节点放到当前节点的后面
                LinkNode cur1 = new LinkNode(cur.getValue());
                cur1.setRadomNode(cur.getRadomNode());
                insertIntoCur(cur, cur1);
                cur = cur1.getNext().getNext();
            }
            // 分出来新节点
            LinkNode newhead = null;
            LinkNode newTail = null;
            LinkNode oldhead = null;
            LinkNode oldTail = null;
            cur = head;
            int count = 1;
            while (cur != null){
                if(count%2 == 0){
                    // 偶数为新值
                    if(newhead == null){
                        newhead = newTail = cur;
                    }else{
                        newTail.setNext(cur);
                        newTail = newTail.getNext();
                    }
                }else{
                    // 奇数为老值
                    if(oldhead == null){
                        oldhead = oldTail = cur;
                    }else{
                        oldTail.setNext(cur);
                        oldTail = oldTail.getNext();
                    }
                }
                cur = cur.getNext();
                count++;
            }
            return newhead;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }


    /**
     * 将insertnode插入head后面
     * @param head
     * @param insertNode
     */
    public static void insertIntoCur(LinkNode head, LinkNode insertNode){
        if(insertNode == null){
            return;
        }
        if(head.getNext() == null){
            head.setNext(insertNode);
        }else{
            LinkNode next = head.getNext();
            head.setNext(insertNode);
            insertNode.setNext(next);
        }
    }


}
