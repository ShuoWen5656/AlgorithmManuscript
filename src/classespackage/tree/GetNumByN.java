package classespackage.tree;

import dataConstruct.MyTreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author swzhao
 * @date 2022/4/15 10:00 上午
 * @Discreption <>统计和生成所有不同的二叉树
 */
public class GetNumByN {


    /**
     * 给定中序数组[1...i...num]，求该数组可能形成多少种二叉树
     * 1、中序数组一定是搜索二叉树，所以每一个节点为根节点时，左边的可能数量X右边的可能数量的总和就是答案
     * 因为对称，可以用map存储已经遍历过的节点
     * @param num
     * @return
     */
    public static int getNum(int num){
        if(num < 2){
            return 1;
        }
        int count = 0;
        for (int i = 0; i < num; i++){
            // 以0位根节点
            if(i == 0 || i == num - 1){
                // 只计算右节点
                count += getNum(num - 1);
                continue;
            }
            // 左边的加右边的
            count += getNum(i) * getNum(num - 1 - i);
        }
        return count;
    }


    /**
     * 动态规划的加速解法
     * 动态规划主要是通过从1（已知答案）可以推算出2来，后面的答案只依赖前面的答案
     * 所以通过前面的答案即可计算出答案
     * 动态规划的前提：
     * 1、计算当前值依赖的必须是前面已经计算出答案的
     * 2、答案只跟数量有关，跟谁是根节点没有关系
     * @param num
     * @return
     */
    public static int getNum2(int num){
        if(num < 2){
            return 1;
        }
        int[] res = new int[num + 1];
        // 先将第一个答案放进去
        res[0] = 1;
        // 对于每一个i来说就是以i为根节点的二叉树可能数量
        for (int i = 1; i < num + 1; i++){
            // 这里就是每一个以i为根节点的树需要计算总和
            for (int j = 1; j < i + 1; j++){
                // 左边结果和右边结果的乘积
                res[i] += res[j - 1] * res[i-j];
            }
        }
        return res[num];
    }

    /**
     * 将所有可能性都生成出来，头结点放入MytreeNode
     * @param n
     * @return
     */
    public static List<MyTreeNode> generateTrees(int n){
        return generate(1, n);
    }


    public static List<MyTreeNode> generate(int start, int end){
        List<MyTreeNode> headList = new LinkedList<>();
        if(start > end){
            headList.add(null);
            return headList;
        }
        // 以i为根节点
        for (int i = start; i < end + 1; i++){
            // 先生成节点
            MyTreeNode head = new MyTreeNode(i);
            // 获取左子树的节点
            List<MyTreeNode> leftSub = generate(start, i - 1);
            List<MyTreeNode> rightSub = generate(i+1, end);
            for (MyTreeNode left : leftSub){
                for (MyTreeNode right: rightSub){
                    head.setLeft(left);
                    head.setRight(right);
                    // 复制一份加进去，下次就不会被覆盖了
                    headList.add(cloneTree(head));
                }
            }
        }
        return headList;
    }

    /**
     * 将二叉树复制一份出来
     */
    public static MyTreeNode cloneTree(MyTreeNode root){
        if(root == null){
            return null;
        }
        MyTreeNode res = new MyTreeNode(root.getData());
        res.setLeft(cloneTree(root.getLeft()));
        res.setRight(cloneTree(root.getRight()));
        return res;
    }

}
