package classespackage.other;

import classespackage.CommonUtils;

import java.util.Comparator;
import java.util.Map;

/**
 * @author swzhao
 * @data 2023/5/28 19:14
 * @Discreption <> 公共堆工具
 */
public class CommonHeapPlus<T> {

    /**
     * 堆数组
     */
    private Object[] heap;

    /**
     * 堆大小
     */
    private int index;

    /**
     * 比较规则:默认大根堆
     */
    private Comparator<T> comparator;

    /**
     * 仿照Arraylist的初始化方式
     * @param size
     */
    public CommonHeapPlus(int size, Comparator<T> comparator) {
        this.heap = new Object[size];
        this.comparator = comparator;
    }


    /**
     * 插入并上浮
     * 返回最终插入的元素所在的位置
     */
    public int heapInsert(T elm) {
        if (index == heap.length) {
            // 堆满了
            throw new RuntimeException("堆满了,暂时不支持扩容");
        }
        heap[index++] = elm;
        int cur = index - 1;
        while (cur != 0) {
            int parent = (cur-1)/2;
            if (comparator.compare((T)heap[cur], (T)heap[parent]) > 0) {
                CommonUtils.swapPlus(heap, parent, cur);
                cur = parent;
            }else {
                break;
            }
        }
        return cur;
    }

    /**
     * 定制化接口
     * @return
     */
    public int heapInsetForTopK(T elm , Map<T, Integer> map) {
        if (index == heap.length) {
            // 堆满了
            throw new RuntimeException("堆满了,暂时不支持扩容");
        }
        heap[index++] = elm;
        int cur = index - 1;
        // 初始化位置
        map.put(elm, cur);
        while (cur != 0) {
            int parent = (cur-1)/2;
            if (comparator.compare((T)heap[cur], (T)heap[parent]) > 0) {
                CommonUtils.swapPlus(heap, parent, cur);
                // 交换的时候需要更新index
                map.put((T)heap[cur], cur);
                map.put((T)heap[parent], parent);
                cur = parent;
            }else {
                break;
            }
        }
        return cur;
    }


    /**
     * 下沉
     */
    public void heapify(int head) {
        int cur = head;
        int left = (cur * 2) + 1;
        while (left < index) {
            int max = comparator.compare((T) heap[left], (T) heap[cur]) > 0 ? left : cur;
            int right = left + 1;
            while (right < index && comparator.compare((T)heap[right], (T) heap[max]) > 0) {
                max = right;
            }
            if (max == cur) {
                break;
            }
            CommonUtils.swapPlus(heap, cur, max);
            cur = max;
            left = (cur * 2) + 1;
        }
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {
        return index == heap.length;
    }


    public T getHead() {
        return (T) heap[0];
    }




    /**
     * 弹出头节点
     * @return
     */
    public T popHead() {
        T res = (T) heap[0];
        CommonUtils.swapPlus(heap, 0, index-1);
        index--;
        heapify(0);
        return res;
    }

    /**
     * 获取当前堆的大小
     * @return
     */
    public int getIndex() {
        return index;
    }

}
