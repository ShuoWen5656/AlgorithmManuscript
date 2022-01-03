package classespackage;

import dataConstruct.LinkNode;
import sun.jvm.hotspot.HelloWorld;

/**
 * @author swzhao
 * @date 2021/12/18 10:03 下午
 * @Discreption <>查找倒数第k个节点：
 * 思路1：
 * 1、遍历链表，当遍历到第k个时，头节点设置一个临时指针q，接下来，k走一个，q走一个，直到k到底，q就是倒数第k个节点
 * 思路2：
 * 1、遍历链表，遍历一个k值-1，直到底部，k是否大于0，如果大于则没有倒数值，如果等于，则头节点下一个就是，小于0。如-1，则再从头节点走1个位置。
 * 2、当前节点的下一个节点就是倒数k个节点
 */
public class FindLastKthNode {


    /**
     * 找出倒数第n个节点
     * @param head
     * @param k
     * @return
     */
    public LinkNode findTheLast(LinkNode head, int k){
        try{
            // 思路1
//            LinkNode knode = head;
//            LinkNode qnode = head;
//            // 遍历
//            while (knode.getNext() != null){
//                if(k >= 0){
//                    // 没有到第k个
//                    k--;
//                    knode = knode.getNext();
//                }else{
//                    knode = knode.getNext();
//                    qnode = qnode.getNext();
//                }
//            }
//            return qnode;
            // 思路2
            LinkNode knode = head;
            while (knode.getNext() != null){
                k--;
            }
            // 遍历完毕
            if(k > 0){
                return null;
            }else if(k == 0){
                return head.getNext();
            }else{
                // 小于零
                while (k < 0){
                    knode = knode.getNext();
                    k++;
                }
                return knode.getNext();
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取中间节点:从第一个链表遍历，每增加两个，中间节点走一个
     * @return
     */
    private LinkNode getMidNode(LinkNode head){
        LinkNode mid = head;
        LinkNode headTem = head;
        int count = 0;
        while (headTem.getNext() != null){
            count ++;
            if(count%2 == 0){
                mid = mid.getNext();
            }
            headTem = headTem.getNext();
        }
        return mid;
    }

    /**
     * 获取第a/b个节点：double类型a/b向上取整，获取到第几个
     * @param head
     * @param a
     * @param b
     * @return
     */
    private LinkNode getNodeByAB(LinkNode head, int a, int b){
        double index = (double)a/(double)b;
        int temIndex =  (int) Math.ceil(index);
        LinkNode tem = head;
        int count = 0;
        while (count != temIndex){
            tem = tem.getNext();
            count ++;
        }
        return tem;
    }

//    public void removeTheLast(){
//
//    }
}
