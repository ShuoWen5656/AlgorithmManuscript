package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2023/11/22 2:20 下午
 * @Discreption <> 生成括号数量
 */
public class GenerateParenthesis {


    public static void main(String[] args) {
        System.out.println(solution(3));
    }

    public static List<String> solution(int n) {
        List<String> res = new ArrayList<>();
        process(0, n, 0,"",n, res);
        return res;
    }


    /**
     * 左括号的额度
     * @param level
     * @param remainLeft 左括号的额度
     * @param remainRight 右括号的额度
     * @param pre
     * @param n
     * @param res
     */
    private static void process(int level, int remainLeft, int remainRight,String pre,int n, List<String> res) {
        if (level == 2 * n) {
            // 当前括号全部放置完毕
            res.add(pre);
            return;
        }
        if (remainLeft > 0) {
            // 说明当前层可以放左括号
            StringBuffer stringBuffer = new StringBuffer(pre);
            stringBuffer.append("(");
            process(level+1, remainLeft - 1, remainRight + 1, stringBuffer.toString(), n, res);
        }
        if (remainRight > 0) {
            // 说明也可以放右括号
            StringBuffer stringBuffer = new StringBuffer(pre);
            stringBuffer.append(")");
            process(level+1, remainLeft, remainRight - 1, stringBuffer.toString(), n, res);
        }
    }

}
