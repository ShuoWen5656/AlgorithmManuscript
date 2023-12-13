package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/5 9:10
 * @Discreption <> 去掉字符串中连续出现k个零的字串
 */
public class RemoveKZeros {

    public static String removeZeros(String str, int k){
        if (str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        // 发现0时，start记录子串中第一个出现的0的位置
        int start = -1;
        // 出现不是‘0’时子串中出现零的个数
        int count = 0;
        for (int i = 0; i < chars.length; i++){
            if(chars[i] == '0'){
                start = start == -1 ? i : start;
                count ++;
            }else{
                if(start != -1 && count == k){
                    // 从start删除后count位
                    while (count-- >= 0){
                        chars[start++] = 0;
                    }

                }
                // 初始化下一个子串
                start = -1;
                count = 0;

            }
        }
        // 如果‘0’结尾需要判断一下0的个数是否也符合条件
        if(count == k){
            while (count-- >= 0){
                chars[start++] = 0;
            }
        }
        return String.valueOf(chars);
    }


    /**
     * 二轮测试：删除字符串中k个零
     * @param str
     * @return
     */
    public static String removeK0CP1(String str, int k) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int count = 0;
        int start = -1;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                // 当前是0
                start = start == -1 ? i : start;
                count ++;
            }else {
                // 不是0的时候需要判断是否结尾还是中间
                if (count != 0 && count == k) {
                    // 将start到i部分全部变成0
                    while (start < i) {
                        chars[start++] = 0;
                    }
                }
                count = 0;
                start = -1;
            }
        }
        // 处理000没有结尾的情况
        if (count != 0 && count == k){
            while (count-- > 0) {
                chars[start++] = 0;
            }
        }
        return String.valueOf(chars).replace("\u0000", "");
    }


    public static void main(String[] args) {
        System.out.println(removeK0CP1("A0000B000", 3));
    }

}
