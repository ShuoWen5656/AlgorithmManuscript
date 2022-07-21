package classespackage.other;

import dataConstruct.LinkNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/7/21 21:38
 * @Discreption <> 一种消息接受并打印的结构设计
 */
public class MessageBox {

    private Map<Integer, LinkNode> headMap;
    private Map<Integer, LinkNode> tailMap;
    private int lastPrint;


    public MessageBox() {
        this.headMap = new HashMap<>();
        this.tailMap = new HashMap<>();
        this.lastPrint = 0;
    }


    /**
     * 接受
     */
    public void receive(int num){
        if (num < 1){
            return;
        }
        // 放入map形成一个气泡
        this.headMap.put(num, new LinkNode(num));
        this.tailMap.put(num, new LinkNode(num));
        if (tailMap.containsKey(num-1)){
            // 新的尾巴诞生了！
            // 只删除头
            LinkNode node = headMap.remove(num);
            // 删除老的尾巴
            LinkNode oldTail = tailMap.remove(num - 1);
            // 链接
            oldTail.setNext(node);
        }
        if (headMap.containsKey(num+1)){
            // 新的头诞生了！
            // 只删除尾巴
            LinkNode node = tailMap.remove(num);
            // 删除老的头结点
            LinkNode oldHead = headMap.remove(num + 1);
            // 链接
            node.setNext(oldHead);
        }
        if (headMap.containsKey(lastPrint+1)){
            print();
        }
    }

    /**
     * 触发打印机制
     */
    private void print() {
        // 从lastprint打印
        LinkNode node = headMap.remove(lastPrint++);
        while (node.getNext() != null){
            System.out.println(node.getValue());
            node = node.getNext();
        }
        // 尾巴也要删除
        lastPrint = node.getValue();
        tailMap.remove(node.getValue());
    }


}
