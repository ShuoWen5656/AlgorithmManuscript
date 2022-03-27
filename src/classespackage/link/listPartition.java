package classespackage.link;

import dataConstruct.LinkNode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author swzhao
 * @date 2022/1/22 1:03 下午
 * @Discreption <>将单链表按照某个值分为小于的（左），等于的（中），大于的
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
                    setLinkNodeToLinkList(ln, ln2, cur);
                }else if(value == num){
                    setLinkNodeToLinkList(mn, mn2, cur);
                }else if(value < num){
                    setLinkNodeToLinkList(sn, sn2, cur);
                }
                cur = cur.getNext();
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
     * @param head
     * @param tail
     * @param cur
     */
    public static void setLinkNodeToLinkList(LinkNode head, LinkNode tail, LinkNode cur){
        if(head == null){
            head = tail = cur;
        }else{
            tail.setNext(cur);
            tail = tail.getNext();
        }
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





}
