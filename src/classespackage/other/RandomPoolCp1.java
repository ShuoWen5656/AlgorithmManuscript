package classespackage.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2023/4/7 19:58
 * @Discreption <> 设计RandomPool结构
 */
public class RandomPoolCp1<K> {

    /**
     * key - index
     */
    private Map<K, Integer> keyIndex;

    /**
     * index - key
     */
    private Map<Integer, K> indexKey;

    /**
     * 维护一个最后的index
     */
    private Integer lastIndex;


    public RandomPoolCp1() {
        this.keyIndex = new HashMap<>(16);
        this.indexKey = new HashMap<>(16);
        this.lastIndex = -1;
    }

    /**
     * 等概率随机返回一个key
     * @return
     */
    public K radomGet() {
        return indexKey.get((int)(Math.random() * lastIndex));
    }


    /**
     * 插入一个元素
     */
    public void insertKey(K key) {
        keyIndex.put(key, ++lastIndex);
        indexKey.put(lastIndex, key);
    }


    /**
     * 删除一个元素
     * 跟最后一个元素交换之后删除掉就行了
     */
    public void deleteKey(K key) {
        if (keyIndex.containsKey(key)) {
            K delteKey = key;
            Integer deleteIndex = keyIndex.get(key);
            // 最后一个元素
            K lastKey = indexKey.get(lastIndex);
            // 将最后一个元素跟待删除的元素进行交换，保持index连续
            keyIndex.put(lastKey, deleteIndex);
            keyIndex.put(delteKey, lastIndex);

            indexKey.put(deleteIndex, lastKey);
            indexKey.put(lastIndex, delteKey);
            // 删除
            indexKey.remove(lastIndex);
            keyIndex.remove(delteKey);
            // 更新lastindex,就减少一个元素就行了
            lastIndex--;
        }
    }


    public static void main(String[] args) {
        RandomPoolCp1<String> randomPoolCp1 = new RandomPoolCp1<>();
        randomPoolCp1.insertKey("k1");
        randomPoolCp1.insertKey("k2");
        randomPoolCp1.insertKey("k3");
        randomPoolCp1.insertKey("k4");

        randomPoolCp1.deleteKey("k3");

        randomPoolCp1.radomGet();
    }

}
