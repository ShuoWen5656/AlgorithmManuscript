package classespackage;

import dataConstruct.LinkNode;

import java.util.Objects;

/**
 *
 * @author swzhao
 * @date 2021/12/29 9:29 下午
 * @Discreption <>
 */public class JosePhosKill {

     public LinkNode kill1(LinkNode head, int num){
         LinkNode pre = head.getNext();
         LinkNode cur = head;
         int count = 0;
         if (Objects.isNull(head.getNext())){
             return head;
         }
         while (cur.getNext() != null){
             count ++;
             if (count%num == 0){
                 // 删除pre
                 cur.setNext(pre.getNext());
                 pre = cur.getNext();
                 count = 0;
             }else{
                 cur = cur.getNext();
                 pre = pre.getNext();
             }
         }
         return cur;
     }

    /**
     * 优化版:记住公式old = （new + m -1）/i +1, old老编号，new为新编号，递归求老编号
     * @param head
     * @param num
     * @return
     */
    public LinkNode kill2(LinkNode head, int num){
        try{
            LinkNode cur = head;
            int tem = 0;
            int result = 1;
            // 环形链表长度遍历
            while (cur.getNext() != head){
                tem ++;
            }
            result = getLive(tem, num);
            while (--result != 0){
                head = head.getNext();
            }
            // 幸存者变成head
            head = head.getNext();
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }

    /**
     * 递归需要知道上一个状态和下一个状态的转换关系：tem长度的链表，数到num的节点去掉
     * @param tem
     * @param num
     * @return
     * @throws Exception
     */
    private int getLive(int tem, int num) throws Exception{
        if(tem == 1){
            return 1;
        }
        return (getLive(tem - 1, num) + num - 1) / tem + 1;
    }
}
