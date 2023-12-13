package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.LinkNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author swzhao
 * @Date 2022/3/2 21:36
 * @Discription<> 删除链表中重复结点：方法一，借助hash，方法2：选择排序循环每一个结点
 */
public class RemoveRep {


    /**
     * 借助hash
     * @return
     */
    public static LinkNode remove(LinkNode head){
        try {
            if(head == null || head.getNext() == null){
                return head;
            }
            Set<Integer> set = new HashSet<>();
            LinkNode pre = head;
            // 先将头结点放进去
            set.add(head.getValue());
            // 直接从第二个遍历，头结点不可能删除
            LinkNode cur = head.getNext();
            while (cur != null){
                if(set.contains(cur.getValue())){
                    // 有值需要删除
                    pre.setNext(cur.getNext());
                    cur = pre.getNext();
                }else{
                    set.add(cur.getValue());
                    pre = cur;
                    cur = cur.getNext();
                }
            }
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }



    public static LinkNode remove2(LinkNode head){
        if (head == null || head.getNext() == null){
            return head;
        }
        LinkNode cur = head;
        while (cur != null){
            // 以当前节点为基准开始
            LinkNode next = cur.getNext();
            LinkNode pre = cur;
            while (next != null){
                if (next.getValue().equals(cur.getValue())){
                    pre.setNext(next.getNext());
                    next = pre.getNext();
                }else {
                    next = next.getNext();
                    pre = pre.getNext();
                }
            }
            cur = cur.getNext();
        }
        return head;
    }


    public static void main(String[] args) {
        LinkNode head = CommonUtils.getLinkNodeListByArr(new int[]{1, 1,2, 2, 3, 4, 4, 5, 5});
        LinkNode linkNode = remove(head);
        CommonUtils.printLinkNode(linkNode);
    }




}
