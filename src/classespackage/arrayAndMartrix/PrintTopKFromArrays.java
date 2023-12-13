package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;
import dataConstruct.HeapNode;

/**
 * @author swzhao
 * @data 2022/6/21 22:50
 * @Discreption <> 打印N个数组整体最大的topK
 * N个数组都是从小到大的，打印topk个数字
 */
public class PrintTopKFromArrays {

    /**
     * 主方法
     * @param array
     * @param k
     */
    public static void printTopK(int[][] array, int k){
        try {
            int heapSize = array.length;
            // 所有数组的最值容器
            HeapNode[] heap = new HeapNode[array.length];
            for (int i = 0; i < array.length; i++){
                // 最后一个值放入heap
                int index = array[i].length-1;
                // 将最后一个值放入heap中
                heap[i] = new HeapNode(array[i][index], i, index);
                // 对当前位置进行上浮操作
                heapInsert(heap, i);
            }
            System.out.println("Top k is:");
            for (int i = 0; i < k; i++){
                if (heapSize == 0){
                    break;
                }
                // 先输出一个
                System.out.println(heap[0].getValue() + " ");
                if (heap[0].getIndex() != 0){
                    // 不是最后一个数，则当前数组中当前值的前一个继续
                    int index = heap[0].getIndex();
                    heap[0].setValue(array[heap[0].getArrayID()][--index]);
                    heap[0].setIndex(index);
                }else {
                    // 如果当前数组已经遍历完成，将当前数移动至最后一个，相当于丢弃，因为后面的堆排序都是基于heapSize-1来进行的
                    swap(heap, 0, --heapSize);
                }
                // 剩下的进行堆排序，从根节点开始往下沉
                heapify(heap, 0, heapSize);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * i位置的数在heap中进行下沉
     * @param heap
     * @param i
     * @param heapSize
     */
    private static void heapify(HeapNode[] heap, int i, int heapSize) {
        // i的左右孩子
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while (left < heapSize){
            if (heap[left].getValue() > heap[i].getValue()){
                // 左孩子比自己大的话,最大值指向左孩子
                largest = left;
            }
            if (right == heapSize && heap[right].getValue() > heap[largest].getValue()){
                largest = right;
            }
            if (largest != i){
                swap(heap, i , largest);
            }else {
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    /**
     *
     * @param heap
     * @param i
     */
    private static void heapInsert(HeapNode[] heap, int i) {
        while (i != 0){
            int parent = (i - 1) / 2;
            if (heap[parent].getValue() < heap[i].getValue()){
                // 新来的值比parent大，新来的需要往上升
                swap(heap, parent, i);
                i = parent;
            }else {
                // 新来的最值比parent还小
                break;
            }
        }
    }

    /**
     * 交换对象位置
     * @param heap
     * @param parent
     * @param i
     */
    private static void swap(HeapNode[] heap, int parent, int i) {
        HeapNode heapNode = heap[parent];
        heap[parent] = heap[i];
        heap[i] = heapNode;
    }


    /**
     * 二轮测试：打印n个数组的topk
     * @param arr
     * @return
     */
    public static int[] getTopKFormNArr(int[][] arr, int k) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return null;
        }
        // 大小为n的堆
        HeapNode[] heap = new HeapNode[arr.length];
        int indexForHeap = 0;
        for (int i = 0; i < arr.length; i++) {
            // 数组从小到大，所以放每一行数组的最后一个数
            heapInsertCp1(heap, indexForHeap,new HeapNode(arr[i][arr[i].length-1], i, arr[i].length-1));
            indexForHeap++;
        }
        int[] res = new int[k];
        // 开始计算Topk,弹出k个值
        for (int i = 0; i < k; i++) {
            HeapNode top = heap[0];
            // 每一次都是堆顶
            res[i] = top.getValue();
            // 堆顶需要弹出:如果堆顶不是自己队列最后一个数，则自己队列找到顶替者，否则堆大小减少一个
            if (top.getIndex() == 0) {
                // 最后一个数,则将头替换成尾巴
                heap[0] = heap[indexForHeap-1];
                // 减少一个长度
                indexForHeap--;
            }else {
                // 不是最后一个数,找到顶替者
                heap[0] = new HeapNode(arr[top.getArrayID()][top.getIndex()-1], top.getArrayID(), top.getIndex()-1);
                // 下沉
                heapifyCp1(heap, indexForHeap);
            }
        }
        return res;
    }

    /**
     * 从顶开始下城
     * @param heap
     * @param len
     */
    private static void heapifyCp1(HeapNode[] heap, int len) {
        int parent = 0;
        int left = (2 * parent) + 1;
        while (left < len) {
            int maxIndex = heap[parent].getValue() > heap[left].getValue() ? parent : left;
            int right = left + 1;
            if (right < len && heap[right].getValue() > heap[parent].getValue()) {
                maxIndex = right;
            }
            if (maxIndex == parent) {
                break;
            }else {
                swap(heap, parent, maxIndex);
            }
            parent = maxIndex;
            left = (2*parent)+1;
        }
    }

    /**
     * 将heapnode插入heap中并上浮
     * @param heap
     * @param index
     * @param heapNode
     */
    private static void heapInsertCp1(HeapNode[] heap, int index, HeapNode heapNode) {
        heap[index] = heapNode;
        // 上浮
        int cur = index;
        while (cur != 0) {
            int parent = (cur-1)/2;
            if (heap[parent].getValue() < heap[cur].getValue()) {
                // 上浮
                swap(heap, parent, cur);
            }
            cur = parent;
        }
    }

    public static void main(String[] args) {
        CommonUtils.printArr(        getTopKFormNArr(new int[][]{
                {1,2,3,4,5,6},
                {3,5,8},
                {9,10,14}
        }, 5));

    }



}
