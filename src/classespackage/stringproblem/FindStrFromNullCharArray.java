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


    /**
     * 二轮测试： 查找字符串
     * @param strs
     */
    public static Integer findCp1(String[] strs, String target) {
        if (strs == null || target == null || strs.length == 0){
            return -1;
        }
        int left = 0;
        int right = strs.length-1;
        while (strs[right] == null){
            right--;
        }
        while (strs[left] == null) {
            left++;
        }
        while (left < right) {
            int mid = (left + right) /2+1;

            if (strs[mid] == null) {
                // null值的两个边界
                int leftNotNull = mid;
                int rightNotNull = mid;
                int tem = mid + 1;
                while (strs[tem] == null) {
                    tem++;
                }
                rightNotNull = tem;
                tem = mid;
                while (strs[tem] == null) {
                    tem--;
                }
                leftNotNull = tem;
                // 处理相等情况
                if (target.equals(strs[rightNotNull])) {
                    left = rightNotNull;
                    break;
                }
                if (target.equals(strs[leftNotNull])) {
                    left = leftNotNull;
                    break;
                }
                // 处理不存在情况
                if (target.compareTo(strs[rightNotNull]) < 0 && target.compareTo(strs[leftNotNull]) > 0) {
                    return -1;
                } else if (target.compareTo(strs[rightNotNull]) > 0) {
                    left = rightNotNull;
                }else if (target.compareTo(strs[leftNotNull]) < 0){
                    right = leftNotNull;
                }
            }else {
                // 处理相等情况
                if (target.equals(strs[mid])) {
                    left = mid;
                    break;
                }else if (target.compareTo(strs[mid]) < 0) {
                    String cur = strs[mid];
                    while (mid >= left && (strs[mid] == null || cur.equals(strs[mid]))) {
                        mid--;
                    }
                    right = mid;
                }else {
                    String cur = strs[mid];
                    while (mid <= right && (strs[mid] == null || cur.equals(strs[mid]))) {
                        mid++;
                    }
                    left = mid;
                }
            }
        }
        if (target.equals(strs[left])) {
            int i = left;
            while (i >= 0 && (target.equals(strs[i]) || strs[i] == null)) {
                if (target.equals(strs[i])) {
                    left = i;
                }
                i--;
            }
            return left;
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(findCp1(new String[]{null, null, "a",null, null, "b", null, "c"}, "a"));


    }


}
