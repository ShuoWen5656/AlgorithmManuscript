package leetcode;

import com.sun.javafx.tools.ant.CSSToBinTask;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author swzhao
 * @date 2023/12/1 7:09 下午
 * @Discreption <>查找和最小的 K 对数字
 */
public class KSmallestPairs {


    public static void main(String[] args) {
        System.out.println(solution(new int[]{0,0,0,0,0}, new int[]{-3,22,35,56,76}, 22));
    }


    public static List<List<Integer>> solution(int[] nums1, int[] nums2, int k) {
        if (k == 0) {
            return new ArrayList<>();
        }
        boolean[][] map = new boolean[nums1.length][nums2.length];
        int count  = 0;
        List<List<Integer>> res = new ArrayList<>();
        // 最大也就弹出k个元素，进入k*2个元素，所以这里只用2*k即可
//        HeapNode[] heapNodes = new HeapNode[k + k/2];
        int[][] heapNodes = new int[k * 2][2];
        // 默认初始位置最小
        heapNodes[0][0] = 0;
        heapNodes[0][1] = 0;
        map[0][0] = true;
        int heapIndex = 1;
        while (count < k) {
            if (heapIndex == 0) {
                return res;
            }
            // 队头弹出
            int[] curNode = heapPop(heapNodes, heapIndex - 1, nums1, nums2);
            heapIndex--;
            // 加入结果
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(nums1[curNode[0]]);
            integers.add(nums2[curNode[1]]);
            res.add(integers);

            // 将候选者加入
            if (curNode[0] + 1 < nums1.length && !map[curNode[0]+1][curNode[1]]){
                map[curNode[0]+1][curNode[1]] = true;
                heapInsert(heapNodes, curNode[0] + 1, curNode[1], heapIndex++, nums1, nums2);
            }
            if (curNode[1] + 1 < nums2.length && !map[curNode[0]][curNode[1] + 1]) {
                map[curNode[0]][curNode[1] + 1] = true;
                heapInsert(heapNodes, curNode[0], curNode[1]+1, heapIndex++, nums1, nums2);
            }
            count++;
        }
        return res;
    }

    private static void heapInsert(int[][] heapNodes, int row, int col, int index, int[] nums1, int[] nums2) {
        heapNodes[index] = new int[]{row, col};
        while (index != 0) {
            int parent = (index - 1)/2;
            if (getSum(heapNodes[parent], nums1, nums2) >= getSum(heapNodes[index], nums1, nums2)) {
                swap(heapNodes, parent, index);
                index = parent;
            }else {
                break;
            }
        }
    }

    private static int[] heapPop(int[][] heapNodes, int index, int[] nums1, int[] nums2) {
        int[] head = heapNodes[0];
        swap(heapNodes, 0, index);
        int parent = 0;
        int left = 2*parent+1;
        while (left < index) {
            int minIndex = (getSum(heapNodes[parent], nums1, nums2) < getSum(heapNodes[left], nums1, nums2)) ?
                    parent : left;
            if (left + 1 < index && getSum(heapNodes[left + 1], nums1, nums2) < getSum(heapNodes[minIndex], nums1, nums2)) {
                minIndex = left + 1;
            }
            if (minIndex == parent) {
                break;
            }
            swap(heapNodes, minIndex, parent);
            parent=  minIndex;
            left = parent * 2+1;
        }
        return head;
    }

    private static int getSum(int[] heapNode, int[] nums1, int[] nums2) {
        return nums1[heapNode[0]] + nums2[heapNode[1]];
    }

    private static void swap(int[][] heapNodes, int index1, int index2) {
        int[] heapNode = heapNodes[index1];
        heapNodes[index1] = heapNodes[index2];
        heapNodes[index2] = heapNode;
    }


    static class HeapNode {
        int row;
        int col;

        public HeapNode(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }





    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 小的在队头
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        // 先加入一边的头，只加入最短的长度
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i,0});
        }
        // k没完或者队列还不是空的
        while (k-- > 0 && !pq.isEmpty()) {
            // 弹出的永远是最小的
            int[] idxPair = pq.poll();
            // 将最小的加入
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            // 最小节点的一边已经被加入，所以只判断另一边即可
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }


    /**
     * 自己实现一边
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static List<List<Integer>> solution2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> helper = new PriorityQueue<>(k, ((o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        }));
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            helper.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !helper.isEmpty()) {
            int[] cur = helper.poll();

            int index1 = cur[0];
            int index2 = cur[1];
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(nums1[index1]);
            integers.add(nums2[index2]);
            res.add(integers);

            if (index2 + 1 < nums2.length) {
                helper.offer(new int[]{index1, index2 + 1});
            }
        }
        return res;
    }


}
