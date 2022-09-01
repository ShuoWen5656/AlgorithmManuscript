package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.LinkNode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author swzhao
 * @date 2022/1/22 1:03 下午
 * @Discreption <>将单向链表按某值划分成左边小、中间相等、右边大的形式
 *     将单链表按照某个值分为小于的（左），等于的（中），大于的
 */
public class listPartition {


    /**
     * 将单链表放入数组中，并将单链表按照快速排序的其中一个步骤进行分类就行
     * @param head
     * @param num
     * @return
     */
    public static LinkNode listPartition1(LinkNode head, int num){
        try{
            LinkNode cur = head;
            int i = 0;
            List<LinkNode> nodes = new ArrayList<>();
            // 计算长度并加入列表
            while (cur != null){
                cur = cur.getNext();
                i++;
                nodes.add(cur);
            }
            // 将列表转成数组
            LinkNode[] linkNodes = nodes.toArray(new LinkNode[nodes.size()]);
            // 进行快排中的一次操作
            quickOrder(linkNodes, num);
            // 转成单链表
            for (i = 0; i < linkNodes.length; i++){
                LinkNode linkNode = linkNodes[i];
                if(i == linkNodes.length -1){
                    // 最后一个
                    linkNode.setNext(null);
                }else{
                    // 不是最后一个就有下一个
                    linkNode.setNext(linkNodes[i+1]);
                }
            }
            // 设置头结点
            head = linkNodes[0];
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }


    static class Helper {
        LinkNode head;
        LinkNode tail;
        LinkNode cur;

        public Helper(LinkNode head, LinkNode tail, LinkNode cur) {
            this.head = head;
            this.tail = tail;
            this.cur = cur;
        }

        public LinkNode getHead() {
            return head;
        }

        public void setHead(LinkNode head) {
            this.head = head;
        }

        public LinkNode getTail() {
            return tail;
        }

        public void setTail(LinkNode tail) {
            this.tail = tail;
        }

        public LinkNode getCur() {
            return cur;
        }

        public void setCur(LinkNode cur) {
            this.cur = cur;
        }
    }


    /**
     * 进阶解法，不使用额外空间,将链表直接拆成三个，大于等于小于的三个单链表，然后合并
     * @param head
     * @param num
     * @return
     */
    public static LinkNode listPartition2(LinkNode head, int num){
        try{
            // largeNode,大于的链表
            LinkNode ln = null;
            // 大于链表的尾巴
            LinkNode ln2 = null;
            LinkNode mn = null;
            LinkNode mn2 = null;
            LinkNode sn = null;
            LinkNode sn2 = null;
            LinkNode cur = head;
            // 将链表分成三段
            while (cur != null){
                Integer value = cur.getValue();
                if(value > num){
                    Helper helper = new Helper(ln, ln2, cur);
                    setLinkNodeToLinkList(helper);
                    ln = helper.getHead();
                    ln2 = helper.getTail();
                }else if(value == num){
                    Helper helper = new Helper(mn, mn2, cur);
                    setLinkNodeToLinkList(helper);
                    mn = helper.getHead();
                    mn2 = helper.getHead();
                }else if(value < num){
                    Helper helper = new Helper(sn, sn2, cur);
                    setLinkNodeToLinkList(helper);
                    sn = helper.getHead();
                    sn2 = helper.getTail();
                }
                LinkNode pre = cur;
                cur = cur.getNext();
                pre.setNext(null);
            }
            // 合并，注意一下null情况
            if (sn == null){
                // 从中间和
                if(mn == null){
                    ln2.setNext(null);
                    return ln;
                }else{
                    if(ln == null){
                        sn2.setNext(null);
                        return sn;
                    }else{
                        sn.setNext(ln);
                        ln2.setNext(null);
                        return sn;
                    }
                }
            }else{
                if(mn == null){
                    sn.setNext(ln);
                    ln2.setNext(null);
                    return sn;
                }else{
                    sn2.setNext(mn);
                    if(ln == null){
                        mn2.setNext(null);
                        return sn;
                    }else{
                        ln2.setNext(null);
                        mn.setNext(ln);
                        return sn;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }


    /**
     * 将cur节点放入指定list后面
     */
    public static void setLinkNodeToLinkList(Helper helper){
        LinkNode head = helper.getHead();
        LinkNode tail = helper.getTail();
        LinkNode cur = helper.getCur();
        if(head == null){
            head = cur;
            tail = cur;
        }else{
            tail.setNext(cur);
            tail = tail.getNext();
        }
        helper.setHead(head);
        helper.setTail(tail);
    }



    /**
     * 快排中的一次排序
     * @param linkNodes
     * @param num
     */
    public static void quickOrder(LinkNode[] linkNodes, int num) throws Exception{
        int start = 0;
        int end = linkNodes.length-1;
        int index = 0;
        // 遍历
        while (index != end){
            if(linkNodes[index].getValue() > num){
                // 如果当前值大于num，应该将其换到end那边，end--
                swap(linkNodes, index, end--);
            }else if(linkNodes[index].getValue() == num){
                index++;
            }else{
                // 如果当前值小于num，应该换到start
                swap(linkNodes, index, start++);
            }
        }

    }

    /**
     * 交换数组的两个节点
     * @param linkNodes
     * @param start
     * @param end
     */
    public static void swap(LinkNode[] linkNodes, int start, int end) throws Exception{
        LinkNode tem = linkNodes[start];
        linkNodes[start] = linkNodes[end];
        linkNodes[end] = tem;
    }


    /**
     * 1、小的和放到small后面
     * 2、大的放到尾巴
     * 3、等于的不处理
     * @param head
     * @param pivot
     */
    public static void changeLinkNode(LinkNode head, int pivot){
        LinkNode small = null;
        LinkNode index = head;
        LinkNode tail  = head;
        LinkNode pre = null;
        // 第一个比pivot大的节点，很重要
        LinkNode firstBig = null;
        // 获取尾巴节点
        while (tail.getNext() != null){
            tail = tail.getNext();
        }
        while (index != firstBig){
            if (index.getValue() < pivot){
                if (small == null){
                    if (index == head){
                        small = index;
                    }else {
                        LinkNode next = index.getNext();
                        pre.setNext(next);
                        small = index;
                        small.setNext(head);
                        head = small;
                        index = next;
                    }

                }else {
                    LinkNode next = small.getNext();
                    LinkNode next1 = index.getNext();
                    pre.setNext(next1);
                    small.setNext(index);
                    index.setNext(next);
                    // index和pre要到下一场,small 为下一个
                    small = small.getNext();
                    index = next1;
                    continue;
                }
            }else if (index.getValue() > pivot){
                if (firstBig == null){
                    firstBig = index;
                }
                if (index == head){
                    LinkNode next1 = index.getNext();
                    index.setNext(null);
                    tail.setNext(index);
                    tail = tail.getNext();
                    head = next1;
                    index = next1;
                }else {
                    LinkNode next1 = index.getNext();
                    pre.setNext(next1);
                    index.setNext(null);
                    tail.setNext(index);
                    tail = tail.getNext();
                    index = next1;
                    continue;
                }
            }
            if (pre == null){
                pre = head;
            }else {
                pre = pre.getNext();
            }
            index = index.getNext();
        }
    }


    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        LinkNode head = CommonUtils.getLinkNodeListByArr(new int[]{1, 2, 1, 4, 3, 9, 7});
        //listPartition2(head, 2);
        changeLinkNode(head, 2);
        CommonUtils.printLinkNode(head);

    }




}
