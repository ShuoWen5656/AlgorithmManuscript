package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/15 19:36
 * @Discreption <> 1到n中1出现的次数
 */
public class OneNumFromN {


    /**
     * 方法二：
     * @param n
     * @return
     */
    public static int oneNum2(int n){
        if (n < 1){
            return 0;
        }
        // 获取位数
        int len = getLen(n);
        // 获取最高位的基数
        int tem1 = (int)Math.pow(10, len-1);
        // 最高位
        int first = n/tem1;
        // 最高位是1的所有数的个数：100-114
        // 很显然如果first超过1，则封顶最高位tem1个，如果没超过，则从[tem1:n]个数就是最高位1的个数
        int firstOneNum = first == 1? n/tem1+1 : tem1;
        // 除了最高位以外的其他位,[0:99]这种，如果最高位是2，则需要计算[0-99]和[100:199]，也就是first的个数
        // tem1/10就是除了最高位不参与个数，剩下的数从个位是1，剩下的位任意组合，十位是1，剩下的任意组合.....
        // 最后累加一共len-1组,所以是(len-1) * (tem1/10)
        int otherOneNum = first * (len-1) * (tem1/10);
        return firstOneNum + otherOneNum + oneNum(n%tem1);
    }




    private static int getLen(int n) {
        int res = 0;
        while (n != 0){
            res++;
            n /= 10;
        }
        return res;
    }


    /**
     * 遍历
     * @param n
     * @return
     */
    public static int oneNum(int n){
        if (n < 1){
            return 0;
        }
        int res = 0;
        for (int i = 1; i <= n; i++){
            res += getNum(i);
        }
        return res;
    }

    /**
     * 获取i中的1的个数
     * @param i
     * @return
     */
    private static int getNum(int i) {
        int res = 0;
        int base = 10;
        while (i > 0){
            res++;
            i = i/base;
        }
        return res;
    }





}
