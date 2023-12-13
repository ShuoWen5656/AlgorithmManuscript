package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.LinkNode;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2022/1/16 8:07 下午
 * @Discreption <> 判断一个链表是否为回文结构
 */
public class IsPalindRome {
    public static boolean isPalindRome(LinkNode head){
        try{
            LinkNode cur = head;
            LinkNode cur2 = head;
            Stack<LinkNode> linkNodes = new Stack<>();
            while (cur != null){
                linkNodes.push(cur);
                cur = cur.getNext();
            }
            // 比对
            while (cur2 != null){
                LinkNode stackNode = linkNodes.pop();
                if(cur2.getValue() != stackNode.getValue()){
                    return false;
                }
                cur2 = cur2.getNext();
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 方法一
     * 用栈结构：可以压入全部，也可以压入后面一半
     * @param head
     * @return
     */
    public static boolean isPalind(LinkNode head){
        Stack<LinkNode> stack = new Stack<>();
        LinkNode cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.getNext();
        }
        cur = head;
        while (cur != null){
            if (stack.pop().getValue() != cur.getValue()){
                return false;
            }
            cur = cur.getNext();
        }
        return true;
    }


    /**
     * 方法二：
     * 找到中间节点后翻转前半部分，然后从中间开始比对
     * @param head
     * @return
     */
    public static boolean isPalind2(LinkNode head){
        LinkNode left = head;
        LinkNode cur = head;
        LinkNode right = head;
        int len = 0;
        while (cur != null){
            len++;
            cur = cur.getNext();
        }
        int mid = (len+1)/2;
        int tem = 0;
        cur = head;
        while (cur != null){
            if (++tem == mid){
                break;
            }
            cur = cur.getNext();
        }
        LinkNode from = cur.getNext();
        right = ReverseListNode.reverse(from);
        while (left != cur){
            if (!left.getValue().equals(right.getValue())){
                return false;
            }
            left = left.getNext();
            right = right.getNext();
        }
        if (!left.getValue().equals(right.getValue())){
            return false;
        }
        return true;
    }




    public static void main(String[] args) {
        LinkNode head = CommonUtils.getLinkNodeListByArr(new int[]{1, 2, 2, 1});
        System.out.println(isPalind2(head));
        System.out.println(isPalind(head));

    }


}
