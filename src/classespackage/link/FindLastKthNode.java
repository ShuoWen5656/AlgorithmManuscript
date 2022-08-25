package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.DoubleNode;
import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2021/12/18 10:03 下午
 * @Discreption <>在单链表和双链表中删除倒数第k个节点
 * 删除链表的中间节点和a/b处的节点
 *     查找倒数第k个节点：
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
            // 思路1 米卡，，
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
            // 思路2,全部遍历完，再往回加，其实跟知道长度计算出来一样。
            LinkNode knode = head;
            while (knode.getNext() != null){
                k--;
                knode = knode.getNext();
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
        if (head == null || head.getNext() == null){
            return null;
        }
        LinkNode pre = head;
        boolean start = false;
        LinkNode mid = head;
        LinkNode headTem = head.getNext();
        int count = 0;
        while (headTem != null){
            count ++;
            if(count%2 == 0){
                start = true;
                mid = mid.getNext();
            }
            if (start){
                pre = pre.getNext();
            }
            headTem = headTem.getNext();
        }
        if (pre == mid){
            //头结点
            LinkNode next = mid.getNext();
            mid.setNext(null);
            head = next;
        }else {
            pre.setNext(mid.getNext());
            mid.setNext(null);
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
        if (head == null || head.getNext() == null){
            return null;
        }
        int count = 0;
        LinkNode len = head;
        while (len != null){
            len = len.getNext();
            count++;
        }
        double index = (double)(a*count)/(double)b;
        int temIndex =  (int) Math.ceil(index);
        LinkNode pre = null;
        LinkNode tem = head;
        count = 1;
        while (count != temIndex){
            tem = tem.getNext();
            count ++;
            if (pre  == null){
                pre = head;
                continue;
            }
            pre = pre.getNext();
        }
        if (pre == null && tem == head){
            //头结点
            LinkNode next = tem.getNext();
            tem.setNext(null);
            head = next;
        }else {
            pre.setNext(tem.getNext());
            tem.setNext(null);
        }
        return tem;
    }


    /**
     * 找到倒数k个节点并删除返回
     * @param head
     * @param k
     * @return
     */
    public static LinkNode findLastKNode(LinkNode head, int k){
        LinkNode p = head;
        LinkNode q = head;
        int index = 1;
        while (p != null){
            p = p.getNext();
            // 因为要找到前一个删掉
            if (index++ > k+1){
                q = q.getNext();
            }
        }
        if (index < k){
            // 说明长度小于k
            return null;
        }
        LinkNode next = q.getNext();
        q.setNext(next.getNext());
        next.setNext(null);
        return next;
    }


    /**
     * 双向链表删除倒数k个节点
     * 方案二
     * @param head
     * @param k
     * @return
     */
    public static DoubleNode findLastKDoubleNode(DoubleNode head, int k){
        DoubleNode p = head;
        DoubleNode remove = null;
        while (p != null){
            p = p.getNext();
            k--;
        }
        if (k == 0){
            // 头结点
            DoubleNode next = head.getNext();
            head.setNext(null);
            next.setLast(null);
            remove = head;
            head = next;
        }else if (k > 0){
            // 不存在
            return null;
        }else {
            p = head;
            // 找前一个
            while (++k != 0){
                p = p.getNext();
            }
            DoubleNode next = p.getNext();
            next.setLast(null);
            p.setNext(next.getNext());
            remove = next;
        }
        return remove;
    }


    public static void main(String[] args) {
        //LinkNode linkNodeListByArr = CommonUtils.getLinkNodeListByArr(new int[]{5, 2, 4, 6, 7});
        //LinkNode lastKNode = findLastKNode(linkNodeListByArr, 4);
        //System.out.println(lastKNode);

        //DoubleNode doubleNodeListByArr = CommonUtils.getDoubleNodeListByArr(new int[]{5, 2, 4, 6, 7});
        //DoubleNode lastKDoubleNode = findLastKDoubleNode(doubleNodeListByArr, 5);
        //System.out.println(lastKDoubleNode.getValue());

        LinkNode linkNodeListByArr = CommonUtils.getLinkNodeListByArr(new int[]{5, 2, 4, 6, 7});

        FindLastKthNode findLastKthNode = new FindLastKthNode();
        LinkNode midNode = findLastKthNode.getNodeByAB(linkNodeListByArr, 3, 5);
        System.out.println(midNode);





    }


//    public void removeTheLast(){
//
//    }
}
