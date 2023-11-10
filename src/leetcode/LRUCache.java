package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2023/11/10 9:15 下午
 * @Discreption <> LRU 缓存
 */
public class LRUCache {


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);
        lruCache.get(6);
        lruCache.get(8);
        lruCache.put(12,1);
        lruCache.get(2);
        lruCache.put(15,11);
        lruCache.put(5,2);
        lruCache.put(1,15);
        lruCache.put(4,2);
        lruCache.get(5);
        lruCache.put(15,15);

    }

    /**
     * key - listnode
     */
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    int capacity = 0;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }else {
            ListNode node = map.get(key);
            // 移动到队尾
            move2Tail(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        ListNode node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = value;
            // 替换到队尾
            move2Tail(node);
        }else {
            node = new ListNode(key, value);
            // 判断是否队列满了，如果满了则将队头干掉，当前节点加到队尾
            if (map.size() >= capacity) {
                // 将队头干掉
                map.remove(head.key);
                head = head.next;
                if (head != null) {
                    head.pre = null;
                }else {
                    tail = null;
                }
            }
            if (head == null && tail == null) {
                head = node;
                tail = node;
            }else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }

            map.put(key, node);
        }
    }


    /**
     * 将cur节点移动到队尾
     * @param cur
     */
    public void move2Tail(ListNode cur) {
        if (cur == tail) {
            return;
        }else if (cur == head){
            head = head.next;
            if (head != null) {
                head.pre = null;
            }else {
                tail = null;
            }
        }else {
            // 取
            ListNode p = cur.pre;
            ListNode n = cur.next;
            p.next = n;
            n.pre = p;
        }
        // 将cur放到tail
        tail.next = cur;
        cur.pre = tail;
        tail = cur;
    }


    class ListNode {
        int key;
        int val;

        ListNode next;

        ListNode pre;

        ListNode() {}

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }




    }
