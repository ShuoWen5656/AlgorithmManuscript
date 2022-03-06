package classespackage;

import com.sun.jmx.snmp.SnmpPduBulk;
import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2022/3/6 10:18 上午
 * @Discreption <> 本书最后三个单链表算法题，因为难度较为简单，放在一起了
 */
public class TheLastThreeLinkedNodeAlgorithm {

    /**
     * 向有序的环形链表中插入新节点
     * @param head
     * @param num
     * @return
     */
    public static LinkNode insertNum(LinkNode head, int num){
        try{
            if(head == null){
                return new LinkNode(num);
            }
            if(head.getNext() == null){
                // 单节点
                LinkNode newNode = new LinkNode(num, head);
                head.setNext(newNode);
            }
            LinkNode cur = head.getNext();
            LinkNode pre = head;
            while (cur.getNext() != head){
                if(num > pre.getValue()
                        && num < cur.getValue()) {
                    // 插入到当前节点下
                    pre.setNext(new LinkNode(num, cur));
                    return head;
                }
            }
            // 到这里说明num是最值，插入头节点和cur之间
            cur.setNext(new LinkNode(num, head));
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }

    /**
     * 合并两个有序链表
     * @param head1
     * @param head2
     * @return
     */
    public static LinkNode merge(LinkNode head1, LinkNode head2){
        LinkNode head = null;
        try{
            LinkNode cur1 = head1;
            LinkNode cur2 = head2;
            if(cur1.getValue() < cur2.getValue()){
                head = cur1;
                cur1.setNext(cur1.getNext());
            }else{
                head = cur2;
                cur2.setNext(cur2.getNext());
            }
            LinkNode cur = head;
            while (cur1 != null && cur2 != null){
                if(cur1.getValue() < cur2.getValue()){
                    cur.setNext(cur1);
                    cur1 = cur1.getNext();
                }else{
                    cur.setNext(cur2);
                    cur2 = cur2.getNext();
                }
                cur = cur.getNext();
            }
            // 将剩余的加入到总链表
            if(cur1 == null){
                cur.setNext(cur2);
            }else{
                cur.setNext(cur1);
            }
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }

    /**
     * 按照左右半区的方式重新组合单链表
     * @param head
     */
    public static LinkNode reLocated(LinkNode head){
        try{
            // 计算长度
            int count = 0;
            LinkNode cur = head;
            // 慢节点，cur走2个 curslow走一个
            LinkNode curSlow = head;
            while (cur != null){
                count ++;
                cur = cur.getNext();
                if(count%2 == 0) {
                    curSlow = curSlow.getNext();
                }
            }
            // 三个不用管
            if(count <= 3){
                return head;
            }
            // 到这里head为链表1的head，curslow为链表2的head
            cur = head;
            while (cur != null){
                // 往后面插入一个
                LinkNode tem = cur.getNext();
                LinkNode tem2 = curSlow.getNext();
                cur.setNext(curSlow);
                curSlow.setNext(tem);
                cur = tem;
                curSlow = tem2;
            }
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }

}
