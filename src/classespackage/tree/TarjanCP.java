package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.DisJointSetCP;
import dataConstruct.MyTreeNode;
import dataConstruct.Query;

import java.util.*;

/**
 * @author swzhao
 * @data 2022/10/2 9:28
 * @Discreption <>Tarjan算法与并查集解决二叉树节点间最近公共祖先的批量查询问题（二轮副本）
 * 二叉树节点数n，查询语句条数m，则查询的时间复杂度O(M+N)
 */
public class TarjanCP {


    /**
     * 核心数据结构 - 并查集
     */
    DisJointSetCP<MyTreeNode> disJointSetCP;

    /**
     * 每一个节点的查询任务
     */
    Map<MyTreeNode, List<MyTreeNode>> queryMap;

    /**
     * 每一个查询任务对应的index
     */
    Map<MyTreeNode, List<Integer>> answerMap;


    /**
     * key - 并查集中的代表节点
     * value - 集合中的根节点
     */
    Map<MyTreeNode, MyTreeNode> ancestorMap;


    public TarjanCP() {
        this.queryMap = new HashMap<>();
        this.answerMap = new HashMap<>();
        this.ancestorMap = new HashMap<>();
    }

    /**
     * 入口方法
     * 传入head和要查询的组合
     * 按照顺序返回答案
     * @param head
     * @param queries
     * @return
     */
    public MyTreeNode[] queryAns(MyTreeNode head, Query[] queries){
        // 将树转化为列表并初始化并查集
        Map<MyTreeNode, MyTreeNode> myTreeNodeMap = CommonUtils.convertTree2Map(head);
        disJointSetCP = new DisJointSetCP<>(new ArrayList<>(myTreeNodeMap.keySet()));
        // 将查询转化为query和answer
        MyTreeNode[] ans = new MyTreeNode[queries.length];
        convert2QueryAndAnswer(queries, ans);
        query(head, ans);
        return ans;
    }


    /**
     * 解析queries转为map
     * @param queries
     * @param ans
     */
    private void convert2QueryAndAnswer(Query[] queries, MyTreeNode[] ans) {
        for (int i = 0; i < queries.length; i++){
            Query query = queries[i];
            MyTreeNode o1 = query.getO1();
            MyTreeNode o2 = query.getO2();
            // 三种情况可以先获取答案
            if (o1 == null && o2 == null){
                ans[i] = null;
            }else if (o1 == null || o2 == null){
                ans[i] = o1 == null ? o2 : o1;
            }else {
                // 如果没有先初始化
                if (!queryMap.containsKey(o1)){
                    queryMap.put(o1, new LinkedList<>());
                    answerMap.put(o1, new LinkedList<>());
                }
                if (!queryMap.containsKey(o2)){
                    queryMap.put(o2, new LinkedList<>());
                    answerMap.put(o2, new LinkedList<>());
                }
                List<MyTreeNode> o1QueryList = queryMap.get(o1);
                List<MyTreeNode> o2QueryList = queryMap.get(o2);
                List<Integer> o1Index = answerMap.get(o1);
                List<Integer> o2Index = answerMap.get(o2);
                o1QueryList.add(o2);
                o2QueryList.add(o1);
                o1Index.add(i);
                o2Index.add(i);
            }
        }
    }

    /**
     * 递归查询并将结果放入ans
     * 左中右中顺序遍历
     * @param head
     * @param ans
     */
    private void query(MyTreeNode head, MyTreeNode[] ans) {
        if (head == null){
            return;
        }
        // 1、左
        query(head.getLeft(), ans);
        // 2、中
        // 2.1 和左子树进行集合合并
        MyTreeNode left = head.getLeft();
        disJointSetCP.union(head, left);
        // 2.2 合并完成后，将当前的根节点作为集合中的父节点（不是代表节点），并与代表节点形成映射
        ancestorMap.put(disJointSetCP.findFather(head), head);
        // 3、右
        query(head.getRight(), ans);
        // 4、中
        // 4.1 和右子树进行集合合并
        MyTreeNode right = head.getRight();
        disJointSetCP.union(head, right);
        ancestorMap.put(disJointSetCP.findFather(head), head);
        // 4.2 查看是否存在查询任务
        LinkedList<MyTreeNode> queryList = (LinkedList<MyTreeNode>) queryMap.get(head);
        LinkedList<Integer> indexList = (LinkedList<Integer>) answerMap.get(head);
        while (queryList != null && !queryList.isEmpty()){
            // 要与谁查询
            MyTreeNode poll = queryList.poll();
            // 结果放在哪
            Integer index = indexList.poll();
            // 结果就是poll所在集合的父节点
            ans[index] = ancestorMap.get(disJointSetCP.findFather(poll));
        }
    }


    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getTreeForTarjan();
        MyTreeNode node8 = CommonUtils.findFromTree(root, 8);
        MyTreeNode node9 = CommonUtils.findFromTree(root, 9);
        MyTreeNode node3 = CommonUtils.findFromTree(root, 3);

        TarjanCP tarjanCP = new TarjanCP();
        Query[] queries = {new Query(node8, node9), new Query(node9, node3), new Query(node9, null)};
        MyTreeNode[] ans = tarjanCP.queryAns(root, queries);

        System.out.println(ans);

    }


}
