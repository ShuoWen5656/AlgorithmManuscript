package leetcode;

import java.util.PriorityQueue;

/**
 * @author swzhao
 * @date 2023/12/2 10:31 上午
 * @Discreption <> 数据流的中位数
 */
public class MedianFinder {

    /**
     * 大跟对
     */
    PriorityQueue<Integer> maxQueue;

    /**
     * 小根堆
     */
    PriorityQueue<Integer> minQueue;



    public MedianFinder() {
        this.maxQueue = new PriorityQueue<>(16, ((o1, o2) -> {
            return o2 - o1;
        }));
        this.minQueue = new PriorityQueue<>(16, (o1, o2) -> {
            return o1 - o2;
        });
    }

    public void addNum(int num) {
        if (maxQueue.isEmpty()) {
            maxQueue.offer(num);
            return;
        }
        if (num <= maxQueue.peek()) {
            maxQueue.offer(num);
        }else {
            minQueue.offer(num);
        }
        if (Math.abs(maxQueue.size() - minQueue.size()) >= 2) {
            if (maxQueue.size() > minQueue.size()) {
                minQueue.offer(maxQueue.poll());
            }else {
                maxQueue.offer(minQueue.poll());
            }
        }
    }

    public double findMedian() {
        if (maxQueue.isEmpty() && minQueue.isEmpty()) {
            return 0d;
        }else if (maxQueue.size() == minQueue.size()) {
            return  (((double)maxQueue.peek() + (double)minQueue.peek())/2);
        }else if (maxQueue.isEmpty()){
            return minQueue.peek();
        }else if (minQueue.isEmpty()) {
            return maxQueue.peek();
        }else {
            // 最正常的情况了
            return maxQueue.size() > minQueue.size() ? maxQueue.peek() : minQueue.peek();
        }
    }


    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.findMedian();
        medianFinder.addNum(3);
        medianFinder.findMedian();
    }

}
