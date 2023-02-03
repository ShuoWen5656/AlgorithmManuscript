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


    /**
     * 求arr中只出现一次的数
     * @param arr
     * @return
     */
    public static int getOneNumCp1(int[] arr, int k) {
        // 假设该数为32位的k进制数,每一位都不大于k
        int[] kE0 = new int[32];
        for (int i = 0; i < arr.length; i++) {
            // 先将arr[i]转k进制
            int[] kE1 = convert2k(arr[i], k);
            // 无进位相加
            kE0 = sumK(kE0, kE1, k);
        }
        // 转成10进制数
        return convert2Tem(kE0, k);
    }


    /**
     * 将10进制转k进制并返回
     * @param num
     * @return
     */
    private static int[] convert2k(int num, int k) {
        int n = num;
        int index = 0;
        int[] res = new int[32];
        while (n != 0) {
            res[index++] = n % k;
            n = n/k;
        }
        return res;
    }

    /**
     * k进制相加
     * @param kE0
     * @param kE1
     * @param k
     * @return
     */
    private static int[] sumK(int[] kE0, int[] kE1, int k) {
        int[] res = new int[32];
        for (int i = 0; i < kE0.length; i++) {
            res[i] = (kE0[i] + kE1[i]) % k;
        }
        return res;
    }


    /**
     * 将k进制转10进制
     * @param kE0
     * @param k
     * @return
     */
    private static int convert2Tem(int[] kE0, int k) {
        int res = 0;
        for (int i = 0; i < kE0.length; i++) {
            res += Math.pow(k, i) * kE0[i];
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(getOneNumCp1(new int[]{5,5,5,6,4,4,4}, 3));
    }


}
