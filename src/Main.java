

import dataConstruct.LinkNode;

import java.util.*;

/**
 * 存父节点和本值，角标要不要？？
 */
//class Node{
//    // 父节点
//    Node parent;
//    // 本值
//    Integer num;
//
//    public Node(Node parent, Integer num) {
//        this.parent = parent;
//        this.num = num;
//    }
//
//    public Node getParent() {
//        return parent;
//    }
//
//    public void setParent(Node parent) {
//        this.parent = parent;
//    }
//
//    public Integer getNum() {
//        return num;
//    }
//
//    public void setNum(Integer num) {
//        this.num = num;
//    }
//}


//class Time{
//    String hour;
//    String min;
//    String sec;
//
//    public Time(String hour, String min, String sec) {
//        this.hour = hour;
//        this.min = min;
//        this.sec = sec;
//    }
//
//    public String getHour() {
//        return hour;
//    }
//
//    public void setHour(String hour) {
//        this.hour = hour;
//    }
//
//    public String getMin() {
//        return min;
//    }
//
//    public void setMin(String min) {
//        this.min = min;
//    }
//
//    public String getSec() {
//        return sec;
//    }
//
//    public void setSec(String sec) {
//        this.sec = sec;
//    }
//}
public class Main {


    public static void test1(){
        try {
            throw new Exception();
        }catch (Exception e){
            e.printStackTrace(System.err);
            return;
        }finally {
            System.out.println("finally");
        }
// 有return 编译不通过
//        System.out.println("after finally");
    }



    /**
     * 华为od面试
     * @param args
     */
    public static void main(String[] args) {
        try{
            test1();
//            LinkedList<Object> objects = new LinkedList<>();






//            Scanner input = new Scanner(System.in);
//            String arrayStr = input.nextLine();
//            String[] arrayS = arrayStr.split(" ");
//            Integer[] array = new Integer[arrayS.length];
//            for (int i = 0; i < arrayS.length; i++){
//                array[i] = Integer.valueOf(arrayS[i]);
//            }
//            List<Node> nodeList = new ArrayList<>();
//            Integer minNum = 1000000;
//            Node minNode = null;
//            for (int i = 0; i < array.length; i++){
//                Integer num = array[i];
//                Integer parentIndex = 0;
//                if(i == 0){
//                    Node node = new Node(null, num);
//                    // 跟节点
//                    nodeList.add(node);
//                    // 第一个节点默认最小先
//                    // 根节点也要是叶子节点才能复制
//                    if(array.length == 1){
//                        // 自己是叶子节点才能副
//                        minNum = num;
//                        minNode = node;
//                    }
//                    continue;
//                }
//                if(i == 2){
//                    // 0和2特殊处理
//                    Node node = new Node(nodeList.get(0), num);
//                    nodeList.add(node);
//                    // 必须是叶子节点，
//                    if((6 > array.length || array[5] == -1)
//                            && (7 > array.length || array[6] == -1)){
//                        if(num != -1 && num < minNum){
//                            minNum = num;
//                            minNode = node;
//                        }
//                    }
//                    continue;
//                }
//                if(i%2 == 0){
//                    //偶数，直接除以2就是父节点
//                    parentIndex = i/2-1;
//                    Node node = new Node(nodeList.get(parentIndex), num);
//                    nodeList.add(node);
//                    if((i * 2 + 2 > array.length || array[i*2+1] == -1)
//                            && (i * 2 + 3 > array.length || array[i * 2 + 2] == -1)){
//                        if(num != -1 && num < minNum){
//                            minNum = num;
//                            minNode = node;
//                        }
//                    }
//                }else{
//                    // 基数-1 再/2
//                    parentIndex = (i - 1)/2;
//                    Node node = new Node(nodeList.get(parentIndex), num);
//                    nodeList.add(node);
//                    if((i * 2 +2 > array.length || array[i*2+1] == -1)
//                            && (i * 2 + 3 > array.length || array[i * 2 + 2] == -1)){
//                        if(num != -1 && num < minNum){
//                            minNum = num;
//                            minNode = node;
//                        }
//                    }
//                }
//            }
//            Stack<Integer> stack = new Stack<>();
//            //输出从最小节点开始
//            while (Objects.nonNull(minNode.getParent())){
//                stack.push(minNode.getNum());
//                minNode = minNode.getParent();
//            }
//            // 还差个跟节点
//            stack.push(minNode.getNum());
//            while (!stack.isEmpty()){
//                System.out.print(stack.pop());
//                if(!stack.isEmpty()){
//                    System.out.print(" ");
//                }
//            }
//            List<Double> doubles = new ArrayList<>();
//            doubles.add(1.1);
//            doubles.add(1.2);
//            Collections.sort(doubles, new Comparator<Double>() {
//                @Override
//                public int compare(Double o1, Double o2) {
//                    if(o1 > o2){
//                        return -1;
//                    }else{
//                        return 0;
//                    }
//                }
//            });
//            System.out.println(doubles.toString());


//            Scanner input = new Scanner(System.in);
//            List<Time> timeList = new ArrayList<>();
//            Map<Double, Map<Double, List<Time>>> timeMap = new LinkedHashMap<>();
//            Map<Double, List<Time>> minMap = new LinkedHashMap<>();
//            Double logNum = Double.valueOf(input.nextLine());
//            for (int i = 0; i < logNum ; i++){
//                String time = input.nextLine();
//                String[] times = time.split(":");
//                Time time1 = new Time(times[0], times[1], times[2]);
//                timeList.add(time1);
//            }
//            // 按小时
//            Collections.sort(timeList, new Comparator<Time>() {
//                @Override
//                public int compare(Time o1, Time o2) {
//                    if(Double.valueOf(o1.getHour()) < Double.valueOf(o2.getHour())){
//                        return -1;
//                    }else {
//                        return 0;
//                    }
//                }
//            });
//            // 相同小时的放一起
//            for (Time time : timeList){
//                Double d = Double.valueOf(time.getHour());
//                if(Objects.nonNull(minMap.get(d))){
//                    List<Time> timeList1 = minMap.get(d);
//                    timeList1.add(time);
//                }else{
//                    minMap.put(d, new ArrayList<>(Arrays.asList(time)));
//                }
//            }
//            // 每一个小时的按照分钟排列
//            for (Double hour : minMap.keySet()){
//                List<Time> timeList1 = minMap.get(hour);
//                Collections.sort(timeList1, new Comparator<Time>() {
//                    @Override
//                    public int compare(Time o1, Time o2) {
//                        if(Double.valueOf(o1.getMin()) < Double.valueOf(o2.getMin())){
//                            return -1;
//                        }else {
//                            return 0;
//                        }
//                    }
//                });
//                Map<Double, List<Time>> eqMinMap = new LinkedHashMap<>();
//                // 同分钟的放一起
//                for (Time t : timeList1){
//                    Double min = Double.valueOf(t.getMin());
//                    if(Objects.isNull(eqMinMap.get(min))){
//                        // 小时没有
//                        eqMinMap.put(min, new ArrayList<>(Arrays.asList(t)));
//                    }else{
//                        eqMinMap.get(min).add(t);
//                    }
//                }
//                for (Double min : eqMinMap.keySet()){
//                    // 遍历秒排序
//                    List<Time> timeList2 = eqMinMap.get(min);
//                    Collections.sort(timeList2, new Comparator<Time>() {
//                        @Override
//                        public int compare(Time o1, Time o2) {
//                            if(Double.valueOf(o1.getSec()) < Double.valueOf(o2.getSec())){
//                                return -1;
//                            }else {
//                                return 0;
//                            }
//                        }
//                    });
//                }
//                if(Objects.isNull(timeMap.get(hour))){
//                    timeMap.put(hour, eqMinMap);
//                }
//            }
//            //输出
//            for (Double key : timeMap.keySet()){
//                Map<Double, List<Time>> doubleListMap = timeMap.get(key);
//                for (Double k2 : doubleListMap.keySet()){
//                    List<Time> timeList1 = doubleListMap.get(k2);
//                    for (Time time : timeList1){
//                        System.out.println(time.getHour() + ":" + time.getMin() + ":" + time.getSec());
//                    }
//                }
//            }




        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 朝厚科技：两个有序队列，判断是否存在两个数字相加 == target
     * @param array1
     * @param array2
     * @param target
     * @return
     */
    public static boolean solution(int[] array1, int[] array2, int target){
        try {
            int index1 = array1.length - 1;
            int index2 = 0;
            while (index1 > 0 && index2 < array2.length){
                if(array1[index1] + array2[index2] > target){
                    index1--;
                }else if(array1[index1] + array2[index2] < target){
                    index2++;
                }else{
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
//    public static void main(String[] args) {
//
//
//        try{
////            List<Integer> integers = Arrays.asList(2, 1, 5, 3, 9, 0);
//
//
//            /**
//             * MyStack1测试用例
//             */
////        MyStack1 myStack1 = new MyStack1();
////        myStack1.push(2);
////        myStack1.push(1);
////        myStack1.push(5);
////        myStack1.push(3);
////        System.out.println(myStack1.toString());
////        System.out.println(myStack1.getmin());
//
////        Class clazz = MyStack1.class;
//
//            /**
//             * MyStack2 测试用例
//             */
////            MyStack2 myStack2 = new MyStack2();
////            for (Integer integer : integers){
////                myStack2.push(integer);
////            }
////            System.out.println(myStack2.toString());
////            System.out.println(myStack2.getMin());
//
//
//            /**
//             * 汉诺塔测试用例
//             */
////            HannioTower hannioTower = new HannioTower();
////            int step = hannioTower.hanioProblem(100, "left", "mid", "right", "left", "right");
////            System.out.println("步数:" + step);
//
//            /**
//             * 窗口最大值测试用例
//             */
////            int[] array = {4, 3, 5, 4, 3, 3, 6, 7};
////            int[] maxArray = MaxWindow.getMaxWindow(array, 3);
////            System.out.println(Arrays.toString(maxArray));
//
//            Scanner input = new Scanner(System.in);
//            String row = input.nextLine();
//            String col = input.nextLine();
//            String[][] array = new String[Integer.valueOf(row)][Integer.valueOf(col)];
//            for (int i = 0 ; i < Integer.valueOf(row); i++){
//                String tem = input.nextLine();
//                String[] s = tem.split(" ");
//                for (int j = 0; j < s.length; j++){
//                    array[i][j] = s[j];
//                }
//            }
//
//
//
////            for (int i = 0 ; i < Integer.valueOf(row); i++){
////                for (int j = 0; j < Integer.valueOf(col); j++){
////                    System.out.print(array[i][j] + " ");
////                }
////                System.out.print("\n");
////            }
//
//
//
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//
//    }
}
