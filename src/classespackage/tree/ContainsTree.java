package classespackage.tree;

import dataConstruct.MyTreeNode;

/**
 * @author swzhao
 * @date 2022/3/31 9:10 下午
 * @Discreption <>判断t1树是否包含t2树全部的拓扑结构
 */
public class ContainsTree {

    /**
     * 判断t1或t1的子树是否包含t2
     * @param t1
     * @param t2
     * @return
     */
    public static boolean contains(MyTreeNode t1, MyTreeNode t2){
        // 以t1头节点开头的是否包含t2拓扑结构，或者t1子节点包含t2拓扑结构的都是true
        return check(t1, t2) || contains(t1.getLeft(), t2) || contains(t1.getRight(), t2);
    }

    /**
     * 判断两个树t1开头的树是否包含t2
     * @param t1
     * @param t2
     * @return
     */
    public static boolean check(MyTreeNode t1, MyTreeNode t2){
        if(t2 == null){
            // null默认包含
            return true;
        }
        if(t1 == null || t1.getData() != t2.getData()){
            return false;
        }
        return check(t1.getLeft(), t2.getLeft()) && check(t1.getRight(), t2.getRight());
    }


    /**
     * 判断t1或t1的子树是否有跟t2完全相同的拓扑结构 时间O(MxN)
     * @param t1
     * @param t2
     * @return
     */
    public static boolean contains2(MyTreeNode t1, MyTreeNode t2){
        // 我倾向于多重判断提升可读性
        if(check2(t1, t2)){
            return true;
        }else if(contains2(t1.getLeft(), t2)){
            return true;
        }else if(contains2(t1.getRight(), t2)){
            return true;
        }
        return false;
    }


    /**
     * 以t1为头节点的树是否有跟t2完全相同的拓扑结构
     * @param t1
     * @param t2
     * @return
     */
    public static boolean check2(MyTreeNode t1, MyTreeNode t2){
        // 等于null必须都null
        if(t1 == null && t2 == null){
            return true;
        }
        // 否则不相等或者有一个为null的也会返回false
        if(t1 == null || t2 == null || t1.getData() != t2.getData()){
            return false;
        }
        return check2(t1.getLeft(), t2.getLeft()) && check2(t1.getRight(), t2.getRight());
    }


    /**
     * **进阶解法：
     * 1、将t1和t2进行序列化后，判断t1是否存在t2的子串即可
     * 2、难点，不可使用String.contains,需要使用线性复杂度的KMP算法
     * @return
     */
    public static boolean contains3(MyTreeNode t1, MyTreeNode t2){
        String serial1 = SerialTree.serial1(t1);
        String serial2 = SerialTree.serial1(t2);
        if(serial1.contains(serial2)){
            return true;
        }else {
            return false;
        }
        //  考虑到str很长时，contains算法的场景性能没有KMP好，所以使用KMP算法判断



    }


}
