package leetcode;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author swzhao
 * @date 2023/11/6 7:39 下午
 * @Discreption <> 简化路径
 */
public class SimplifyPath {


    public static void main(String[] args) {
        System.out.println(solution("/home//foo/"));
    }



    public static String solution(String path) {
        // 先分割
        String[] split = path.split("/");
        Deque<String> deque = new LinkedList<String>();

        for (int i = 0; i < split.length; i++) {
            if ("..".equals(split[i])) {
                // 弹出
                deque.pollLast();
            }else if (!"".equals(split[i]) && !".".equals(split[i])) {
                // 直接进
                deque.addLast(split[i]);
            }
        }

        // 结合
        StringBuilder stringBuilder = new StringBuilder("/");
        while (!deque.isEmpty()) {
            stringBuilder.append(deque.pollFirst() + "/");
        }
        if (stringBuilder.length() != 1 && stringBuilder.substring(stringBuilder.length() - 1).equals("/")) {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
