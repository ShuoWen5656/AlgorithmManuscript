package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/17 11:31
 * @Discreption <> 分糖果问题
 */
public class DistributeCandy {

    /**
     * 主方法
     * @param array
     */
    public static int distributeCandy1(int[] array){
        if (array == null || array.length == 0){
            return 0;
        }
        // 首先走了一个下坡模式，找到局部低点，算出需要的糖果数量
        int index = nextMinIndex(array, 0);
        int res = rightCands(array, 0, index++);
        int lbase = 1;
        int rbase = 0;
        int rcands = 0;
        int next = 0;
        while (index != array.length){
            if (array[index] > array[index-1]){
                // 递增顺序，，总结果增加一个lbase，lbase增加,继续往下
                res += ++lbase;
                index++;
            }else if (array[index] < array[index-1]){
                // 转换为下坡模式
                // 从index-1开始下坡模式，找到下一个局部低点
                next = nextMinIndex(array, index-1);
                // 计算这波下坡需要多少糖果
                rcands = rightCands(array, index-1, next++);
                // 这波下坡的坡度，123和321的坡度都是3,
                rbase = next-index+1;
                // 上坡123,下坡4321，则中间的数应该从4算起，总124321,这个时候res已经是123了，所以加上rcands 4321后需要减掉一个lbase，
                // 否则按照左边的坡度计算的话，需要将右边的大坡度减掉，也就是rbase
                res += rcands + (rbase < lbase ? -rbase : -lbase);
                // 进行下一轮计算
                index = next;
                lbase = 1;
            }else {
                // 相等的情况，这种情况比较特殊，相邻的分数相同的话，为了降低糖果总数,右边可以变成1从头开始
                lbase = 1;
                res+=lbase;
                index++;
            }
        }
        return res;
    }


    /**
     * 下一个局部较小的index, 第一个左右边都比自己大的值
     * @param arr
     * @param start
     * @return
     */
    public static int nextMinIndex(int[] arr, int start){
        for (int i = start; i != arr.length; i++){
            if (arr[i] <= arr[i+1]){
                return i;
            }
        }
        return arr.length-1;
    }


    /**
     * 需要分配的糖果数量
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int rightCands(int[] arr, int left, int right){
        int n = left-right+1;
        return n + n*(n-1)/2;
    }


    /**
     * 分糖果第二种方式
     * @param array
     * @return
     */
    public static int distributeCandy2(int[] array){
        if (array == null || array.length == 0){
            return 0;
        }
        // \___./ 中的.的位置
        int index = getMinIndex2(array, 0);
        // 获取上坡所需糖果数量和右边的坡度
        int[] candsAndBase = rightCandsAndBase(array, 0, index++);
        int lbase = 0;
        // 标注上坡中有几个相等的
        int same = 0;
        int next = 0;
        // 先获取第一个下坡所需糖果数量
        int res = candsAndBase[1];
        while (index < array.length){
            if (array[index] < array[index+1]){
                // 正常上坡
                res += ++lbase;
                // /---/---/这种情况需要same置为1
                same = 1;
                index++;
            }else if (array[index] > array[index+1]){
                // 转下坡
                next = getMinIndex2(array, index);
                int[] data = rightCandsAndBase(array, index-1, next++);
                if (data[1] < lbase){
                    // 左边坡度大，按照左边来,右边把自己的rbase减掉
                    res += data[0] - data[1];
                }else {
                    // 按照右边来，需要左边把自己相同的减掉， 即 （左边减掉same个lbase） + 右边减掉一个rbase + same个rbase
                    res += (res-same * lbase) + data[0]-data[1] + same * data[1];
                }
                index = next;
                same = 1;
                lbase = 1;
            }else {
                // 相等情况./---\这种中间的---就需要解决
                same++;
                // 这里lbase不递增
                res += lbase;
                index++;
            }
        }
        return res;
    }


    /**
     * 获取最小index2
     *  tips： 这里遇到第一个比自己大的右元素就结束，相等的不返回
     * @return
     */
    public static int getMinIndex2(int[] arr, int start){
        for (int i = start; i < arr.length; i++){
            if (arr[i] < arr[i+1]){
                return i;
            }
        }
        return arr.length-1;
    }

    /**
     * 获取所需要的糖果和base数量
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int[] rightCandsAndBase(int[] arr, int left, int right){
        int base = 1;
        int cands = 1;
        for (int i = right-1; i >= left; i--){
            if (arr[i] == arr[i+1]){
                cands+=base;
            }else {
                cands+= ++base;
            }
        }
        return new int[]{cands, base};
    }


    /**
     * 二轮测试：分糖果问题（原问题）
     * 1、首先分爬坡
     * 2、左右坡度合一
     * @param arr
     * @return
     */
    public static int distributeCandyCp1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }else if (arr.length == 1){
            return 1;
        }
        int len = arr.length;
        // 找到下一个爬坡的起点
        int index = getNextStartCp1(arr, 0);
        int res = getRightCandy(0, index++);
        // 左边的糖果数量最大值
        int lbase = 1;
        // 右边糖果数量最大值
        int rbase = 0;
        while (index < len) {
            if (arr[index] > arr[index - 1]) {
                // 上坡过程
                res += ++lbase;
                index ++;
            }else if (arr[index] < arr[index-1]) {
                // 下坡过程,直接找到下一个上坡点
                int next = getNextStartCp1(arr, index-1);
                // 右边需要的糖果
                int rcand = getRightCandy(index - 1, next);
                rbase = next - index + 1;
                res += rcand - (lbase > rbase ? rbase : lbase);
                lbase = 1;
                index = next+1;
            }else {
                // 相等时相当于下一坡
                res += 1;
                lbase = 1;
                index ++;
            }
        }
        return res;
    }

    /**
     * 计算下坡需要的糖果数量[...3,2,1]
     * 等差数列求和公式
     * @param left
     * @param right
     * @return
     */
    private static int getRightCandy(int left, int right) {
        int len = right - left + 1;
        return  (len * (len + 1))/2;
    }

    /**
     * 找到下一个爬坡起点
     * @param arr
     * @param start
     * @return
     */
    private static int getNextStartCp1(int[] arr, int start) {
        for (int i = start; i < arr.length; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length-1;
    }


    /**
     * 二轮测试：分糖果问题（进阶问题）
     * 1、首先分爬坡
     * 2、左右坡度合一
     * @param arr
     * @return
     */
    public static int distributeCandyCp2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }else if (arr.length == 1){
            return 1;
        }
        int len = arr.length;
        // 找到下一个爬坡的起点
        int index = getNextStartCp2(arr, 0);
        int[] data = getRightCandyCp2(arr, 0, index++);
        int res = data[0];
        // 左边的糖果数量最大值
        int lbase = 1;
        // 右边糖果数量最大值
        int rbase = data[1];
        // 计算有多少个相同的
        int same = 1;
        while (index < len) {
            if (arr[index] > arr[index - 1]) {
                // 上坡过程
                res += ++lbase;
                same = 1;
                index ++;
            }else if (arr[index] == arr[index - 1]) {
                res += lbase;
                same++;
                index ++;
            } else if (arr[index] < arr[index-1]) {
                // 下坡过程,直接找到下一个上坡点
                int next = getNextStartCp2(arr, index-1);
                // 右边需要的糖果
                int[] resAndRcand = getRightCandyCp2(arr, index - 1, next);
                int rcand = resAndRcand[0];
                rbase = resAndRcand[1];
                if (rbase < lbase) {
                    res += rcand - rbase;
                }else {
                    res += rcand - same * lbase - rbase + same * rbase;
                }
                lbase = 1;
                index = next+1;
            }
        }
        return res;
    }

    /**
     * 计算下坡需要的糖果数量[...3,3,2,2,1]
     * 等差数列求和公式
     * @param left
     * @param right
     * @return
     */
    private static int[] getRightCandyCp2(int[] arr, int left, int right) {
        int base = 1;
        int res = 0;
        for (int i = right; i >= left; i--) {
            if (i == right || arr[i] == arr[i + 1]) {
                res += base;
            }else {
                res += ++base;
            }
        }
        return new int[]{res, base};
    }

    /**
     * 找到下一个爬坡起点
     * @param arr
     * @param start
     * @return
     */
    private static int getNextStartCp2(int[] arr, int start) {
        for (int i = start; i < arr.length-1; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length-1;
    }



    public static void main(String[] args) {
        System.out.println(distributeCandyCp2(new int[]{0,1,2,3,3,3,2,2,2,2,2,1,1}));
    }





}
