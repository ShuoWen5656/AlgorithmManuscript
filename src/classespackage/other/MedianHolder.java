package classespackage.other;

import utils.MaxHeapComparater;
import utils.MinHeapComparater;

/**
 * @author swzhao
 * @data 2022/7/24 9:53
 * @Discreption <> 随时找到数据流的中位数
 */
public class MedianHolder {

    /**
     * 大根堆(存小数组)
     */
    private MyHeap<Integer> maxHeap;
    /**
     * 小根堆（存大数组）
     */
    private MyHeap<Integer> minHeap;


    public MedianHolder() {
        this.maxHeap = new MyHeap<>(new MaxHeapComparater());
        this.minHeap = new MyHeap<>(new MinHeapComparater());
    }

    /**
     * 添加一个元素
     * @param num
     */
    public void addElement(Integer num){
        // 优先大根堆
        if (this.maxHeap.isEmpty()){
            this.maxHeap.add(num);
            return;
        }
        if (this.maxHeap.getHead() >= num){
            // 小于大根堆最大的数，应该放进大根堆
            this.maxHeap.add(num);
        }else {
            if (this.minHeap.isEmpty()){
                this.minHeap.add(num);
            }
            if (this.minHeap.getHead() > num){
                // 如果num大于大根堆的最大值，小于小根堆的最小值，则优先加入大根堆中
                this.maxHeap.add(num);
            }else {
                this.minHeap.add(num);
            }
        }
        this.modifyTowHeapsSize();
    }

    /**
     * 调整两个堆的大小，两个堆之间的大小不能超过2
     */
    private void modifyTowHeapsSize() {
        if (this.maxHeap.getSize() == this.minHeap.getSize()+2){
            this.minHeap.add(this.maxHeap.popHead());
        }
        if (this.minHeap.getSize() == this.maxHeap.getSize()+2){
            this.maxHeap.add(this.minHeap.popHead());
        }
    }

    /**
     * 获取中位数
     * @return
     */
    public Integer getMedian(){
        int maxHeapSize = this.maxHeap.getSize();
        int minHeapSize = this.minHeap.getSize();
        if (maxHeapSize + maxHeapSize == 0){
            return null;
        }
        // 两个堆顶拿出来
        Integer maxHead = (Integer) maxHeap.getHead();
        Integer minHead = minHeap.getHead();
        if ((maxHeapSize == minHeapSize)){
            // 相等，中位数为相加
            return (maxHead+minHead)/2;
        }else if (maxHeapSize > minHeapSize){
            // 大根堆大
            return maxHead;
        }else{
            return minHead;
        }
    }
}
