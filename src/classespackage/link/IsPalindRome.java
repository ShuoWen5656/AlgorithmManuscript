package classespackage.link;

import dataConstruct.LinkNode;

import java.util.Stack;

/**
 * @author swzhao
 * @date 2022/1/16 8:07 下午
 * @Discreption <> 是否回文链表
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
}
