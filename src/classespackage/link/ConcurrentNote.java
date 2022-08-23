package classespackage.link;

import dataConstruct.LinkNode;

/**
 * @author swzhao
 * @date 2021/12/18 8:56 下午
 * @Discreption <>  打印两个有序链表的公共部分
 * 有序链表获取公共节点：这里是从小到大的顺序
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



    public static void getCur(LinkNode head1, LinkNode head2){
        while (head1 != null && head2 != null){
            if (head1.getValue() == head2.getValue()){
                System.out.println(head1.getValue());
                head1 = head1.getNext();
                head2 = head2.getNext();
            }else if (head1.getValue() < head2.getValue()){
                head1 = head1.getNext();
            }else {
                head2 = head2.getNext();
            }
        }
    }


    public static void main(String[] args) {
        LinkNode[] linkNodes1 = { new LinkNode(3), new LinkNode(7)};
        LinkNode[] linkNodes2 = {new LinkNode(2), new LinkNode(3), new LinkNode(7), new LinkNode(9)};
        LinkNode head1 = linkNodes1[0];
        LinkNode head2 = linkNodes2[0];
        for (int i = 0; i < linkNodes1.length; i++){
            if (i != linkNodes1.length-1){
                linkNodes1[i].setNext(linkNodes1[i+1]);
            }
        }

        for (int j = 0; j < linkNodes2.length; j++){
            if (j != linkNodes2.length-1){
                linkNodes2[j].setNext(linkNodes2[j+1]);
            }
        }
        getCur(head1, head2);

    }

}
