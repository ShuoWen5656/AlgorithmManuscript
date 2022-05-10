package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/10 21:28
 * @Discreption <> 在有序但含有空的数组中查找字符串
 */
public class FindStrFromNullCharArray {


    /**
     * 尽量使用二分法查找给定char
     * @param strs
     * @param str
     * @return
     */
    public static int getIndex(String[] strs, String str){
        try {
            if(str == null || strs == null || str.length() == 0){
                return -1;
            }
            // 二分法的三个变量
            int left = 0;
            int right = strs.length - 1;
            int mid = 0;
            // 结果
            int res = -1;
            // 临时变量
            int index = 0;
            while (left <= right){
                // 每一次都需要计算中间点
                mid = (left + right)/2;
                if(str.equals(strs[mid])){
                    // 直接相等了，可能不是最左边第一个出现的,先记录res，继续左边找
                    res = mid;
                    right = mid-1;
                }else if(strs[mid] != null){
                    // 可比
                    if(strs[mid].compareTo(str) < 0){
                        // 中间比str小,说明在右边
                        left = mid + 1;
                    }else{
                        // 中间的大
                        right = mid - 1;
                    }
                }else{
                    // strs == null, 不可比较，这里循环往左边开始遍历，找到一个可比较的数值
                    index = mid;
                    // 找到左边第一个可比较的str
                    while (index >= left && strs[index] == null){
                        index--;
                    }
                    // while结束后可能越界
                    // 如果越界或者可比较的strs[index] < str， 说明需要找右边的数组
                    if(index < left || strs[index].compareTo(str) < 0){
                        left = mid + 1;
                    }else{
                        // 到这里说明答案在左边
                        right = index - 1;
                    }
                }
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


}
