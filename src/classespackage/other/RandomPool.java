package classespackage.other;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/7/9 9:01
 * @Discreption <> 设计RandomPool结构
 * 问题：设计一个数据结构能够存储k值并且能够等概率随机获取其中一个值,时间复杂度O（1）
 */
public class RandomPool<K> {
    /**
     * k- index，表示在当前第几个位置
     */
    Map<K, Integer> kIntegerMap;
    /**
     * index - k, 表示当前index表示哪个值
     */
    Map<Integer, K> integerKMap;
    /**
     * 表示当前的数据大小+1，也就是下一个元素的index
     */
    int size;

    /**
     * 初始化
     */
    public RandomPool() {
        this.kIntegerMap = new HashMap<>();
        this.integerKMap = new HashMap<>();
        this.size = 0;
    }


    /**
     * 写入一个值，两个都要写入，如果已经存在则不作为
     * @param key
     */
    public void insert(K key){
        if (!this.kIntegerMap.containsKey(key)){
            this.kIntegerMap.put(key, size);
            this.integerKMap.put(size++, key);
        }
    }


    /**
     * 随机获取一个值,这里的随机通过随机函数获取一个index返回即可
     * @return
     */
    public K getRandom(){
        int random = (int)(Math.random())*this.size;
        return integerKMap.get(random);
    }

    /**
     * 删除一个元素比较麻烦，需要将当前size-1的位置的值跟需要删除的值的index对换一下，然后删除key即可
     * @param key
     */
    public void delete(K key){
        if (kIntegerMap.containsKey(key)){
            Integer deleteIndex = kIntegerMap.get(key);
            kIntegerMap.put(key, size-1);
            K lastKey = integerKMap.get(size - 1);
            kIntegerMap.put(lastKey, deleteIndex);
            integerKMap.put(deleteIndex, lastKey);
            integerKMap.remove(size-1);
            kIntegerMap.remove(key);
            size--;
        }
    }


}
