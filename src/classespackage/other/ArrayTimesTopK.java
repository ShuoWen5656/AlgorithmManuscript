package classespackage.other;

import classespackage.CommonUtils;
import dataConstruct.TimesNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/7/29 21:30
 * @Discreption <> 出现次数的TopK问题
 */
public class ArrayTimesTopK {

    /**
     * 给定数组，获取次数在前k的数列表
     * @param array
     * @param topK
     * @return
     */
    public static void getTopK(String[] array, int topK){
        if (array == null || array.length == 0 || topK <= 0){
            return;
        }
        // 统计数组
        Map<String, Integer> map = new HashMap<>();
        for (String str : array){
            if (map.containsKey(str)){
                map.put(str, map.get(str)+1);
            }else {
                map.put(str, 1);
            }
        }
        // 小根堆，次数为依据
        TimesNode[] heap = new TimesNode[topK];
        // 堆中的索引
        int index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            TimesNode timesNode = new TimesNode(entry.getKey(), entry.getValue());
            // 判断是否有资格能够进入堆
            TimesNode head = heap[0];
            if (head.getTimes() < timesNode.getTimes()){
                if (index < topK){
                    // 堆没满直接插入即可
                    insertHeap(heap, index++, timesNode);
                }else {
                    // 替换头
                    heap[0] = timesNode;
                    // 调整堆
                    heapify(heap, 0 , index-1);
                }
            }
        }
        // 从大到小打印堆元素
        // 先排序
        for (int i = index-1; i > 0; i--){
            // 从后往前每一个元素都当一次头然后开始下沉
            CommonUtils.swapPlus(heap, 0, i);
            heapify(heap, 0,  i);
        }
        for (int i = 0; i < heap.length; i++){
            TimesNode timesNode = heap[i];
            System.out.println(String.format("No.%s:%s", timesNode.getStr(), timesNode.getTimes()));
        }
    }

    /**
     * 从head开始下沉
     * @param heap
     * @param i
     * @param index
     */
    private static void heapify(TimesNode[] heap, int i, int index) {
        int left = i*2+1;
        int right = i*2+2;
        int small = i;
        while (left < index){
            if (heap[left].getTimes() < heap[small].getTimes()){
                small = left;
            }
            if (heap[right].getTimes() < heap[small].getTimes()){
                small = right;
            }
            if (small != i){
                // 下沉
                CommonUtils.swapPlus(heap, small, i);
                i = small;
                left = i*2+1;
                right = i*2+2;
            }else {
                break;
            }
        }


    }

    /**
     * 插入新元素到尾巴并进行堆调整，前提是index不超过堆大小的情况
     * @param heap
     * @param index
     * @param timesNode
     */
    private static void insertHeap(TimesNode[] heap, int index, TimesNode timesNode) {
        heap[index] = timesNode;
        // 调整开始
        int parent = 0;
        while (index > 0){
            parent = (index-1)/2;
            if (heap[index].getTimes() < heap[parent].getTimes()){
                // 替换
                CommonUtils.swapPlus(heap, index, parent);
                index = parent;
            }else{
                break;
            }
        }
    }


}
