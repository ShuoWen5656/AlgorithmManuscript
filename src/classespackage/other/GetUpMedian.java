package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/25 21:22
 * @Discreption <> 在两个长度相等的排序数组中找到上中位数
 */
public class GetUpMedian {


    /**
     * 主方法
     * 原理：
     * 判断中点大小，如果mid1=mid2，则合成总数组后一定是两边数量相等的数组
     * 如果mid1 < mid2 则 xxxx(m1)xxxxxxxx(m2)xxxx这种，则中位数一定是在m1和m2中间，剔除掉两边的xxxx,中间继续计算中位数
     * mid2>mid1同理
     * @param arr1
     * @param arr2
     * @return
     */
    public static int getUpMedian(int[] arr1, int[] arr2){
        try {
            if (arr1 == null || arr2 == null || arr1.length != arr2.length){
                throw new RuntimeException("非法array");
            }
            int start1 = 0;
            int start2 = 0;
            int end1 = arr1.length-1;
            int end2 = arr2.length-1;
            int mid1 = 0;
            int mid2 = 0;
            int offset = 0;
            while (start1 < end1){
                mid1 = (start1+end1)/2;
                mid2 = (start2+end2)/2;
                // 如果偶数则offset为1，基数为0
                offset = ((end1-start1+1)&1)^1;
                if (mid1 == mid2){
                    return arr1[mid1];
                }else if (mid1 > mid2){
                    end1 = mid1;
                    start2 = mid2+offset;
                }else {
                    start1 = mid1+offset;
                    start2 = mid2;
                }
            }
            // 当两个数组的start1都等于end1时，则选择较小的那个为上中位数
            return Math.min(arr1[start1], arr1[start1]);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }



    /**
     * 二轮测试：求相同长度的排序数组的上中位数
     * @param arr1
     * @param arr2
     * @return
     */
    public static int getUpMedianCp1(int[] arr1, int[] arr2){
        if (arr1 == null || arr2 == null || arr1.length != arr2.length){
            throw new RuntimeException("非法array");
        }
        int left1 = 0;
        int left2 = 0;
        int right1 = arr1.length-1;
        int right2 = arr2.length-1;
        while (left1 < right1) {
            // 求终点
            int mid1 = (left1 + right1)/2;
            int mid2 = (left2 + right2)/2;
            if (arr1[mid1] == arr2[mid2]) {
                return arr1[mid1];
            }else {
                // 判断当前数组长度是奇数还是偶数
                int offset = ((right1 - left1 + 1) & 1) ^ 1;
                if (arr1[mid1] < arr2[mid2]) {
                    left1 = mid1 + offset;
                    right2 = mid2;
                }else {
                    right1 = mid1;
                    left2 = mid2 + offset;
                }
            }
        }
        return Math.min(arr1[left1], arr1[left2]);
    }

    public static void main(String[] args) {
        System.out.println(getUpMedianCp1(new int[]{3,4,5,6}, new int[]{1,2,3,4}));
    }


}
