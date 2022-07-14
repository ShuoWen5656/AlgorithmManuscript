package classespackage.other;

/**
 * @author swzhao
 * @data 2022/7/12 21:27
 * @Discreption <> 调整[0,x)区间上的数出现的概率
 */
public class RandomXPowerK {


    /**
     * 主方法
     * @param k
     * @return
     */
    public static double randomXPowerK(int k){
        if (k < 1){
            return 0;
        }
        double res = -1;
        for (int i = 0; i < k; i++){
            res = Math.max(res, Math.random());
        }
        return res;
    }
}
