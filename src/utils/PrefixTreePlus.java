package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2024/1/11 10:19 下午
 * @Discreption <> 支持泛型的前缀树
 * 支持某种类型的数组以前缀树的方式存储
 * 注意重写equals和hashcode方法
 */
public class PrefixTreePlus<T> {

    /**
     * 维护一个根节点
     */
    private Record<T> root;

    public PrefixTreePlus() {
        this.root = new Record<>(null, new HashMap<>());
    }

    /**
     * 插入某种类型的数组
     * @param arr
     */
    public void insert(T[] arr) {
        Record<T> cur = this.root;
        for (int i = 0; i < arr.length; i++) {
            T t = arr[i];
            Map<T, Record<T>> children = cur.getChildren();
            if (!children.containsKey(t)) {
                children.put(t, new Record<>(t, new HashMap<>()));
            }
            Record<T> tRecord = children.get(t);
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
    public boolean contains(T[] arr) {
        Record<T> cur = this.root;
        for (int i = 0; i < arr.length; i++) {
            T t = arr[i];
            Map<T, Record<T>> children = cur.getChildren();
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
    public void delete(T[] arr) {
        Record<T> cur = this.root;
        if (!contains(arr)){
            // 不存在
            return;
        }
        // 存在的话沿途减少pass，最后一个减少end
        for (int i = 0; i < arr.length; i++) {
            T t = arr[i];
            Map<T, Record<T>> children = cur.getChildren();
            Record<T> tRecord = children.get(t);
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
    class Record<T> {
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
        T value;

        Map<T, Record<T>> children;

        public Record(T value, Map<T, Record<T>> children) {
            this.passNum = 0;
            this.endNum = 0;
            this.value = value;
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

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Map<T, Record<T>> getChildren() {
            return children;
        }

        public void setChildren(Map<T, Record<T>> children) {
            this.children = children;
        }
    }



}
