package classespackage.link;

import classespackage.CommonUtils;
import classespackage.Constants;
import dataConstruct.LinkNode;

import javax.tools.Diagnostic;

/**
 * @author swzhao
 * @data 2022/9/3 9:05
 * @Discreption <> 两个单链表生成相加链表
 */
public class GenerateAddLink {



    public static LinkNode generate(LinkNode head1, LinkNode head2){
        if (head1 == null || head2 == null){
            return null;
        }
        // 翻转
        LinkNode cur1 = ReverseListNode.reverse(head1);
        LinkNode cur2 = ReverseListNode.reverse(head2);
        LinkNode head = null;
        LinkNode cur = null;
        int ca = 0;
        while (cur1 != null && cur2 != null){
            Integer value1 = cur1.getValue();
            Integer value2 = cur2.getValue();
            // 记得加进位
            int add = value1 + value2 + ca;
            // 取进位
            ca = add/10;
            // 取当前节点数值
            int curRes = add % 10;
            LinkNode curLink = new LinkNode(curRes);
            // 放入新链表中或者成为新头部
            if (head == null){
                head = curLink;
                cur = head;
            }else {
                cur.setNext(curLink);
                cur = curLink;
            }
            cur1 = cur1.getNext();
            cur2 = cur2.getNext();
        }
        LinkNode notNull = cur1 == null ? cur2 : cur1;
        while (notNull != null){
            int add = notNull.getValue() + ca;
            ca = add/10;
            int curRes = add % 10;
            LinkNode curLink = new LinkNode(curRes);
            cur.setNext(curLink);
            cur = curLink;
            notNull = notNull.getNext();
        }
        if (ca == 1){
            // 有进位，加一个
            cur.setNext(new LinkNode(Constants.NUM_1));
        }
        return ReverseListNode.reverse(head);


    }


    public static void main(String[] args) {
        LinkNode head1 = CommonUtils.getLinkNodeListByArr(new int[]{8, 3, 7});
        LinkNode head2 = CommonUtils.getLinkNodeListByArr(new int[]{6, 3});
        LinkNode generate = generate(head1, head2);


        CommonUtils.printLinkNode(generate);


    }





}
