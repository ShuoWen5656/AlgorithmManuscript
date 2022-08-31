package classespackage.link;

import classespackage.CommonUtils;
import dataConstruct.LinkNode;

import java.util.Objects;

/**
 *
 * @author swzhao
 * @date 2021/12/29 9:29 下午
 * @Discreption <> 环形单链表的约瑟夫问题
 */public class JosePhosKill {

    /**
     * 优化版:记住公式old = （new + m -1）/i +1, old老编号，new为新编号，递归求老编号
     * @param head
     * @param num
     * @return
     */
    public LinkNode kill2(LinkNode head, int num){
        try{
            LinkNode cur = head;
            int tem = 1;
            int result = 1;
            // 环形链表长度遍历
            while (cur.getNext() != head){
                tem ++;
                cur = cur.getNext();
            }
            result = getLive(tem, num);
            while (--result != 0){
                head = head.getNext();
            }
            // 幸存者变成head
            head.setNext(head);
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
        return (getLive(tem - 1, num) + num - 1) % tem + 1;
    }


    /**
     * 说道num和num的倍数的人自杀
     * @param head
     * @param num
     * @return
     */
    public static LinkNode myKill(LinkNode head, int num){
        if (head == null || head.getNext() == null){
            return head;
        }
        // 成环
        int count = 0;
        LinkNode cur = head;
        LinkNode pre = null;
        while (cur != pre){
            count ++;
            if (count%num == 0){
                // 删除当前
                // 节点
                pre.setNext(cur.getNext());
                cur.setNext(null);
                cur = pre.getNext();
                continue;
            }
            if (pre == null){
                pre = head;
            }else {
                pre = pre.getNext();
            }
            cur = cur.getNext();
        }
        head = cur;
        return cur;
    }


    /**
     * 利用递归方式直接找到需要删除的节点
     * @param head
     * @param num
     * @return
     */
    public static LinkNode mykill2(LinkNode head, int num){
        if (head == null || head.getNext() == null){
            return head;
        }
        int len = 1;
        LinkNode cur = head.getNext();
        while (cur != head){
            len++;
            cur = cur.getNext();
        }
        int removeNum = getNum(len, num);
        while (--removeNum != 0) {
            head = head.getNext();
        }
        head.setNext(head);
        return head;
    }

    /**
     * 递归从new一层层获取到old
     * @param len
     * @param num
     * @return
     */
    private static int getNum(int len, int num) {
        if (len == 1){
            return 1;
        }
        return (getNum(len-1, num) + (num-1)%len)%len + 1;
    }


    public static void main(String[] args) {
        LinkNode linkNodeListByArr = CommonUtils.getLinkNodeListByArr(new int[]{2, 4, 6, 3, 9});
        // 成环
        LinkNode cur = linkNodeListByArr;
        while (cur.getNext() != null){
            cur = cur.getNext();
        }
        cur.setNext(linkNodeListByArr);
        JosePhosKill josePhosKill = new JosePhosKill();
        LinkNode linkNode = josePhosKill.kill2(linkNodeListByArr, 3);


//        LinkNode linkNode = mykill2(linkNodeListByArr, 3);
        System.out.println(linkNode);
    }


}
