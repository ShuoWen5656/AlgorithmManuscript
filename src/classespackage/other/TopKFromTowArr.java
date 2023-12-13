package classespackage.other;

import classespackage.CommonUtils;
import dataConstruct.HeapNodeForTopK;

import java.util.HashSet;
import java.util.Set;

/**
 * @author swzhao
 * @data 2022/7/27 19:49
 * @Discreption <> 两个有序数组间相加和的TOPK问题
 */
public class TopKFromTowArr {


    /**
     * 主方法
     * @param arr1
     * @param arr2
     * @param topK
     */
    public static int[] getTopSum(int[] arr1, int[] arr2, int topK){
        if (arr1 == null || arr2 == null){
            return new int[0];
        }
        // 如果比所有结果都大那么最终的堆排序就是结果
        topK = Math.min(topK, arr1.length * arr2.length);
        // 这里的长度定义是：第一次堆进一个，后面都是进2 出 1，所以总共最大需要topK+1
        HeapNodeForTopK[] heap = new HeapNodeForTopK[topK + 1];
        int heapR = arr1.length-1;
        int heapC = arr2.length-1;
        int upR = -1;
        int upC = -1;
        int lR = -1;
        int lC = -1;
        int heapSize = 0;
        // 第一个最大的数先放入
        insertHeap(heap, heapSize++, heapR, heapC, arr1[heapR] + arr2[heapC]);
        // 去重set
        Set<String> positionSet = new HashSet<>();
        int[] res = new int[topK];
        int resIndex = 0;
        while (resIndex != topK-1){
            // 弹出
            HeapNodeForTopK head = popHeap(heap, heapSize--);
            // 当前位置
            heapR = head.getRow();
            heapC = head.getCol();
            // 结果赋值
            res[++resIndex] = head.getValue();
            // 上位置
            upR = heapR-1;
            upC = heapC;
            // 左位置
            lR = heapR;
            lC = heapC-1;
            // 如果没有重复，则入堆
            if (upR >= 0 && !isContains(upR, upC, positionSet)){
                // 入堆
                insertHeap(heap, heapSize++, upR, upC, arr1[upR] + arr2[upC]);
                // 入set
                addSet(positionSet, upR, upC);
            }
            if (lC >= 0 && !isContains(lR, lC, positionSet)){
                insertHeap(heap, heapSize++, lR, lC, arr1[lR] + arr2[lC]);
                addSet(positionSet, lR, lC);
            }
        }
        return res;
    }

    private static void addSet(Set<String> positionSet, int upR, int upC) {
        positionSet.add(upR+"_"+upC);
    }

    private static boolean isContains(int upR, int upC, Set<String> positionSet) {
        return positionSet.contains(upR+"_"+upC);
    }

    /**
     * 弹出堆顶
     * @param heap
     * @param heapSize
     * @return
     */
    private static HeapNodeForTopK popHeap(HeapNodeForTopK[] heap, int heapSize) {
        // 顶替换到尾巴
        HeapNodeForTopK head = heap[0];
        CommonUtils.swapPlus(heap, 0, heapSize-1);
        heap[heapSize-1] = null;
        // 弹出成功,开始调整堆
        heapify(heap, 0,heapSize);
        return head;
    }

    /**
     * 堆操作,当前被替换到堆顶的进行下沉
     * @param heap
     * @param index
     * @param heapSize
     */
    private static void heapify(HeapNodeForTopK[] heap, int index, int heapSize) {
        int left = index*2+1;
        int right = index*2+2;
        // 三者最大值是哪个
        int largest = index;
        while (left < heapSize){
            if (heap[left].getValue() > heap[largest].getValue()){
                largest = left;
            }
            if (heap[right].getValue() > heap[largest].getValue()){
                largest = right;
            }
            // largest确定后swap
            if (largest == index){
                break;
            }else {
                CommonUtils.swapPlus(heap, index, largest);
            }
            // 更新下一波比较
            left = largest*2+1;
            right = largest*2+2;
        }
    }

    private static void insertHeap(HeapNodeForTopK[] heap, int tail, int heapR, int heapC, int value) {
        HeapNodeForTopK heapNodeForTopK = new HeapNodeForTopK(value, heapR, heapC);
        heap[tail] = heapNodeForTopK;
        while (tail != 0){
            int parent = (tail - 1)/2;
            if (heap[tail].getValue() > heap[parent].getValue()){
                // 上浮
                CommonUtils.swapPlus(heap, tail, parent);
                // 位置变更
                tail = parent;
                parent = (parent-1)/2;
            }else{
                break;
            }
        }
    }


    /**
     * 获取两个有序数组中的相加和topk
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] getTopKFromArrCp1(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr2 == null
                || arr1.length == 0 || arr2.length == 0
                || arr1.length < k || arr2.length < k) {
            return null;
        }
        int row = arr1.length-1;
        int col = arr2.length-1;
        // 存储topk的容器
        RowColNode[] heap = new RowColNode[k+1];
        heap[0] = new RowColNode(row, col, arr1[row] + arr2[col]);
        int count = k;
        int[] res = new int[k];
        int heapSize = 1;
        while (k > 0) {
            // 弹出堆顶
            RowColNode head = heap[0];
            res[count-k] = head.getValue();
            CommonUtils.swapPlus(heap, 0, heapSize-1);
            heap[heapSize-1] = null;
            heapSize--;
            heapifyCp1(heap, heapSize);
            // 加入左边和上边的两个元素
            row = head.getRow();
            col = head.getCol();
            if (row - 1 >= 0) {
                heap[heapSize++] = new RowColNode(row-1, col, arr1[row-1] + arr2[col]);
                // 上浮
                heapInsertCp(heap, heapSize);
            }
            if (col - 1 >= 0) {
                heap[heapSize++] = new RowColNode(row, col-1, arr1[row] + arr2[col-1]);
                heapInsertCp(heap, heapSize);
            }
            k--;
        }
        return res;
    }

    private static void heapInsertCp(RowColNode[] heap, int heapSize) {
        int cur = heapSize-1;
        int parent = (cur-1)/2;
        while (cur != 0) {
            if (heap[cur].getValue() > heap[parent].getValue()) {
                CommonUtils.swapPlus(heap, cur, parent);
                cur = parent;
            }else {
                break;
            }
        }
    }

    private static void heapifyCp1(RowColNode[] heap, int heapSize) {
        int parent = 0;
        int left = (parent * 2) + 1;
        while (left < heapSize) {
            int maxIndex = heap[parent].getValue() > heap[left].getValue() ? parent : left;
            int right = left + 1;
            while (right < heapSize && heap[right].getValue() >heap[maxIndex].getValue()) {
                maxIndex = right;
            }
            if (maxIndex == parent) {
                break;
            }
            CommonUtils.swapPlus(heap, parent, maxIndex);
            parent = maxIndex;
            left = (parent * 2) + 1;
        }
    }


    public static void main(String[] args) {
        CommonUtils.printArr(getTopKFromArrCp1(new int[]{2,3,4,5,6}, new int[]{5,6,7,8,9}, 3));
    }


    public static class RowColNode {

        int row;
        int col;
        int value;

        public RowColNode(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }





}
