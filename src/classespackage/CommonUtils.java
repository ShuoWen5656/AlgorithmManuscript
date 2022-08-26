package classespackage;

import dataConstruct.DoubleNode;
import dataConstruct.LinkNode;

/**
 * @Author swzhao
 * @Date 2022/2/13 21:02
 * @Discription<>公共方法
 */
public class CommonUtils<T> {

    /**
     * 交换1、2索引值
     * @param array
     * @param index1
     * @param index2
     */
    public static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * 获取中间值，如果为偶数，则取前一个
     * @param left
     * @param right
     */
    public static int getMid(int left, int right){
        return (left + right) /2;
    }



    public static <T> void swapPlus(T[] array, int index1, int index2){
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static int getMaxValue(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
        }
        return max;
    }


    /**
     * 形成一个单链表返回头节点
     * @param arr
     * @return
     */
    public static LinkNode getLinkNodeListByArr(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        LinkNode head = new LinkNode(arr[0]);
        LinkNode cur = head;
        for (int i = 1; i < arr.length; i++){
            cur.setNext(new LinkNode(arr[i]));
            cur = cur.getNext();
        }
        return head;
    }

    /**
     * 形成一个双向链表返回
     * @param arr
     * @return
     */
    public static DoubleNode getDoubleNodeListByArr(int[] arr){
        if (arr == null || arr.length == 0){
            return null;
        }
        DoubleNode head = new DoubleNode(String.valueOf(arr[0]));
        DoubleNode cur = head;
        for (int i = 1; i < arr.length; i++){
            DoubleNode newNode = new DoubleNode(String.valueOf(arr[i]));
            cur.setNext(newNode);
            newNode.setLast(cur);
            cur = cur.getNext();
        }
        return head;
    }


    /**
     * 打印单链表结构
     * @param linkNode
     */
    public static void printLinkNode(LinkNode linkNode){
        LinkNode cur = linkNode;
        while (cur != null){
            System.out.println(cur.getValue());
            cur = cur.getNext();
        }
    }


}
