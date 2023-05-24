package classespackage.other;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2023/5/24 19:51
 * @Discreption <> 一个普通的堆结构，大根堆
 */
public class HeapCommon {

    private int[] heap;

    /**
     * 记录当前堆中有多少个元素
     */
    private int size;

    public HeapCommon(int len) {
        this.heap = new int[len];
        this.size = 0;
    }


    /**
     * 插入堆中
     * @param num
     */
    public void heapInsert(int num) {
        heap[size++] = num;
        // 上浮
        int cur = size-1;
        while (cur != 0) {
            int parent = (size - 1)/2;
            if (heap[parent] < heap[cur]) {
                CommonUtils.swap(heap, parent, cur);
                cur = parent;
            }else {
                break;
            }
        }
    }


    /**
     * 堆化: 从头结点下沉
     */
    public void heapify() {
        int cur = 0;
        int left = (2*cur) + 1;
        while (left < size) {
            int max = heap[left] > heap[cur] ? left : cur;
            if (left + 1 < size && heap[left + 1] > heap[max]) {
                max = left + 1;
            }
            if (max == cur) {
                break;
            }
            CommonUtils.swap(heap, max, cur);
            cur = left;
            left = cur*2 + 1;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int getSize() {
        return heap.length;
    }


    public int getHead() {
        if (size == 0) {
            throw new RuntimeException("堆为空");
        }
        return heap[0];
    }


    /**
     * 弹出head
     * @return
     */
    public int popHead() {
        int res = heap[0];
        CommonUtils.swap(heap, 0, size-1);
        size--;
        heapify();
        return res;
    }

    public static void main(String[] args) {
        HeapCommon heapCommon = new HeapCommon(4);
        heapCommon.heapInsert(3);
        heapCommon.heapInsert(2);
        heapCommon.heapInsert(4);

    }







}
