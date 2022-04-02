package classespackage.tree;

import dataConstruct.MyTreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2022/3/26 1:27 下午
 * @Discreption <>二叉树中找到累加和为指定值的最长路径长度
 */
public class getTreeMaxLength {

    /**
     * 获取指定值最长路径长度
     * @param root
     * @param num
     * @return
     */
    public static int getMaxLength(MyTreeNode root, int num){
        try{
            if(root == null){
                return 0;
            }
            // 结果
            int resLen = 0;
            int level = 0;
            Map<Integer, Integer> sumFirstIndex = new HashMap<>();
            sumFirstIndex.put(0, 0);
            int curSum = 0;
            // 先序遍历
            resLen = preOrderRecur(root, num,resLen, level, curSum, sumFirstIndex);
            return resLen;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public static int preOrderRecur(MyTreeNode root, int num ,int resLen, int level, int curSum, Map<Integer, Integer> sumFirstIndex){
        if(root == null){
            // 返回条件，将当前累计下来的最长长度返回回去
            return resLen;
        }
        int preSum = curSum;
        Integer value = root.getData();
        curSum += value;
        // 如果前面存在了，则不放入
        sumFirstIndex.putIfAbsent(curSum, level);
        // 求以当前节点为结尾的最长子数组长度
        int sub = curSum - num;
        if(sumFirstIndex.containsKey(sub)){
            // 更新len
            resLen = Math.max(resLen, level - sumFirstIndex.get(sub) + 1);
        }
        // 如果当前层不存在，则继续遍历左右节点
        int resL = preOrderRecur(root.getLeft(), num, resLen, level+1, curSum, sumFirstIndex);
        int resR = preOrderRecur(root.getRight(), num, resLen, level+1, curSum, sumFirstIndex);
        // 最后返回前判断是否当前层写入了sumFirstIndex，如果写入了需要删除当前层的信息，以免影响兄弟节点判断
        if(sumFirstIndex.get(curSum) == level){
            sumFirstIndex.remove(curSum);
        }
        return Math.max(resL, resR);
    }

}
