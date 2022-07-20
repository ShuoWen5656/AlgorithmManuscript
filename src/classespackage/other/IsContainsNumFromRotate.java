package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/20 19:46
 * @Discreption <>在有序旋转数组中找到一个数
 */
public class IsContainsNumFromRotate {


    /**
     * 判断num是否再array旋转数组中
     * @param arrray
     * @param num
     * @return
     */
    public static boolean isContains(int[] arrray, int num){
        try {
            int low = 0;
            int high = arrray.length-1;
            int mid = 0;
            while (low <= high){
                if (arrray[low] == num){
                    return true;
                }
                mid = (low+high)/2;
                if (num == arrray[mid]){
                    return true;
                }
                // 四种图：
                /**
                 * 1、
                 *       /
                 *      /
                 *        /
                 *
                 */

                /**
                 * 2、
                 *     /
                 *
                 *         /
                 *        /
                 */

                /**
                 * 3、
                 *
                 *
                 *        /
                 * ------/
                 *
                 *
                 */
                /**
                 * 4、
                 * ------
                 *
                 *
                 *       /
                 */
                if (arrray[low] == arrray[mid] && arrray[mid] == arrray[high]){
                    while (arrray[low] == arrray[mid] && low != mid){
                        low++;
                    }
                    if (low == mid){
                        low = mid+1;
                        continue;
                    }
                }
                if (arrray[low] != arrray[mid]){
                    if (arrray[low] < arrray[mid]){
                        // 不相等
                        if (num > arrray[low] && arrray[mid] > num){
                            high = mid-1;
                        }else {
                            low = mid+1;
                        }
                    }else {
                        if (num > arrray[mid] && num < arrray[high]){
                            low = mid+1;
                        }else {
                            high = mid-1;
                        }
                    }
                }else {
                    // low和mid相等
                    if (arrray[mid] < high){
                        if (num <= arrray[high] && num > arrray[mid]){
                            low = mid+1;
                        }else {
                            high = mid-1;
                        }
                    }else {
                        if (num >= arrray[low] && num < arrray[mid]){
                            high = mid-1;
                        }else {
                            low = mid+1;
                        }
                    }
                }
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
