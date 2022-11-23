package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/4 10:23
 * @Discreption <>字符串中数字累加和
 */
public class NumSum {

    public static int getSum(String str){
        try {
            if(str == null || str.length() == 0){
                return 0;
            }
            char[] chars = str.toCharArray();
            // true为正数，false为负数
            boolean posi = true;
            // 累加结果
            int res = 0;
            // 便利到目前数字为止的数值
            int num = 0;
            // 便利到当前字符的数字数值
            int cur = 0;
            for (int i = 0; i < chars.length; i++){
                // 当前遍历到的字符数值
                cur = chars[i] - '0';
                // 如果不是0-9的，需要判断是否是-，如果是‘-’，也可以看成是分割符号
                if(cur < 0 || cur > 9){
                    // 分割
                    res += num;
                    num = 0;
                    if(chars[i] == '-'){
                        if(i-1 > -1 && chars[i-1] == '-'){
                            posi = !posi;
                        }else{
                            posi = false;
                        }
                    }else{
                        // 如果不是，则初始化posi
                        posi = true;
                    }
                }else{
                    num = num * 10 + (posi? cur : - cur);
                }
            }
            // 如果以数字字符结尾的话
            res+=num;
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 二轮测试
     * @param str
     * @return
     */
    public static int getSumCp1(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int res = 0;
        int cur = 0;
        // true为正数
        boolean pos = true;
        for (int i = 0; i < chars.length; ) {
            if (i < chars.length && chars[i] != '-' && (chars[i] - '0' < 0 || chars[i] - '0' > 9)) {
                // 不是‘-’并且也不是数字的直接略过
                i++;
                continue;
            }
            // 定符号
            while (i < chars.length && chars[i] == '-') {
                pos = !pos;
                i++;
            }
            // 到这里只取数字
            while (i < chars.length && chars[i] - '0' > 0 && chars[i] - '0' < 9) {
                cur = cur * 10 + (chars[i] - '0');
                i++;
            }
            res += pos ? cur : -cur;
            // 复位
            cur = 0;
            pos = true;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(getSumCp1("A1DC2E33-1F--12"));
    }

}
