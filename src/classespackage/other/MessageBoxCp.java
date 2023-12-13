package classespackage.other;

import dataConstruct.DoubleNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2023/5/6 20:28
 * @Discreption <> 一种消息接受并打印的结构设计
 */
public class MessageBoxCp {

    /**
     * 头节点列表：key代表某个值，value代表以该值为头的区间头结点
     */
    private Map<Integer, DoubleNode> headList;

    /**
     * 尾节点列表：key代表某个值，value代表以改值为尾的区间尾节点
     */
    private Map<Integer, DoubleNode> tailList;

    /**
     * 已经打印的节点列表的最后一个节点
     */
    private DoubleNode lastNode;


    public MessageBoxCp() {
        this.headList = new HashMap<>();
        this.tailList = new HashMap<>();
        this.lastNode = new DoubleNode(0);
    }

    /**
     * 接收值
     * @param num
     */
    public void reveive(Integer num) {
        if (num < 0) {
            return;
        }
        // 首先自成区间
        DoubleNode cur = new DoubleNode(num);
        headList.put(num, cur);
        tailList.put(num, cur);
        // 判断前面是否能连上
        if (tailList.containsKey(num - 1)) {
            // 1、更新上一个链表结尾
            DoubleNode last = tailList.get(num - 1);
            last.setNext(cur);
            // 2、删除上一个尾巴，cur为新的尾巴
            tailList.remove(num - 1);
            // 3、num为新的尾巴就不会是头了
            headList.remove(num);
        }
        if (headList.containsKey(num + 1)) {
            // 1、更新链表节点
            cur.setNext(headList.get(num + 1));
            // 2、删除老头
            headList.remove(num + 1);
            // 3、num为新的头就不会是尾巴了
            tailList.remove(num);
        }
        // 判断是否需要打印
        if (num == lastNode.getIntegerValue() + 1) {
            print();
        }
    }

    /**
     * 打印值
     */
    private void print(){
        //打印值
        int curNum = lastNode.getIntegerValue()+1;
        DoubleNode head = headList.get(curNum);
        DoubleNode curNode = head;
        while (curNode.getNext() != null) {
            System.out.println(curNode.getIntegerValue());
            curNode = curNode.getNext();
        }
        // 再输出一个
        System.out.println(curNode.getIntegerValue());
        // 删除头尾节点
        headList.remove(head.getIntegerValue());
        tailList.remove(curNode.getIntegerValue());
        // 更新last
        lastNode = curNode;
    }



    public static void main(String[] args) {
        MessageBoxCp messageBoxCp = new MessageBoxCp();
        messageBoxCp.reveive(1);
        messageBoxCp.reveive(3);
        messageBoxCp.reveive(4);
        messageBoxCp.reveive(2);
    }

}
