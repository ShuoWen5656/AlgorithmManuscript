package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author swzhao
 * @date 2023/11/15 8:45 下午
 * @Discreption <> 除法求值
 */
public class CalcEquation {


    public static void main(String[] args) {

    }


    public double[] solution(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 1、给所有节点编号
        // 节点值 - 编号
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (List<String> list : equations) {
            if (!map.containsKey(list.get(0))) {
                map.put(list.get(0), count++);
            }
            if (!map.containsKey(list.get(1))) {
                map.put(list.get(1), count++);
            }
        }
        // 2、初始化并查集
        // father[i]代表编号i节点的父节点，初始化都是自己
        int[] father = new int[count];
        // 到father的边长乘积
        double[] w = new double[count];
        for (int i = 0; i < count; i++) {
            father[i] = i;
            w[i] = 1.0d;
        }
        // 3、合并已知的边
        for (int i = 0; i < equations.size(); i++) {
            // 获取前后节点
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            // 合并
            merge(father, w, map.get(a), map.get(b), values[i]);
        }

        // 4、开始计算答案
        double[] res = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            double curRes = -1;
            // 获取问题的两个节点
            String a = queries.get(i).get(0), b = queries.get(i).get(1);
            // 必须都存在才有意义
            if (map.containsKey(a) && map.containsKey(b)) {
                Integer ai = map.get(a);
                Integer bi = map.get(b);
                int fa = findFather(father, w, ai);
                int fb = findFather(father, w, bi);
                if (fa == fb) {
                    // 存在共同父节点说明联通了
                    curRes = w[ai]/w[bi];
                }
            }
            res[i] = curRes;
        }
        return res;
    }

    private int findFather(int[] father, double[] w, int i) {
        if (i != father[i]) {
            int f = findFather(father, w, father[i]);
            // 一路上将当前节点的老父亲father[i]的w值相乘，因为在图中算是一条路径，铺平之后就不会再计算了
            w[i] = w[i] * w[father[i]];
            // 铺平节点
            father[i] = f;
        }
        return father[i];
    }

    private void merge(int[] father, double[] w, int a, int b, double value) {
        // 找到各自父节点的编号
        int fa = findFather(father, w, a);
        int fb = findFather(father, w, b);
        // 将a的父节点fa置为fb节点
        father[fa] = fb;
        // 更新父节点的w，其实 从a 到 b 的w值就是value，也是w[a] * w[fa] / w[b]
        // 也可以理解为w[a]表示a/直连父节点 的结果，也就是父节点的几倍
        // 如 a -> b 为3 表示a是b的3倍，b->c 表示b是c的2倍
        // 此时 a -> b ,b -> c a是c的6倍，b和c合并之后w[a] = 3 w[b] = 2 w[c] = 1
        // 这里计算的fa已经是a节点的直接节点了，递归的时候已经整合了
        w[fa] = value * w[b]/w[a];
    }






}
