package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;

import java.util.HashSet;
import java.util.List;

/**
 * @author swzhao
 * @date 2022/3/8 7:18 下午
 * @Discreption <> 打印二叉树的边界节点
 *     打印二叉树的边界：两种规定
 */
public class PrintEdge {


    /**
     * 输出树的边界：标准1
     * 1、获取每一层的最左和最右边的节点分成左边和右边
     * 2、通过先序遍历，获取不是左右边界的叶子节点
     * 3、左边
     * @param root
     */
    public static void printEdge1(MyTreeNode root, List<MyTreeNode> treeNodes){
        try {
            // 获取树高度
            int height = getHeight(root, 0);
            // 二维数组，存每一行的左边节点和右边节点
            MyTreeNode[][] map = new MyTreeNode[height][2];
            // 遍历树将左右节点放进来
            fillMap(root, 0, map);
            // 先将左边的放进去
            for (int i = 0; i < map.length; i++){
                treeNodes.add(map[i][0]);
            }
            // 获取非左右两边的孩子节点
            getChildNotInMap(root, 0, map, treeNodes);
            for (int i = map.length - 1; i >= 0; i--){
                treeNodes.add(map[i][1]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取树高度
     * 1、主要就是左边的深度和右边的深度进行一个比较
     * @param root 根节点
     * @param count
     * @return
     */
    public static int getHeight(MyTreeNode root, int count){
        if(root == null){
            return count;
        }
        int leftH = getHeight(root.getLeft(), count+1);
        int rightH = getHeight(root.getRight(), count+1);
        return Math.max(leftH, rightH);
    }

    /**
     * 将每一行的左边节点和右边节点放入到map中
     * 1、先序遍历
     * @param root
     * @param count
     * @param map
     */
    public static void fillMap(MyTreeNode root, int count, MyTreeNode[][] map){
        if(root == null){
            return;
        }
        // 只接收遍历到这一行的第一个元素
        map[count][0] = map[count][0] == null ? root : map[count][0];
        // 只接收遍历到这一行的最后一个元素
        map[count][1] = root;
        // 下一行
        fillMap(root.getLeft(), count + 1, map);
        fillMap(root.getRight(), count + 1, map);
    }

    /**
     * 获取不在map中的孩子节点
     * 1、先序遍历，查到孩子节点判断是否在map中，不在的话放入treeNodes
     * @param root
     * @param count
     * @param map
     * @param treeNodes
     */
    public static void getChildNotInMap(MyTreeNode root, int count, MyTreeNode[][] map,
                                        List<MyTreeNode> treeNodes){
        if(root == null){
            return;
        }
        if(root.getRight() == null && root.getLeft() == null
                && root != map[count][0] && root != map[count][1]){
            treeNodes.add(root);
        }
        getChildNotInMap(root.getLeft(), count + 1, map, treeNodes);
        getChildNotInMap(root.getRight(), count + 1, map, treeNodes);
    }


    /**
     * 标准一：
     * @param root
     */
    public static void myPrintEdge1(MyTreeNode root){
        if (root == null){
            return;
        }
        // 获取高度
        int height = CommonUtils.getTreeHeight(root, 0);
        MyTreeNode[][] edges = new MyTreeNode[height][2];
        fillEdges(edges, root, 0, height);
        HashSet<MyTreeNode> myTreeNodes = new HashSet<>();
        for (int i = 0; i < edges.length; i++){
            for (int j = 0; j < edges[0].length; j++){
                if (edges[i][j] != null){
                    myTreeNodes.add(edges[i][j]);
                }
            }
        }
        // 打印左边
        for (int i = 0; i < edges.length; i++){
            if (edges[i][0] != null){
                System.out.println(edges[i][0].getData());
            }
        }
        // 打印叶子
        printLeaf(root, myTreeNodes);
        // 打印右边
        for (int i = edges.length-1; i >= 0; i--){
            if (edges[i][1] != edges[i][0]){
                System.out.println(edges[i][1].getData());
            }
        }
    }

    private static void printLeaf(MyTreeNode root, HashSet<MyTreeNode> myTreeNodes) {
        if (root == null){
            return;
        }
        if (!myTreeNodes.contains(root)
                && root.getLeft() == null && root.getRight() == null){
            System.out.println(root.getData());
        }
        printLeaf(root.getLeft(), myTreeNodes);
        printLeaf(root.getRight(), myTreeNodes);
    }


    private static void fillEdges(MyTreeNode[][] edges, MyTreeNode root, int curLevel, int height) {
        if (curLevel == height || root == null){
            return;
        }
        // 每一个节点都要填充左右边界
        edges[curLevel][0] = edges[curLevel][0] == null? root : edges[curLevel][0];
        edges[curLevel][1] = root;
        fillEdges(edges, root.getLeft(), curLevel+1, height);
        fillEdges(edges, root.getRight(), curLevel+1, height);
    }


    /**
     * 标准二
     * @param root
     */
    public static void myPrintEdge2(MyTreeNode root){
        if (root == null){
            return;
        }
        System.out.println(root.getData());
        if (root.getLeft() != null && root.getRight() != null){
            myPrintLeft(root.getLeft(), true);
            myPrintRight(root.getRight(), true);
        }else {
            myPrintEdge2(root.getLeft() == null? root.getRight() : root.getLeft());
        }
    }

    private static void myPrintRight(MyTreeNode root, boolean print) {
        if (root == null){
            return;
        }
        myPrintRight(root.getLeft(), print && root.getRight() == null);
        myPrintRight(root.getRight(), true);
        if (print || (root.getLeft() == null && root.getRight() == null)){
            System.out.println(root.getData());
        }
    }

    private static void myPrintLeft(MyTreeNode root, boolean print) {
        if (root == null){
            return;
        }
        if (print || (root.getLeft() == null && root.getRight() == null)){
            // 打印或者是叶子节点
            System.out.println(root.getData());
        }
        myPrintLeft(root.getLeft(), print);
        // print为true并且左边为null的时候，才处理右节点
        myPrintLeft(root.getRight(), print && root.getLeft() == null);
    }


    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getTreeForEdge();
        myPrintEdge2(root);


    }


}
