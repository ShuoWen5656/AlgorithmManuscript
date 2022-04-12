package classespackage.tree;

import dataConstruct.MyTreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swzhao
 * @date 2022/4/12 11:03 上午
 * @Discreption <>并查集高级数据结构
 */
public class DisJointSets {
    /**
     * 并查集的每一个节点的父节点
     */
    Map<MyTreeNode, MyTreeNode> fatherMap;
    /**
     * 并查集的每一个节点的rank
     */
    Map<MyTreeNode, Integer> rankMap;

    /**
     * 初始化方法
     */
    public DisJointSets() {
        fatherMap = new HashMap<>();
        rankMap = new HashMap<>();
    }

    /**
     * 初始化Map
     * 中序遍历二叉树将二叉树的每一个节点都初始化到map中
     */
    public void initMap(MyTreeNode root){
        if(root == null){
            return;
        }
        MyTreeNode cur1 = root;
        MyTreeNode cur2 = null;
        while (cur1 != null){
            cur2 = cur1.getLeft();
            if(cur2 != null){
                while (cur2.getRight() != null && cur2.getRight() != cur1){
                    cur2 = cur2.getRight();
                }
                if(cur2.getRight() == null){
                    cur2.setRight(cur1);
                    cur1 = cur1.getLeft();
                    continue;
                }
                cur2.setRight(null);
            }
            // cur1到达最左边，或者二次遍历过来的
            fatherMap.put(cur1, cur1);
            rankMap.put(cur1, 0);
            cur1 = cur1.getRight();
        }
    }

    /**
     * 合并集合
     * 1、先找到father代表
     * 2、找到各自father的rank
     * 3、比较rank，谁大谁做新的father
     * 4、相等的话第一个做father，并且第一个的rank+1
     * @param o1
     * @param o2
     */
    public void union(MyTreeNode o1, MyTreeNode o2){
        MyTreeNode father1 = findFather(o1);
        MyTreeNode father2 = findFather(o2);
        Integer rank1 = rankMap.get(father1);
        Integer rank2 = rankMap.get(father2);
        if(rank1 > rank2){
            fatherMap.put(father2, father1);
        }else if(rank1 < rank2){
            fatherMap.put(father1, father2);
        }else{
            //相等
            fatherMap.put(father2, father1);
            // 更新rank1
            rankMap.put(father1, rank1++);
        }
    }

    public MyTreeNode findFather(MyTreeNode node){
        MyTreeNode father = fatherMap.get(node);
        if(father != node){
            // 一直找到当前节点的father是自己的节点
            father = findFather(father);
        }
        // 一路沿途往上查的同时，将路径上的节点都挂在顶级节点上
        fatherMap.put(node, father);
        return father;
    }

}
