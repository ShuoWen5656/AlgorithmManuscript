package classespackage.other;

import classespackage.stackAndQueue.catDogQueue.Pet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2023/4/5 9:51
 * @Discreption <>设计可以变更的缓存结构
 */
public class LRUCacheCp1<K, V> {

    /**
     * 双向链表数据结构
     */
    private LRULinkedNodes<K,V> linkedNodes;


    /**
     * 存储核心数据结构
     */
    private Map<K, Node<K,V>> map;


    /**
     * 初始化cache大小
     */
    private int size;
    
    /**
     * 定义cache大小
     * @param size
     */
    public LRUCacheCp1(int size) {
        this.size = size;
        linkedNodes = new LRULinkedNodes<>();
        map = new HashMap<>(size);
    }


    /**
     * 放入值
     * @param key
     * @param value
     */
    public void set(K key, V value) {
        if (map.containsKey(key)) {
            Node<K,V> vNode = map.get(key);
            // 已经存在这个值了，直接更新value即可
            vNode.setValue(value);
            // 更新队列
            linkedNodes.move2Tail(vNode);
        }else {
            // 全新的值
            Node<K,V> vNode = new Node<>(value, key,null, null);
            // map先放入
            map.put(key, vNode);
            // linkednode也放入
            linkedNodes.addNode(vNode);
            // 超过限制了：队列将最近最少使用的返回并删除
            if (map.size() > size) {
                Node<K, V> remove = linkedNodes.remove(linkedNodes.getHead());
                map.remove(remove.getKey());
            }
        }
    }

    /**
     * 获取某个值
     * @param key
     * @return
     */
    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node<K,V> vNode = map.get(key);
        // 更新缓存队列：已经存在的值直接放入tial即可
        linkedNodes.move2Tail(vNode);
        return vNode.getValue();
    }
    
    
    
    /**
     * LRU专用的双向链表结构
     * @param <K,V>
     */
    private class LRULinkedNodes<K,V> {

        /**
         * 头
         */
        private Node<K,V> head;
        /**
         * 尾
         */
        private Node<K,V> tail;

        /**
         * 链表长度
         */
        private int size;
        
        
        public LRULinkedNodes() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        /**
         * 添加一个元素到尾结点
         * @param node
         */
        public void addNode(Node<K,V> node) {
            if (node == null) {
                return;
            }
            if (this.size == 0) {
                this.head = node;
                this.tail = node;
            }else {
                this.tail.setNext(node);
                node.setPre(this.tail);
                this.tail = node;
            }
            this.size++;
        }

        /**
         * 将已有元素移动到tail
         * @param node
         */
        public void move2Tail(Node<K,V> node) {
            if (node == null) {
                return;
            }
            // 获取前后值
            if (node  == this.tail) {
                // 已经是尾节点
                return;
            }
            // 先移除，再加入尾结点
            Node<K,V> vNode = remove(node);
            addNode(vNode);
            return;
            
        }

        /**
         * 移除某个节点并返回
         */
        private Node<K,V> remove(Node<K,V> node) {
            if (node == null) {
                return null;
            }
            // 判断是否头尾
            if (this.head == node) {
                this.head = node.getNext();
            }else if (this.tail == node) {
                this.tail = node.getPre();
            }else {
                // 普通删除
                Node<K,V> pre = node.getPre();
                Node<K,V> next = node.getNext();
                pre.setNext(next);
                next.setPre(pre);
            }
            // 清空pre和next
            node.setPre(null);
            node.setNext(null);
            this.size--;
            return node;
        }

        public Node<K, V> getHead() {
            return head;
        }

        public void setHead(Node<K, V> head) {
            this.head = head;
        }

        public Node<K, V> getTail() {
            return tail;
        }

        public void setTail(Node<K, V> tail) {
            this.tail = tail;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
    

    /**
     * 存值用得node类
     * @param <K,V>
     */
    private class Node<K,V> {

        /**
         * 存一份key,node不会存在更新key和value的情况，所以可以安全使用key
         */
        private K key;
        /**
         * 当前节点值
         */
        private V value;

        /**
         * 前一个节点
         */
        private Node<K,V> pre;

        /**
         * 下一个节点
         */
        private Node<K,V> next;

        
        public Node(V value, K key, Node<K,V> pre, Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }


        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K,V> getPre() {
            return pre;
        }

        public void setPre(Node<K,V> pre) {
            this.pre = pre;
        }

        public Node<K,V> getNext() {
            return next;
        }

        public void setNext(Node<K,V> next) {
            this.next = next;
        }
    }


    public static void main(String[] args) {
        LRUCacheCp1<String, Integer> lruCacheCp1 = new LRUCacheCp1<>(3);
        lruCacheCp1.set("1", 11);
        lruCacheCp1.set("2", 22);
        lruCacheCp1.set("2", 221);
        lruCacheCp1.set("3", 33);
        lruCacheCp1.get("2");
        lruCacheCp1.set("4", 44);
        lruCacheCp1.set("5", 55);

    }
}
