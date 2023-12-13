package classespackage.other;

import dataConstruct.DoubleNodePlus;
import dataConstruct.NodeDoubleLinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/7/8 20:20
 * @Discreption <> 设计可以变更的缓存结构
 * 问题：设计一个<K,V>的键值对缓存结构，能够存取，并且初始化设置缓存大小，整个过程体现LRU算法
 */
public class LRUCache<K, V> {

    /**
     * k-v map,这里V统一封装到双向链表节点中
     */
    private Map<K, DoubleNodePlus<V>> kDoubleNodePlusMap;
    /**
     * v-k
     */
    private Map<DoubleNodePlus<V>, K> doubleNodePlusKMap;

    /**
     * 双向链表链结构，tail表示最近使用的节点，head表示最近最少使用的节点
     */
    private NodeDoubleLinkedList<V> nodeDoubleLinkedList;

    private int capasity;


    /**
     * 初始化只接受缓存大小，其他的默认初始化
     * @param capasity
     */
    public LRUCache(int capasity) {
        this.kDoubleNodePlusMap = new HashMap<>();
        this.doubleNodePlusKMap = new HashMap<>();
        this.nodeDoubleLinkedList = new NodeDoubleLinkedList<>();
        this.capasity = capasity;
    }

    /**
     * 获取某个值
     * @param key
     * @return
     */
    public V get(K key){
        if (this.kDoubleNodePlusMap.containsKey(key)){
            // 有则缓存更新，并将node返回
            DoubleNodePlus<V> vDoubleNodePlus = this.kDoubleNodePlusMap.get(key);
            this.nodeDoubleLinkedList.moveToTail(vDoubleNodePlus);
            return vDoubleNodePlus.getValue();
        }
        return null;
    }

    /**
     * 放入元素
     * @param key
     * @param value
     */
    public void set(K key, V value){
        if (this.kDoubleNodePlusMap.containsKey(key)){
            // 已经有了,只需要改个值就可以
            DoubleNodePlus<V> vDoubleNodePlus = this.kDoubleNodePlusMap.get(key);
            vDoubleNodePlus.setValue(value);
            // 放到最近使用的队尾
            this.nodeDoubleLinkedList.moveToTail(vDoubleNodePlus);
        }else {
            // 新节点
            DoubleNodePlus<V> newNode = new DoubleNodePlus<>(value);
            // 放进map
            this.kDoubleNodePlusMap.put(key, newNode);
            this.doubleNodePlusKMap.put(newNode, key);
            // 缓存操作
            this.nodeDoubleLinkedList.add(newNode);
            if (this.kDoubleNodePlusMap.size() > capasity){
                // 超出缓存了
                this.removeMostUnUsedCache();
            }
        }
    }

    /**
     * 移除最近最少使用的节点（头结点）
     */
    private void removeMostUnUsedCache() {
        DoubleNodePlus<V> vDoubleNodePlus = nodeDoubleLinkedList.removeHead();
        K key = doubleNodePlusKMap.get(vDoubleNodePlus);
        this.kDoubleNodePlusMap.remove(key);
        this.doubleNodePlusKMap.remove(vDoubleNodePlus);
    }

}
