package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/10 11:20
 * @Discreption <>路径数组变为统计数组
 */
public class Path2Nums {

    /**
     * 主方法
     * 首先将路径数组转化成距离数组
     * 再将距离数组转化为数量数组
     * @param array
     */
    public static void path2Nums(int[] array){
        if (array == null || array.length == 0){
            return;
        }
        // citiePath -> distanceArray
        toDistanceArray(array);
        // distanceArray -> numsArray
        toNumsArray(array);
    }


    /**
     * 计算每一个位置到首都的距离，用负数表示
     * @param array
     */
    private static void toDistanceArray(int[] array) {
        // 首都位置
        int cap = 0;
        for (int i = 0; i < array.length; i++){
            // 从i开始
            if (array[i] == i){
                // 首都
                cap = i;
                continue;
            }else if (array[i] < 0){
                // 当前位置已经计算过到首都的距离，下一个
                continue;
            }else{
                // 当前位置不是首都并且没有计算过，开始计算当前距离，首先记录为-1，意思是当前距离小于0，为起点标记
                // 先记录下一跳
                int next = array[i];
                // 记录当前位置
                int cur = i;
                // 当前距离为-1
                array[i] = -1;
                // 一直往下找，只要是大于0的并且下一跳不是自己的（首都）就继续往下走，沿途array记录从哪里来的
                while (array[next] >= 0 && array[next] != next){
                    // 记录next的next
                    int nnext = array[next];
                    // 当前位置数据是上一个位置，为了回溯
                    array[next] = cur;
                    // 更新cur和next
                    cur = next;
                    next = nnext;
                }
                int ccur = -1;
                // 到这里说明array[next]找到负数或者首都了
                if (array[next] == next){
                    // 说明next是首都，手动往回走一个
                    //cur为上一个
                    ccur = array[cur];
                    array[cur] = -1;
                    // 坐标往下移动一个
                    next = cur;
                    cur = ccur;
                }
                // 这里开始往回走，一路上将原来的数据更新为到首都的距离,负数也没关系
                while (array[cur] != -1){
                    ccur = array[cur];
                    // 更新cur的数值
                    array[cur] = array[next]-1;
                    next = cur;
                    cur = ccur;
                }
                // 当前cur为出发点
                array[cur] = array[next]-1;
            }
        }
        // 首都到首都的距离为0,这里最后再进行赋值为了区分next为0位置还是距离为0
        array[cap] = 0;
    }


    /**
     * 将数组从距离状态转成数量状态
     * @param array
     */
    private static void toNumsArray(int[] array) {
        for (int i = 0; i < array.length; i ++){
            if (array[i] == 0){
                // 0的情况稍后处理
                continue;
            }else if (array[i] < 0){
                // 说明是距离状态,说明发现一个距离为array[i]的城市
                int dis = Math.abs(array[i]);
                array[i] = 0;
                while (array[dis] < 0){
                    // 只要dis为距离状态，则继续追踪
                    // 记录下一跳
                    int next = Math.abs(array[dis]);
                    // 处理当前跳
                    array[dis] = 1;
                    dis = next;
                }
                // 到这里array[dis]>=0 ， 说明是数量状态,dis距离的+1
                array[dis]++;
            }
        }
        // 结束之后处理0的情况,距离为0的只有首都一个
        array[0] = 1;
    }





}
