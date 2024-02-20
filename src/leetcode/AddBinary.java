package leetcode;

/**
 * @author swzhao
 * @date 2023/12/2 10:56 上午
 * @Discreption <>二进制求和
 */
public class AddBinary {


    public static void main(String[] args) {
        solution3("1", "111");
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


    /**
     * 逃课玩法,但是不能过所有的测试用例
     * @param a
     * @param b
     * @return
     */
    public static String solution2(String a, String b) {
        return Integer.toBinaryString(
        Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }


    /**
     * 第三种玩法，按照位计算，精简版本
     * @param a
     * @param b
     * @return
     */
    public static String solution3(String a, String b) {
        StringBuffer res = new StringBuffer();

        int len = Math.max(a.length(), b.length());
        // 进位
        int carry = 0;
        for (int i = 0; i < len; i++) {
            carry += i >= a.length() ? 0 : a.charAt(a.length() - 1 - i) - '0';
            carry += i >= b.length() ? 0 : b.charAt(b.length() - 1 - i) - '0';
            // carry最大不超过3，最小为0
            res.append(carry % 2);
            // 计算进位
            carry /= 2;
        }
        if (carry > 0) {
            res.append("1");
        }
        res.reverse();
        return res.toString();
    }


}
