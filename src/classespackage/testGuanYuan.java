package classespackage;

import dataConstruct.LinkNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author swzhao
 * @date 2022/2/28 7:25 下午
 * @Discreption <> 删除指定值的节点
 */
public class testGuanYuan {

    // 13473
    public static LinkNode removeNodeByValue(LinkNode head, Integer num){
        try {
            if(head == null){
                return head;
            }
            LinkNode cur = head.getNext();
            LinkNode pre = head;
            while (pre.getNext() != null){
                if(cur.getValue() == num){
                    // 删除节点
                    pre.setNext(cur.getNext());
                    cur = pre.getNext();
                }else{
                    pre = pre.getNext();
                    cur = cur.getNext();
                }
            }
            // 判断头节点
            if(head.getValue() == num){
                head = head.getNext();
            }
            return head;
        }catch (Exception e){
            e.printStackTrace();
            return head;
        }
    }


    public static void main(String[] args) {
        LinkNode node1 = new LinkNode(1);
        LinkNode node2 = new LinkNode(3);
        LinkNode node3 = new LinkNode(4);
        LinkNode node4 = new LinkNode(7);
        LinkNode node5 = new LinkNode(3);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node1 = removeNodeByValue(node1, 3);
        while (node1 != null){
            System.out.println(node1.getValue());
            node1 = node1.getNext();
        }
    }


    /**
     * 给定矩阵，返回最短路径数量
     * @param x
     * @param y
     * @return
     */
    public static int getNumByXY(int x, int y){
        try{
            if((x == 0 || y == 0)){
                return 1;
            }
            return getNumByXY(x - 1, y) + getNumByXY(x, y-1);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }



}
