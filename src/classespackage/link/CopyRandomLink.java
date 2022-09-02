package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2022/2/22 10:01 下午
 * @Discreption <>复制含有随机指针节点的链表
 * 1、一种O（n）空间解法，使用map进行链表一一对应，再将random和next写入
 * 2、第二种：复制一个新的节点插入到当前节点后面，不包括随机指针，二次遍历时，将旧节点的随机节点的下一个节点赋值给自己的新节点，然后分离开就行
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
                insertIntoCur(cur, cur1);
                cur = cur1.getNext().getNext();
            }
            // 这个时候为1-1'-2-2'-3-3'，需要将1'的随机节点指向1的随机节点的新节点
            cur = head;
            while (cur != null){
                LinkNode next = cur.getNext();
                next.setNext(cur.getRadomNode().getNext());
                cur = cur.getNext().getNext();
            }
            // 分出来新节点
            LinkNode newhead = head.getNext();
            LinkNode newTail = head.getNext();
            LinkNode oldhead = head;
            LinkNode oldTail = head;
            cur = head;
            // 拆分代码：1、cur每次走两个，每次将当前的next指向next.next,将next的next指向next的next.next
            while (cur != null){
                LinkNode next1 = cur.getNext();
                // 先存一下下一波的起点
                LinkNode next2 = cur.getNext().getNext();
                cur.setNext(next2);
                next1.setNext(next2.getNext() == null ? null : next2.getNext());
                cur = next2;
            }
            // 分离代码有问题，cur复制给自己了
//            while (cur != null){
//                if(count%2 == 0){
//                    // 偶数为新值
//                    if(newhead == null){
//                        newhead = newTail = cur;
//                    }else{
//                        newTail.setNext(cur);
//                        newTail = newTail.getNext();
//                    }
//                }else{
//                    // 奇数为老值
//                    if(oldhead == null){
//                        oldhead = oldTail = cur;
//                    }else{
//                        oldTail.setNext(cur);
//                        oldTail = oldTail.getNext();
//                    }
//                }
//                cur = cur.getNext();
//                count++;
//            }
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


    /**
     * 简化版本
     * @param head
     * @return
     */
    public static LinkNode copy(LinkNode head){
        if (head == null){
            return head;
        }
        LinkNode cur = head;
        while (cur != null){
            LinkNode copy = new LinkNode(cur.getValue());
            // 先指向母节点的下一个
            copy.setRadomNode(cur.getRadomNode());
            LinkNode next = cur.getNext();
            cur.setNext(copy);
            copy.setNext(next);
            cur = copy.getNext();
        }
        cur = head;
        LinkNode head2 = cur.getNext();
        LinkNode cur2 = head2;
        while (cur != null){
            LinkNode copy = cur.getNext();
            // 解决radom
            LinkNode radomNode = copy.getRadomNode();
            if (radomNode != null){
                copy.setRadomNode(radomNode.getNext());
            }
            // 剔出来
            cur.setNext(copy.getNext());
            copy.setNext(null);
            // 放入新列表中
            if (cur2 != copy){
                // 头结点
                cur2.setNext(copy);
                cur2 = copy;
            }
            cur = cur.getNext();
        }
        return head2;
    }


    public static void main(String[] args) {
        LinkNode head = CommonUtils.getLinkNodeListByArr(new int[]{2, 3, 1, 5, 4});
        //3-5
        head.getNext().setRadomNode(head.getNext().getNext().getNext());
        // 2-3
        head.setRadomNode(head.getNext());
        LinkNode head2 = copy(head);
        // 检测一下
        LinkNode cur1 = head;
        LinkNode cur2 = head2;
        while (cur1 != null){
            if (cur1 == cur2){
                System.out.println(false);
            }
            cur1 = cur1.getNext();
            cur2 = cur2.getNext();
        }
        CommonUtils.printLinkNode(head2);
    }


}
