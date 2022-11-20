package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/4 9:55
 * @Discreption <> 是判断两个字符串是否互为变形词
 */
public class IsDeformation {

    /**
     * 这里申请空间可以是map类型，也可以是数组类型，数组类型要求类型都是一致的，比如char类型，并且范围都是一定的
     * map类型可以支持比较多的类型
     * str1和str2的长度不一致直接false
     * str1遍历对应数量++，str2对应数量--，只要不出现负值就可以
     * 种类M，长度N
     * 时间O（n）空间O（M）
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isDeformation(String str1, String str2){
        try {
            if(str1 == null || str2 == null || str1.length() != str2.length()){
                return false;
            }
            // index：char类型的UTF-16编码
            int[] map = new int[256];
            char[] chars1 = str1.toCharArray();
            char[] chars2 = str2.toCharArray();
            // 遍历char1
            for (int i = 0; i < chars1.length; i++){
                map[chars1[i]] ++;
            }
            for (int i = 0; i < chars2.length; i++){
                map[chars2[i]] --;
                if(map[chars2[i]] < 0){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 是否互为变形词
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isDeformationCp1(String str1, String str2) {
        if (str1 == null || str2 == null ||  str1.length() != str2.length()) {
            return false;
        }
        int[] map = new int[255];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0;  i < chars1.length; i++) {
            map[chars1[i]]++;
        }
        for (int i = 0; i < chars2.length; i++) {
            map[chars2[i]]--;
            if (map[chars2[i]] < 0) {
                return false;
            }
        }
        return true;
    }
}
