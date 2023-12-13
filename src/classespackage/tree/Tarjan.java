package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.DisJointSets;
import dataConstruct.MyTreeNode;
import dataConstruct.Query;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author swzhao
 * @date 2022/4/12 11:02 上午
 * @Discreption <>进阶问题：Tarjan算法与并查集解决二叉树节点间最近公共祖先的批量查询问题
 */
public class Tarjan {


    /**
     * key：节点，value：与当前节点存在查询任务的节点列表
     */
    Map<MyTreeNode, LinkedList<MyTreeNode>> queryMap;

    /**
     * key：节点， value：存在的查询任务对应的查询结果应该放数组的什么位置
     */
    Map<MyTreeNode, LinkedList<Integer>> indexMap;

    /**
     * key：代表节点， value：所在集合的祖先节点
     */
    Map<MyTreeNode, MyTreeNode> ancestorMap;

    DisJointSets disJointSets;


    public Tarjan() {
        this.queryMap = new HashMap<>();
        this.indexMap = new HashMap<>();
        this.ancestorMap = new HashMap<>();
        this.disJointSets = new DisJointSets();
    }

    /**
     * 入口方法
     * 1、初始化queryMap和indexMap
     * 2、初始化并查集
     * 3、遍历：左中右中的顺序进行
     *      ` 先判断是否o1 = o2， o1=null|| o2 == null的情况，直接给出答案
     *      ` 判断当前cur节点的祖先节点是否存在，如果不存在，则设置
     *      ` 再判断是否有查询任务，如果有则查询另一个节点是否有祖先节点，如果没有，则忽略，有则答案为另一个祖先节点
     *      ` 返回到上一层后，判断左子节点还是右子节点返回的，然后将当前节点集合与左右子节点集合合并
     * @param root
     * @param queries
     * @return
     */
    public MyTreeNode[] tarJanQuery(MyTreeNode root, Query[] queries){
        try{
            MyTreeNode[] ans = new MyTreeNode[queries.length];
            initQuery(queries, ans);
            disJointSets.initMap(root);
            setAnswer(root, ans);
            return ans;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void initQuery(Query[] queries, MyTreeNode[] ans){
        MyTreeNode o1 = null;
        MyTreeNode o2 = null;
        for (int i = 0; i < queries.length ; i++){
            o1 = queries[i].getO1();
            o2 = queries[i].getO2();
            if(o1 == null && o2 == null){
                ans[i] = null;
                continue;
            }else if(o1 == null || o2 == null){
                ans[i] = o1 == null ? o2 : o1;
                continue;
            }
            // o1和o2 都不是null了
            if(queryMap.get(o1) == null){
                queryMap.put(o1, new LinkedList<>());
                indexMap.put(o1, new LinkedList<>());
            }
            queryMap.get(o1).add(o2);
            indexMap.get(o1).add(i);
            if(queryMap.get(o2) == null){
                queryMap.put(o2, new LinkedList<>());
                indexMap.put(o2, new LinkedList<>());
            }
            queryMap.get(o2).add(o1);
            indexMap.get(o2).add(i);
        }
    }

    /**
     * 后序遍历节点
     * 1、遍历一个子树结束后，合并当前root和子树，并且设置合并后集合的祖先节点为当前节点
     * 2、最后对当前节点root开始判断是否存在查询任务，如果存在查询任务则直接从map中查出另一个节点
     * 3、通过另一个节点在map中查询到祖先节点，祖先节点即为答案
     * @param root
     * @param ans
     * @return
     */
    public MyTreeNode[] setAnswer(MyTreeNode root, MyTreeNode[] ans){
        if(root == null){
            return null;
        }
        setAnswer(root.getLeft(), ans);
        // 合并当前节点和左子树的集合
        disJointSets.union(root.getLeft(), root);
        // 更新祖先map
        ancestorMap.put(disJointSets.findFather(root.getRight()), root);
        setAnswer(root.getRight(), ans);
        disJointSets.union(root.getRight(), root);
        ancestorMap.put(disJointSets.findFather(root.getRight()), root);
        // 开始对当前节点进行判断
        if(queryMap == null || queryMap.get(root) == null){
            return null;
        }
        // 将该节点的两个变量取出来
        LinkedList<MyTreeNode> queryTreeNodes = queryMap.get(root);
        LinkedList<Integer> indexList = indexMap.get(root);
        while (queryTreeNodes != null && !queryTreeNodes.isEmpty()){
            // 将比较的节点取出来
            MyTreeNode o2Node = queryTreeNodes.poll();
            Integer ansIndex = indexList.poll();
            // 如果比较的节点已经计算过，则会出现在ancestorMap中
            MyTreeNode father = disJointSets.findFather(o2Node);
            if(ancestorMap.get(father) != null && ansIndex != null){
                ans[ansIndex] = ancestorMap.get(disJointSets.findFather(o2Node));
            }
        }
        return null;
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
