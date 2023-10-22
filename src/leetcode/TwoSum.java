package leetcode;

/**
 * @author swzhao
 * @date 2023/10/22 10:25 上午
 * @Discreption <>两数之和 II - 输入有序数组
 */
public class TwoSum {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2,7,11,15}, 9));
    }


    public static int[] solution(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int[] res = new int[2];
        int index1 = 0;
        int index2 = 0;
        while (index1 < numbers.length) {
            int other = target - numbers[index1];
            if (other < numbers[index1]) {
                // 差值已经小于当前值了，说明前面不可能有答案了,往回找是不可能往回找的，这被子都不可能的
                return null;
            }
            // 到这里说明后面是可能有答案的，但是值也不能超过other
            index2 = index1 + 1;
            while (index2 < numbers.length && numbers[index2] <= other) {
                if (numbers[index2] == other) {
//                    找到了！
                    return new int[]{index1 + 1, index2 + 1};
                }
                index2++;
            }
            // 到这里说明没找到，下一个元素吧
            index1++;
        }
        return res;
    }




}
