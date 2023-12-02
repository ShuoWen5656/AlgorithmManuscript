package leetcode;

/**
 * @author swzhao
 * @date 2023/12/2 10:56 上午
 * @Discreption <>二进制求和
 */
public class AddBinary {


    public static void main(String[] args) {
        solution("1", "111");
    }



    public static String solution(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        // 是否进位
        int flag = 0;
        int indexA = charA.length-1;
        int indexB = charB.length-1;
        char[] res = new char[charA.length > charB.length ? charA.length + 1 : charB.length + 1];
        int indexC = res.length-1;
        while (indexA >= 0 && indexB >= 0) {
            int cA = charA[indexA] - '0';
            int cB = charB[indexB] - '0';
            res[indexC--] = (char)(cA ^ cB ^ flag + '0');
            // 在不算进位的时候，当前位是否会产生进位
            boolean curC = (cA & cB) == 1;
            if (curC) {
                // 自身产生了进位
                flag = 1;
            }else {
                flag = ((cA ^ cB) & flag);
            }
            indexA--;
            indexB--;
        }
        while (indexA >= 0) {
            // 说明b先结束了
            res[indexC--] = (char)(flag ^ (charA[indexA] - '0') + '0');
            flag = (flag & charA[indexA] - '0');
            indexA--;
        }
        while (indexB >= 0) {
            // 说明b先结束了
            res[indexC--] = (char)(flag ^ (charB[indexB] - '0') + '0');
            flag = (flag & charB[indexB] - '0');
            indexB--;
        }
        if (flag == 1) {
            res[indexC] = '1';
        }
        return new String(res).replace("\u0000", "");
    }


}
