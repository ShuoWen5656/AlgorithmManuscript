package classespackage.other;

import java.util.Comparator;

/**
 * @author swzhao
 * @data 2023/5/12 20:08
 * @Discreption <> 随时找到数据流的中位数
 */
public class MedianHolderCp1 {

    /**
     * 较小的一半放入这里
     */
    MyHeapCp<Integer> maxHeap;
    /**
     * 较大的一半放入这里
     */
    MyHeapCp<Integer> minHeap;

    public MedianHolderCp1() {
        this.maxHeap = new MyHeapCp<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        this.minHeap = new MyHeapCp<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }


    /**
     * 增加一个数
     * @param num
     */
    public void add(Integer num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }
        int maxHeapSize = this.maxHeap.getSize();
        int minHeapSize = this.minHeap.getSize();
        if (maxHeap.getHead() >= num) {
            maxHeap.add(num);
        }else {
            if (minHeap.isEmpty()) {
                minHeap.add(num);
            }else {
                if (num < minHeap.getHead()) {
                    maxHeap.add(num);
                }else {
                    minHeap.add(num);
                }
            }
        }
        // 调整两个堆
        if (maxHeap.getSize() == minHeap.getSize() + 2) {
            minHeap.add(maxHeap.popHead());
        }else if (minHeap.getSize() == maxHeap.getSize() + 2) {
            maxHeap.add(minHeap.popHead());
        }
    }


    /**
     * 获取中位数
     */
    public Integer getMid() {
        int maxHeapSize = this.maxHeap.getSize();
        int minHeapSize = this.minHeap.getSize();
        if (maxHeapSize == minHeapSize) {
            return (this.maxHeap.getHead() + this.minHeap.getHead()) / 2;
        }else if (maxHeapSize > minHeapSize){
            return this.maxHeap.getHead();
        }else {
            return this.minHeap.getHead();
        }
    }


    public static void main(String[] args) {
        MedianHolderCp1 medianHolderCp1 = new MedianHolderCp1();
        medianHolderCp1.add(6);
        medianHolderCp1.add(1);
        medianHolderCp1.add(3);
        medianHolderCp1.add(0);
        medianHolderCp1.add(9);
        medianHolderCp1.add(8);
        medianHolderCp1.add(7);
        medianHolderCp1.add(2);

        medianHolderCp1.getMid();
    }


}
