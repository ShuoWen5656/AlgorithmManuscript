package classespackage.other;

import classespackage.CommonUtils;
import dataConstruct.TimesNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/7/30 14:57
 * @Discreption <> 出现次数的TOPK问题：进阶问题
 * 设计实现Tok结构，不断加入字符串并且能够在add时间复杂度logn，printTopK复杂度O（k）下进行操作
 */
public class TopKRecord {

    /**
     * 存储topk的小根堆
     */
    private TimesNode[] heap;
    /**
     * 当前值应该放入小根堆的什么地方
     */
    private int index;
    /**
     * "A" -> ("A", 1)
     */
    private Map<String, TimesNode> strNodeMap;
    /**
     * ("A", 1) -> 6 (所在heap中的位置)
     */
    private Map<TimesNode, Integer> nodeIndexMap;


    /**
     * 初始化制定top几
     * @param k
     */
    public TopKRecord(int k) {
        this.heap = new TimesNode[k];
        this.index = 0;
        this.strNodeMap = new HashMap<>();
        this.nodeIndexMap = new HashMap<>();
    }

    /**
     * 最重要的方法
     * 加入一个str，调整堆和map
     * @param str
     */
    public void add(String str){
        if (!this.strNodeMap.containsKey(str)){
            // 第一次出现
            TimesNode timesNode = new TimesNode(str, 1);
            strNodeMap.put(str, timesNode);
        }else {
            TimesNode timesNode = strNodeMap.get(str);
            // 不是第一次出现,出现次数+1
            timesNode.setTimes(timesNode.getTimes()+1);
        }
        insertHeap(strNodeMap.get(str));
    }



    /**
     * 输出次数在前k的数
     */
    public void printTopK(){
        for (int i = 0; i < this.heap.length; i++){
            if (heap[i] != null){
                System.out.println(String.format("No.%s: %s times", heap[i].getStr(), heap[i].getTimes()));
            }
        }
    }

    /**
     * 将timenode加入到堆中
     * 1、判断是否有资格加入堆
     * 2、没有资格直接退出，更新nodeIndexMap
     * 3、有资格则看插入堆底还是堆顶，在进行heapify操作
     * @param timesNode
     */
    private void insertHeap(TimesNode timesNode) {
        // 判断是否堆满了
        if (this.index == heap.length){
            // 满了, 判断堆顶
            if (timesNode.getTimes() > heap[0].getTimes()){
                // 有资格
                TimesNode oldHead = heap[0];
                // 替换掉head，并更新head的index
                heap[0] = timesNode;
                nodeIndexMap.put(oldHead, -1);
                // 调整堆
                heapify(0);
            }else {
                // 没资格,也要更新nodeIndexMap, -1表示不在堆里面
                nodeIndexMap.put(timesNode, -1);
            }
        }else{
            // 未满,插入堆中
            this.heap[index++] = timesNode;
            // 调整
            int child = index;
            while (child > 0){
                int parent = (child-1)/2;
                if (heap[parent].getTimes() > heap[child].getTimes()){
                    // 交换
                    CommonUtils.swapPlus(heap, parent, child);
                    // 更新nodeIndexMap
                    nodeIndexMap.put(heap[parent], parent);
                    nodeIndexMap.put(heap[child], child);
                    // 更新par和child
                    child = parent;
                    parent = (child-1)/2;
                }else {
                    break;
                }
            }
        }
    }

    /**
     * 从start开始往下沉
     * @param start
     */
    private void heapify(int start) {
        int left = start*2+1;
        int right = start*2+1;
        int smallest = start;
        while (left < index){
            if (heap[left].getTimes() < heap[smallest].getTimes()){
                smallest = left;
            }
            if (heap[right].getTimes() < heap[smallest].getTimes()){
                smallest = right;
            }
            if (smallest != start){
                // 需要交换
                CommonUtils.swapPlus(heap, smallest, start);
                // 交换后需要更新nodeIndex
                nodeIndexMap.put(heap[smallest], smallest);
                nodeIndexMap.put(heap[start], start);
            }else {
                break;
            }
            // 更新
            start = smallest;
            left = smallest*2+1;
            right = smallest*2+1;
        }
    }


}
