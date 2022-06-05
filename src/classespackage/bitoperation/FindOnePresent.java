package classespackage.bitoperation;

/**
 * @author swzhao
 * @data 2022/6/4 9:43
 * @Discreption <> 在其他数都出现k次的数组中找到只出现一次的数
 */
public class FindOnePresent {


    /**
     * 主方法：
     * @param array
     * @param k
     * @return
     */
    public static int onceNum(int[] array, int k){
        try {
            // 32位k进制数
            int[] e0 = new int[32];
            for (int i = 0; i < array.length; i++){
                // 将array[i]化为k进制数保存到e0中
                setExclusiveOr(e0, array[i], k);
            }
            return getNumFromKSysNum(e0, k);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将num转k进制和e0进行无进位整数相加
     * @param e0
     * @param num
     * @param k
     */
    private static void setExclusiveOr(int[] e0, int num, int k) {
        int[] kSysNumFromNum = getKSysNumFromNum(num, k);
        for (int i = 0; i < e0.length; i++){
            // 每一位进行无进位相加
            e0[i] = (e0[i] + kSysNumFromNum[i]) % k;
        }
    }


    /**
     * 将十进制的num化为k进制数
     * @param num
     * @param k
     * @return
     */
    public static int[] getKSysNumFromNum(int num, int k){
        int[] res = new int[32];
        int index = 0;
        while (num != 0){
            // 取余数
            res[index++] = num%k;
            // 取商
            num = num/k;
        }
        return res;
    }

    /**
     * 将k进制数转成10进制
     * @param e0
     * @param k
     * @return
     */
    public static int getNumFromKSysNum(int[] e0, int k){
        int res = 0;
        for (int i = e0.length-1; i > -1; i--){
            res = res + e0[i] * k;
        }
        return res;
    }

}
