package leetcode;


/**
 * @author swzhao
 * @data 2023/10/4 16:12
 * @Discreption <> 分发糖果
 */
public class Candy {

    public static void main(String[] args) {
        int[] ints = {0,1,2,5,3,2,7};
        System.out.println(solution1(ints));
    }



    public static int solution1(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }else if (ratings.length == 1) {
            return 1;
        }

        // 左边的增加因子,右边的增加因子
        int lbase = 1, rbase = 0;
        int index = getNextIndex(ratings, 0);
        // 糖果总数:如果第一个有下坡，则先计算第一个下坡的糖果数量
        int res = getCand(0, index);
        // 从第二个开始
        index++;
        // 找到第一个爬坡点
        while (index < ratings.length) {
            if (ratings[index - 1] < ratings[index]) {
                // 严格递增,因子加一
                lbase++;
                // 结果增加
                res+=lbase;
                index++;
            }else if (ratings[index-1] > ratings[index]){
                // 严格递减了,注意index不是顶点
                // 1、直接找到下一个起始点先
                int next = getNextIndex(ratings, index);
                // 2、计算下坡长度
                rbase = next - index + 2;
                res += getCand(index - 1, next) - (rbase < lbase ? rbase : lbase);
                lbase = 1;
                // 更新索引到第二个值
                index = next + 1;
            }else {
                // 相等了，可以以最少的给就行
                lbase = 1;
                res+=lbase;
                index++;
            }
        }

        return res;
    }

    private static int getCand(int start, int end) {
        int len = end - start + 1;
        // 等差数列求和
        return (len * (len + 1))/2;
    }


    private static int getNextIndex(int[] ratings, int start) {
        // 返回平地或者爬坡的起点
        for (int i = start; i < ratings.length-1; i++) {
            if (ratings[i] <= ratings[i+1]) {
                return i;
            }
        }
        return ratings.length - 1;
    }


}
