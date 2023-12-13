package leetcode;

/**
 * @author swzhao
 * @date 2023/11/30 7:45 下午
 * @Discreption <> 找到第k大的数
 */
public class FindKthLargest {


    public static void main(String[] args) {
        solution(new int[]{3,2,3,1,2,4,5,5,6}, 4);
    }

    /**
     * 弹出len-k个最小的数
     * @param nums
     * @param k
     * @return
     */
    public static int solution(int[] nums, int k) {

        // 小根堆
        int index = 0;
        int[] heap = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (index != heap.length) {
                // 没满
                heapInsert(heap, index++, nums[i]);
            }else {
                // 已经满了,当前值比堆中的最小值还小的就不进去了,如果比对顶大，则将对顶跟尾巴兑换并将当前值放入再次上浮
                if (nums[i] <= heap[0]) {
                    continue;
                }else {
                    // 交换头，并下沉
                    heap[0] = nums[i];
                    heapfiy(heap, 0);
                }
            }
        }
        // 此时堆中是最大的k个值，对顶就是最大中最小的也就是第k大的值
        return heap[0];
    }

    private static void heapfiy(int[] heap, int parent) {
        int left = 2 * parent + 1;
        while (left < heap.length) {
            int minIndex = heap[left] < heap[parent] ? left : parent;
            if (left + 1 < heap.length && heap[left + 1] < heap[minIndex]) {
                minIndex = left + 1;
            }
            if (minIndex == parent) {
                break;
            }
            swap(heap, minIndex, parent);
            parent = minIndex;
            left = parent * 2 + 1;
        }
    }

    private static void swap(int[] heap, int index1, int index2) {
        int tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

    /**
     * 加到index并上浮
     * @param heap
     * @param index
     * @param num
     */
    private static void heapInsert(int[] heap, int index, int num) {
        heap[index] = num;
        // 上浮
        while (index != 0) {
            int parent = (index-1)/2;
            if (heap[index] <= heap[parent]) {
                swap(heap, index, parent);
                index = parent;
            }else {
                break;
            }
        }
    }


}
