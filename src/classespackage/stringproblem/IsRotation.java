package classespackage.stringproblem;

/**
 * @author swzhao
 * @data 2022/5/5 9:25
 * @Discreption <> 判断两个字符串是否互为旋转词
 * 将字符串前几位移动到后面形成的字符串与原来的字符串互为旋转词
 */
public class IsRotation {

    public static boolean isRotation(String str1, String str2){
        try {
            // 如果长度不相同则不是旋转词
            if(str1 == null || str2 == null || str1.length() != str2.length()){
                return false;
            }
            String str22 = str2 + str2;
            if(str22.contains(str1)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 二轮测试： 判断是否互为旋转词
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isRotationCp1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        String str3 = str1 + str1;
        return str3.contains(str2);
    }



}
