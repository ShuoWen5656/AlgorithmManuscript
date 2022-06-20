package classespackage.arrayAndMartrix;

import classespackage.CommonUtils;

/**
 * @author swzhao
 * @data 2022/6/19 10:03
 * @Discreption <> 在数组中找到一个局部最小的位置
 * 局部最小：
 */
public class GetLessIndex {


    /**
     * 主方法
     * @param array
     * @return
     */
    public static int getLessIndex(int[] array){
        try {
            if (array == null || array.length == 0){
                return 0;
            }
            // 判断左右边是否存在局部最小
            if (array.length == 1){
                return array[0];
            }else if (array[0] < array[1]){
                return array[0];
            }else if (array[array.length-1] < array[array.length-2]){
                return array[array.length - 1];
            }else {
                // 到这里说明局部最小在里面
                // array局部趋势:[\....../]，很显然中间存在\/转折，类似函数零点,使用二分法查找即可
                int left = 1;
                int right = array.length - 2;
                int mid = 0;
                while (left <= right){
                    // 先判断是否left是当前的局部最小
                    if (array[left] < array[left+1]){
                        return left;
                    }else if (array[right] < array[right-1]){
                        return right;
                    }
                    mid = CommonUtils.getMid(left, right);
                    if (array[mid] > array[mid-1]){
                        // [\.../.../],转折在left。。mid-1
                        right = mid-1;
                    }else {
                        // [\...\.../]，转折在mid+1.。。right
                        left = mid+1;
                    }
                }
            }
            // 这里应该到不了
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


}
