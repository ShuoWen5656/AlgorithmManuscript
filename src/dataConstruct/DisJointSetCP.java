package dataConstruct;

import dataConstruct.MyTreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author swzhao
 * @data 2022/10/2 10:50
 * @Discreption <> 并查集数据结构（二轮副本）
 * 并查集介绍：并查集主要是对元素进行，集合化（每一个元素都是集合），合并（集合与集合之间通过某种自定义逻辑进行合并），查找（通过某个节点能够快速的查找到集合（代表集合））
 */
public class DisJointSetCP<T> {

    /**
     * key 代表 某个元素
     * value 代表某个元素的父节点
     */
    Map<T, T> fatherMap;
    /**
     * key 代表某个元素
     * value 代表该元素作为代表元素时，下面还有多少层，初始化为0层
     */
    Map<T, Integer> rankMap;


    public DisJointSetCP(List<T> eList) {
        this.fatherMap = new HashMap<>();
        this.rankMap = new HashMap<>();
        initMap(eList);
    }

    /**
     * 将所有元素初始化为集合并设置rank
     * @param eList
     */
    private void initMap(List<T> eList) {
        // 初始化所有元素的父节点指向自己（代表节点的最后也指向自己）
        // 初始化rank为0
        for (T e : eList){
            fatherMap.put(e, e);
            rankMap.put(e, 0);
        }
    }

    /**
     * 找到节点e的父节点并在寻找过程中进行fathermap的更新
     * 查找的路径上所有节点最后的father节点都变成共同的代表节点（并查集中最大的优化，尽可能的减少rank,但是这个过程并没有更新rank的操作，更新过程只对代表集合节点生效）
     * 这也是为什么说rankmap并不严格但是同时带来了性能提升
     * @param e
     * @return
     */
    public T findFather(T e){
        T father = fatherMap.get(e);
        if (father != e){
            // 如果不是自己也就是说自己不是代表，那就继续往上找，直到找到代表节点（e == father）为止
            father = findFather(father);
        }
        // 找完后将自己的父节点更新成代表节点
        fatherMap.put(e, father);
        return father;
    }


    /**
     * 合并操作，外界通过自己的逻辑进行合并操作，当前数据结构不提供业务逻辑判断
     * @param e1
     * @param e2
     */
    public void union(T e1, T e2){
        if (e1 == null || e2 == null){
            return;
        }
        T e1Father = findFather(e1);
        T e2Father = findFather(e2);
        if (e1Father == e2Father){
            // 在同一个集合中不操作
            return;
        }else {
            // 不同集合需要比较rank
            Integer rank1 = rankMap.get(e1Father);
            Integer rank2 = rankMap.get(e2Father);
            // rank大的作为父节点
            if (rank1 > rank2) {
                fatherMap.put(e2Father, e1Father);
            }else if (rank1 < rank2){
                fatherMap.put(e1Father, e2Father);
            }else {
                // 相等的情况默认e1作为代表
                fatherMap.put(e2Father, e1Father);
                // e2集合的代表节点需要更新rank
                rankMap.put(e2Father, rank2 + 1);
            }
        }
    }




}
