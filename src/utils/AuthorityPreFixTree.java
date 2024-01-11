package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2024/1/11 11:47 下午
 * @Discreption <> 变种权限前缀树 - 暂时未想好
 */
public class AuthorityPreFixTree<K, V> {


    /**
     * 维护一个根节点
     */
    private Record<K, V> root;

    public AuthorityPreFixTree() {
        this.root = new Record<>(null, new HashMap<>());
    }

    /**
     * 插入某种类型的数组
     * @param arr
     */
    public void insert(K[] arr) {
        Record<K, V> cur = this.root;
        for (int i = 0; i < arr.length; i++) {
            K t = arr[i];
            Map<K, Record<K, V>> children = cur.getChildren();
            if (!children.containsKey(t)) {
                children.put(t, new Record<>(t, new HashMap<>()));
            }
            Record<K, V> tRecord = children.get(t);
            tRecord.setPassNum(tRecord.getPassNum()+1);
            if (i == arr.length-1) {
                // 当前节点是最后一个
                tRecord.setEndNum(tRecord.getEndNum()+1);
            }
            // 下一个
            cur = tRecord;
        }
    }


    /**
     * 判断当前数组是否在里面
     * @param arr
     */
    public boolean contains(K[] arr) {
        Record<K, V> cur = this.root;
        for (int i = 0; i < arr.length; i++) {
            K t = arr[i];
            Map<K, Record<K, V>> children = cur.getChildren();
            // 不存在这个key或者存在但是已经没有值经过了（被删了）
            if (!children.containsKey(t) || children.get(t).getPassNum() == 0) {
                return false;
            }
            // 有经过的话，如果是最后一个值，end也不能为0
            if (i == arr.length - 1) {
                return children.get(t).getEndNum() != 0;
            }
            cur = children.get(t);
        }
        return true;
    }


    /**
     * 删除一个数组
     * @param arr
     */
    public void delete(K[] arr) {
        Record<K, V> cur = this.root;
        if (!contains(arr)){
            // 不存在
            return;
        }
        // 存在的话沿途减少pass，最后一个减少end
        for (int i = 0; i < arr.length; i++) {
            K t = arr[i];
            Map<K, Record<K, V>> children = cur.getChildren();
            Record<K, V> tRecord = children.get(t);
            tRecord.setPassNum(tRecord.getPassNum()-1);
            if (i == arr.length - 1) {
                tRecord.setEndNum(tRecord.getEndNum() - 1);
            }
            cur = tRecord;
        }
    }




    /**
     * 元素记录
     */
    class Record<K, V> {
        /**
         * 经过当前节点的数量
         */
        int passNum;

        /**
         * 以当前节点为结尾的数量
         */
        int endNum;

        /**
         * 当前节点的值
         */
        K key;

        Map<K, Record<K, V>> children;

        public Record(K key, Map<K, Record<K, V>> children) {
            this.passNum = 0;
            this.endNum = 0;
            this.key = key;
            this.children = children;
        }

        public int getPassNum() {
            return passNum;
        }

        public void setPassNum(int passNum) {
            this.passNum = passNum;
        }

        public int getEndNum() {
            return endNum;
        }

        public void setEndNum(int endNum) {
            this.endNum = endNum;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public Map<K, Record<K, V>> getChildren() {
            return children;
        }

        public void setChildren(Map<K, Record<K, V>> children) {
            this.children = children;
        }
    }



}
