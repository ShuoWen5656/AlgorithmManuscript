package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/6 8:41
 * @Discreption <> 将整数字符串转成整数值
 */
public class Convert2NumFromStr {


    /**
     * 将整数字符串转成整数值，溢出的返沪false
     * @param str
     * @return
     */
    public static int convert(String str){
        try {
            char[] chars = str.toCharArray();
            if(!isValid(chars)){
                return 0;
            }
            // 由于补码负数会比正数多1个，所以先按照负数相加，最后根据符号判断最终结果是否溢出
            // 结果
            int res = 0;
            // 表示最小值的10位以上的数字
            int minq = Integer.MIN_VALUE/10;
            // 表示最小值的个位数字
            int minr = Integer.MIN_VALUE%10;
            // 表示最终结果的符号
            boolean posi = true;
            for (int i = 0; i < chars.length; i++){
                if(i == 0 && chars[i] == '-'){
                    posi = false;
                    continue;
                }
                // 到这里chars[i] 应该都是数字了
                // 获取到数字
                int cur = chars[i] - '0';
                // 判断是否溢出
                if(res < minq){
                    // 说明后面不管再加什么都会溢出
                    return 0;
                }else if(res == minq && !posi && cur < minr){
                    // 说明更新数值之后，res的十位以上都会和minq相同，个位溢出了,并且是负数
                    return 0;
                }else if(res == minq && posi && cur < minr + 1){
                    // 正数并且十位以上都相同，个位溢出
                    return  0;
                }else{
                    res = res*10 + (-cur);
                }
            }

            return posi? res : -res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 校验chars是否是整数序列
     * @param chars
     * @return
     */
    public static boolean isValid(char[] chars){
        if(chars == null || chars.length == 0){
            return false;
        }
        // 开头不是‘-’或者数字的不行
        if(chars[0] != '-' && (chars[0] - '0' < 0 || chars[0] - '0' > 9)){
            return false;
        }
        // 开头是0，长度只能是1
        if(chars[0] == '0' && chars.length > 1){
            return false;
        }
        // 开头是‘-’，1、长度不能是1 2、第二位不能是0
        if((chars[0] == '-' && chars.length == 1)
                || (chars[0] == '-' && chars[1] == '0')){
            return false;
        }
        // 开头情况已经判断过了
        for (int i = 1; i < chars.length; i++){
            if(chars[i] < '0' || chars[i] > '9'){
                return false;
            }
        }
        return true;
    }



}
