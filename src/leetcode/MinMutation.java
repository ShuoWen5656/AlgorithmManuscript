package leetcode;

import java.util.*;

/**
 * @author swzhao
 * @date 2023/11/18 9:27 上午
 * @Discreption <> 最小基因变化
 */
public class MinMutation {

    public static void main(String[] args) {
        solution("AAAACCCC", "CCCCCCCC", new String[]{"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"});
    }

    public static Character[] KEY = {'A', 'C', 'G', 'T'};

    public static int solution(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) {
            return 0;
        }
        List<String> list = Arrays.asList(bank);
        if (!list.contains(endGene)) {
            return -1;
        }
        int res = 1;
        // 字符串是否已经遍历过
        HashSet<String> vis = new HashSet<>();
        Deque<String> deque = new LinkedList<>();
        deque.addLast(startGene);
        while (!deque.isEmpty()) {
            int curSize = deque.size();
            for (int k = 0; k < curSize; k++) {
                String cur = deque.pollFirst();
                // 暴力变化每一个字符
                for (int i = 0; i < 8; i++) {
                    StringBuffer stringBuffer = new StringBuffer(cur);
                    for (int j = 0; j < KEY.length; j++) {
                        if (KEY[j] == stringBuffer.charAt(i)) {
                            continue;
                        }
                        // 变
                        stringBuffer.setCharAt(i, KEY[j]);
                        String changed = stringBuffer.toString();
                        // 校验合法性
                        if (vis.contains(changed)) {
                            continue;
                        }
                        if (!list.contains(changed)) {
                            continue;
                        }
                        // 合法基因
                        if (changed.equals(endGene)) {
                            return res;
                        }else {
                            vis.add(changed);
                            deque.addLast(changed);
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }




}
