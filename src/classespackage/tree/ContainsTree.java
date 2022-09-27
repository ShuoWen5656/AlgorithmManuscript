package classespackage.tree;

import classespackage.CommonUtils;
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



    public static boolean isContain(MyTreeNode r1, MyTreeNode r2){
        if (r1 == null && r2 == null){
            return true;
        }else if (r2 == null || r1 == null){
            return false;
        }
        return isContain2(r1, r2)
                || isContain(r1.getLeft(), r2)
                || isContain(r1.getRight(), r2);

    }

    private static boolean isContain2(MyTreeNode r1, MyTreeNode r2) {
        if (r1 == null && r2 == null){
            return true;
        }else if (r2 == null || r1 == null){
            return false;
        }
        return r1.getData().equals(r2.getData())
                && isContain2(r1.getLeft(), r2.getLeft())
                && isContain2(r1.getRight(), r2.getRight());
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

    /**
     * mycheck1 是判断r1是否包含r2的
     * @param root1
     * @param root2
     * @return
     */
    public static boolean myCheck(MyTreeNode root1, MyTreeNode root2){
        if (root2 == null){
            return true;
        }else if (root1 == null){
            return false;
        }
        // 首先判断当前节点为头同步遍历是否包含root2，如果不是，则判断左边是否包含，再判断右边是否包含
        return myCheck2(root1, root2) || myCheck(root1.getLeft(), root2) || myCheck(root1.getRight(), root2);
    }

    /**
     * mycheck2 是判断root1和root2同步遍历时是否相同
     * @param root1
     * @param root2
     * @return
     */
    private static boolean myCheck2(MyTreeNode root1, MyTreeNode root2) {
        if (root2 == null){
            return true;
        }else if (root1 == null){
            return false;
        }
        return root1.getData().equals(root2.getData())
                && myCheck2(root1.getLeft(), root2.getLeft())
                && myCheck2(root1.getRight(), root2.getRight());
    }






    public static void main(String[] args) {
        MyTreeNode node6 = CommonUtils.getSearchMyTreeNode();
        MyTreeNode root2 = CommonUtils.getSearchMyTreeNode();

        MyTreeNode root1 = new MyTreeNode(97);
        MyTreeNode node9 = new MyTreeNode(99);
        root1.setRight(node6);
        root1.setLeft(node9);
        PrintTreeDirect.myPrint(root1);
        System.out.print("\n\n\n\n\n\n\n\n\n");
        PrintTreeDirect.myPrint(root2);

        System.out.println(myCheck(root1, root2));
    }

}
