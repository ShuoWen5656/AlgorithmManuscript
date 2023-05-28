package classespackage.other;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @data 2023/5/28 19:10
 * @Discreption <> 出现次数TopK问题
 * 循环加入字符串，随时可以统计出当前出现最多的topk字符串
 */
public class TopKRecordCp1 {


    /**
     * 小根堆结构
     */
    private CommonHeapPlus<Record> heap;



    /**
     * 用于判断用户当前输入的是否已经有了
     */
    private Map<String, Record> mapStringRecord;

    /**
     * 用于判断当前记录对象是否在堆中，如果不存在为-1，如果存在则可以直接定位到堆位置
     */
    private Map<Record, Integer> mapRecordInteger;


    public TopKRecordCp1(int k) {
        // 小根
        this.heap = new CommonHeapPlus<>(k, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.getTimes() - o1.getTimes();
            }
        });
        mapStringRecord = new HashMap<>();
        mapRecordInteger = new HashMap<>();
    }


    /**
     * 加入一个str
     * @param str
     */
    public void add(String str) {
        if (str == null) {
            return;
        }
        Record cur = null;
        // 先处理第一个map
        if (mapStringRecord.containsKey(str)) {
            cur = mapStringRecord.get(str);
            cur.setTimes(cur.getTimes()+1);
            if (mapRecordInteger.containsKey(cur) && mapRecordInteger.get(cur) >= 0) {
                // 已经在堆中
                heap.heapify(mapRecordInteger.get(cur));
                return;
            }
            // 不再堆中的逻辑可以和下面一样
        }else {
            cur = new Record(str, 1);
            mapStringRecord.put(str, cur);
        }
        // 到这里说明不管cur是否是老str，都不在堆中，所以现在判断是否应该加入堆
        // 看下堆是否满了
        if (!heap.isFull()) {
            //没有满直接放进去，返回所在堆的index
            heap.heapInsetForTopK(cur, mapRecordInteger);
            // 更新indexmap
            //mapRecordInteger.put(cur, curIndex);
        }else{
            // 满了的话需要判断是否有资格进去
            if (heap.getHead().getTimes() >= cur.getTimes()) {
                // 连最小值都大于当前的times，没资格进堆
                mapRecordInteger.put(cur, -1);
            }else {
                // 有资格进堆
                heap.popHead();
                heap.heapInsetForTopK(cur, mapRecordInteger);
                //int curIndex = heap.heapInsert(cur);
                //mapRecordInteger.put(cur, curIndex);
            }
        }
    }


    /**
     * 打印到目前为止的次数topk
     */
    public void printTopK() {
        while (!heap.isEmpty()) {
            Record record = heap.popHead();
            System.out.println("Str: " + record.getVlaue() + " Times : " + record.getTimes());
        }
    }






    /**
     * 存储两个变量
     * value：用户输入的字符串
     * times：该字符串目前出现的次数
     */
    public class Record{



        private String vlaue;

        private int times;


        public Record(String vlaue, int times) {
            this.vlaue = vlaue;
            this.times = times;
        }

        public String getVlaue() {
            return vlaue;
        }

        public void setVlaue(String vlaue) {
            this.vlaue = vlaue;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }


    public static void main(String[] args) {
        TopKRecordCp1 topKRecordCp1 = new TopKRecordCp1(2);
        topKRecordCp1.add("1");
        topKRecordCp1.add("1");
        topKRecordCp1.add("2");
        topKRecordCp1.add("3");
        topKRecordCp1.printTopK();
    }



}
