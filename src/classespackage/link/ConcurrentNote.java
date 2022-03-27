package classespackage.link;

import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2021/12/18 8:56 下午
 * @Discreption <>  打印两个有序链表的公共部分，有序链表获取公共节点：这里是从小到大的顺序
 */
public class ConcurrentNote {

    public LinkNode getConcurrentNode(LinkNode head1, LinkNode head2){
        try {
            LinkNode tem1 = head1;
            LinkNode tem2 = head2;
            while (tem1.getValue() != tem2.getValue() && tem1.getNext() != null && tem2.getNext() != null){
                if(tem1.getValue() < tem2.getValue()){
                    // 较小的需要往下走一格
                    tem1 = tem1.getNext();
                }else{
                    tem2 = tem2.getNext();
                }
            }
            if(tem1.getValue() != tem2.getValue()){
                return null;
            }
            return tem1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
