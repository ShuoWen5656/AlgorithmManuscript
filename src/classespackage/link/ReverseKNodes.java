package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.LinkNode;

import java.util.Stack;

/**
 * @Author swzhao
 * @Date 2022/3/2 21:10
 * @Discription<> 将单链表的每k个节点之间逆序
 *     每k个链表翻转：方法1利用栈结构，方法2 使用翻转链表函数翻转后再重新链接
 */
public class ReverseKNodes {


    /**
     * 方法1：利用栈结构实现
     * @param head
     * @return
     */
    public static LinkNode reverseKNodes(LinkNode head, int num){
        try{
            if(head == null || num < 2){
                // 长度为1直接返回
                return head;
            }
            Stack<LinkNode> tempStack = new Stack<>();
            LinkNode cur = head;
            // 每一组前一个
            LinkNode per = head;
            int count = 0;
            while (cur != null){
                LinkNode tempNext = cur.getNext();
                tempStack.push(cur);
                count ++;
                if (count%num == 0){
                    // 到达一组，翻转
                    if(count == num){
                        // 第一次翻转需要特殊处理,头结点变了
                        head = cur;
                    }
                    // 先把自己弹出来，放入per后面
                    per.setNext(tempStack.pop());
                    while (!tempStack.isEmpty()){
                        LinkNode next = tempStack.pop();
                        cur.setNext(next);
                        cur = next;
                    }
                    // 当前cur在翻转过后的尾巴上，
                    cur.setNext(tempNext);
                    // per是每一组的前一个用来链接小组翻转过后的头
                    per = cur;
                    cur = cur.getNext();
                }else{
                    cur = cur.getNext();
                }
            }
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }


    public static LinkNode myReverse(LinkNode head, int num){
        if (head == null || head.getNext() == null){
            return head;
        }
        int len = 0;
        LinkNode cur = head;
        while (cur != null){
            len++;
            cur = cur.getNext();
        }
        int count = len / num;
        for (int i = 0; i < count; i++){
            head = ReverseListNode.reverseNodesFromTo(head, i * 3 + 1, (i + 1) * 3);
        }
        return head;
    }


    public static void main(String[] args) {
        LinkNode head = CommonUtils.getLinkNodeListByArr(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        LinkNode linkNode = myReverse(head, 3);
        CommonUtils.printLinkNode(linkNode);
    }



}
