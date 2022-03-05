package classespackage;

import dataConstruct.LinkNode;

/**
 * @Author swzhao
 * @Date 2022/3/5 22:34
 * @Discription<> 怪异删除结点方式： 问题1、当给定的节点是尾巴时必须要找到尾巴的前一个节点才行，问题2、如果链表结点比较复杂，甚至对象中的值无法更改时，该方法失效
 */
public class StrangeRemoveNode {

    /**
     * 给头结点和数字,删除指定数字结点
     * 1、找到该结点，如果该结点是尾巴结点，上一个结点直接指向null，如果不是，注意了！！，该节点的值直接等于下一个结点的值，并删除下一个结点，哈哈
     * @param head
     * @return
     */
    public static LinkNode remove1(LinkNode head, int num){
        try {
            if(head == null){
                return null;
            }
            LinkNode cur = head;
            LinkNode per = head;
            while (cur.getNext() != null){
                if(cur.getValue() == num){
                    // 删除当前结点:将后面的节点值复制给自己，删掉后面的节点
                    cur.setValue(cur.getNext().getValue());
                    cur.setNext(cur.getNext().getNext());
                }else{
                    per = cur;
                    cur = cur.getNext();
                }
            }
            // 这里cur已经指向尾巴结点了，判断一下
            if(cur.getValue() == num){
                per.setNext(null);
            }
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }
}
