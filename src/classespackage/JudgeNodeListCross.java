package classespackage;

import dataConstruct.LinkNode;

/**
 * @Author swzhao
 * @Date 2022/2/25 20:43
 * @Discription<> 判断单链表是否有交点，如果有，则返回第一个相交的节点指针
 */
public class JudgeNodeListCross {

    /**
     * 判断单链表是否相交，如果相交，返回第一个相交的节点指针
     * 1、一个有环，另一个无环，则不可能相交
     * 2、两个都有环
     * 3、两个都无环
     * @return
     */
    public static LinkNode getCrossNode(LinkNode head1, LinkNode head2){
        try{
            LinkNode inLoop1 = getLoopNode(head1);
            LinkNode inLoop2 = getLoopNode(head2);
            if((inLoop1 != null && inLoop2 == null)
                    || (inLoop1 == null && inLoop2 != null)){
                // 不可能相交
                return null;
            }
            if(inLoop1 == null && inLoop2 == null){
                return noLoop(head1, head2);
            }else{
                // 两个有环的
                return loopCross(head1, head2);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 判断nodelist是否存在环，如果存在返回入环点，不存在null
     * 原理：
     * 1、假设环长度x，整个链表取余x为n，则链表应该为若干x+n长度，即为（m+1）x+n
     * 2、当慢节点第一次到达环入口时，走过了mx+n，此时，快节点应该走了2（mx+n）
     * 3、快节点已经走过了慢节点的mx+n，还剩mx+n，所以这时应该在慢节点前面，距离慢节点n个单位
     * 4、快节点和慢节点要相遇的话，应该还剩x-n个单位
     * 5、快节点一次比慢节点快一个单位，所以相遇时还需要循环x-n次，此时，慢节点应该到达了(n+mx)+(x-n),距离入口处还剩n个节点
     * 6、该位置离起点的距离刚好是x的整数倍（m+1）,此时再来一个指针从起点开始和慢节点一起移动1个单位，直到相遇时，必为环入口点
     * 7、参考：等余方程 (a-b)%m = 0
     * @return
     */
    public static LinkNode getLoopNode(LinkNode head) throws Exception{
        if(head == null || head.getNext() == null){
            return null;
        }
        // 慢指针一次走一个
        LinkNode slow = head;
        // 快指针一次走两个
        LinkNode fast = head.getNext().getNext();
        if(fast == null){
            return null;
        }
        // 第一次相遇
        while (slow != fast){
            slow = slow.getNext();
            if(fast.getNext() != null){
                // fast后面还有值
                fast = fast.getNext().getNext();
            }else{
                // fast为最后一个节点了，没有环了
                return null;
            }
        }
        // 走到这，两个节点相遇了,必有环,fast重置到head
        fast = head;
        // 第二次相遇，这次fast也只走一个
        while (fast != slow){
            fast = fast.getNext();
            slow = slow.getNext();
        }
        // 第二次相遇即为入环点
        return fast;
    }

    /**
     * 两个单链表无环时，求交点，如果不相交，返回null
     * @return
     */
    public static LinkNode noLoop(LinkNode head1, LinkNode head2) throws Exception{
        // 找到各自的尾巴，并计算出长度
        LinkNode tail1 = head1;
        LinkNode tail2 = head2;
        int count1 = 0;
        int count2 = 0;
        if(head1 == null || head2 == null) {
            return null;
        }
        while (tail1.getNext() != null){
            tail1 = tail1.getNext();
            count1++;
        }
        while (tail2.getNext() != null){
            tail2 = tail2.getNext();
            count2++;
        }
        if(tail1 != tail2){
            // 如果尾巴不相同，则没有相交
            return null;
        }
        if(count1 >= count2){
            return findCrossForNoLoop(head1 , head2, count1, count2);
        }else{
            return findCrossForNoLoop(head2, head1, count1, count2);
        }
    }


    /**
     * 找出无环链表的相交点
     * @param largeList 长list
     * @param smallList 短list
     * @param largeLen  长list长度
     * @param smallLen  短list长度
     * @return
     */
    public static LinkNode findCrossForNoLoop(LinkNode largeList, LinkNode smallList, int largeLen, int smallLen){
            int sub = largeLen - smallLen;
            while (sub > 0){
                sub--;
                largeList = largeList.getNext();
            }
            while (largeList != null && smallList != null && largeList != smallList){
                largeList = largeList.getNext();
                smallList = smallList.getNext();
            }
            return largeList;
    }


    /**
     * 两个链表都有环的时候：
     * 1、入环点相等，说明入环之前就已经相交了
     * 2、入环点不相等：循环一个链判断是否经过另一个链的入环点
     *      · 经过：有相交点，返回任何一个入环店都可以
     *      · 不经过：不相交，返回null
     * @param head1
     * @param head2
     * @return
     */
    public static LinkNode loopCross(LinkNode head1, LinkNode head2) throws Exception{
        if(head1 == null || head2 == null) return null;
        LinkNode inLoop1 = getLoopNode(head1);
        LinkNode inLoop2 = getLoopNode(head2);
        LinkNode tem = head1;
        if(inLoop1 == null || inLoop2 == null) return null;
        if(inLoop1 == inLoop2){
            int count1 = 1;
            int count2 = 1;
            while (tem != inLoop1){
                tem = tem.getNext();
                count1 ++;
            }
            tem = head2;
            while (tem != inLoop2){
                tem = tem.getNext();
                count2++;
            }
            if(count1 > count2){
                return findCrossForNoLoop(head1, head2, count1, count2);
            }else{
                return findCrossForNoLoop(head2, head1, count2, count1);
            }
        }else{
            // 两种情况：head1循环查找head2的入环点
            boolean isCross = false;
            tem = inLoop1.getNext();
            while (tem != inLoop1){
                if(tem == inLoop2){
                    isCross = true;
                }
                tem = tem.getNext();
            }
            if(!isCross){
                // 说明不相交
                return null;
            }else{
                // 说明相交，返回任意一个即可
                return inLoop1;
            }
        }
    }

}
