package classespackage.tree;

import dataConstruct.MyTreeNode;

import java.util.Arrays;

/**
 * @author swzhao
 * @date 2022/4/14 9:35 上午
 * @Discreption <>先序、中序、后序数组两两结合重构二叉树
 * 通过先序和中序数组生成后序数组
 */
public class ChangeIntoTreeByArray {

    /**
     * 先序中序结合重构二叉树
     * 此函数将array的tree每一次都
     * @param arrayPre 先序
     * @param arrayIn 中序
     * @return
     */
    public static MyTreeNode preIntoTree(int[] arrayPre, int[] arrayIn){
        if(arrayPre == null || arrayPre.length == 0){
            return null;
        }
        // 先序遍历数组第一个就是当前节点
        MyTreeNode root = new MyTreeNode(arrayPre[0]);
        // 找到中序遍历的位置
        int index = findIndex(arrayPre[0], arrayIn);
        // 将左右中序子树切割出来递归
        int[] leftArray = Arrays.copyOfRange(arrayIn, 0, index);
        int[] rightArray = Arrays.copyOfRange(arrayIn, index + 1, arrayIn.length);
        // 左右子树不管顺序，数量总是一样的，所以按照数量可以找到右子树的头结点
        int[] leftArrayPre = Arrays.copyOfRange(arrayPre, 1, leftArray.length + 1); // 左闭右开
        int[] rightArrayPre = Arrays.copyOfRange(arrayPre, leftArray.length + 1, arrayPre.length + 1);
        root.setLeft(preIntoTree(leftArrayPre, leftArray));
        root.setRight(preIntoTree(rightArrayPre, rightArray));
        return root;
    }


    /**
     * 后序和中序结合
     * @param arrayPost
     * @param arrayIn
     * @return
     */
    public static MyTreeNode postIntoTree(int[] arrayPost, int[] arrayIn){
        if(arrayPost == null || arrayPost.length == 0
                || arrayIn == null || arrayIn.length == 0){
            return null;
        }
        // 后续遍历最后一个元素为root
        MyTreeNode root = new MyTreeNode(arrayPost[arrayPost.length-1]);
        int index = findIndex(arrayPost[arrayPost.length - 1], arrayIn);
        // 中序遍历依然一样
        // 将左右中序子树切割出来递归
        int[] leftArray = Arrays.copyOfRange(arrayIn, 0, index);
        int[] rightArray = Arrays.copyOfRange(arrayIn, index + 1, arrayIn.length);
        // 后序遍历切割
        int[] leftArrayPost = Arrays.copyOfRange(arrayPost, 0, leftArray.length); // 左闭右开
        int[] rightArrayPost = Arrays.copyOfRange(arrayPost, leftArray.length, arrayPost.length - 1);
        root.setLeft(postIntoTree(leftArrayPost, leftArray));
        root.setRight(postIntoTree(rightArrayPost, rightArray));
        return root;
    }

    /**
     * 先序遍历和后序遍历结合
     * 前提：只有每一个节点的孩子树为0或2的树才能够通过先序和后续重构出二叉树
     * @param arrayPre
     * @param arrayPost
     * @return
     */
    public static MyTreeNode postAndPreIntoTree(int[] arrayPre, int[] arrayPost){
        if(arrayPre == null || arrayPre.length == 0
                || arrayPost == null || arrayPost.length == 0){
            return null;
        }
        int preValue = arrayPre[0];
        MyTreeNode root = new MyTreeNode(preValue);
        // 先序遍历来说，左子树array的第一个就是左子节点，由于节点要么是叶子节点，要么就有两个孩子节点
        if(arrayPre.length == 1){
            // 该节点为叶子节点了,左右节点为null
            root.setRight(null);
            root.setLeft(null);
        }else{
            // 该节点一定有左右节点
            // 左子节点值
            int leftValue = arrayPre[1];
            // 找到左子节点在后序遍历的index
            int postIndex = findIndex(leftValue, arrayPost);
            // 切割左子树的先序遍历和后序遍历
            int[] leftPost = Arrays.copyOfRange(arrayPost, 0, postIndex + 1);
            int[] leftPre = Arrays.copyOfRange(arrayPre, 1, leftPost.length + 1);
            // 切割右子树
            int[] rightPost = Arrays.copyOfRange(arrayPost, postIndex + 1, arrayPost.length);
            int[] rightPre = Arrays.copyOfRange(arrayPre, leftPost.length + 1, arrayPre.length);
            root.setLeft(postAndPreIntoTree(leftPre, leftPost));
            root.setRight(postAndPreIntoTree(rightPre, rightPost));
        }
        return root;
    }


    public static int[] getPostArrayByPreAndIn(int[] arrayPre, int[] arrayIn){
        try {
            int[] res = new int[arrayPre.length];
            getPostArrayByPreAndIn2(arrayPre, arrayIn, res, arrayPre.length - 1);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * arrayPre第一位放入结果的最后一个，然后左右子树切分开进入下一次递归即可
     * @param arrayPre
     * @param arrayIn
     * @param res
     * @param pos
     * @return
     */
    public static void getPostArrayByPreAndIn2(int[] arrayPre, int[] arrayIn, int[] res, int pos){
        if(arrayPre == null || arrayPre.length == 0 || arrayIn == null || arrayIn.length == 0){
            return;
        }
        int last = arrayPre[arrayPre.length - 1];
        res[pos--] = last;
        // 拆分
        int index = findIndex(last, arrayIn);
        int[] leftIn = Arrays.copyOfRange(arrayIn, 0, index);
        int[] rightIn = Arrays.copyOfRange(arrayIn, index + 1, arrayIn.length);
        int[] leftPre = Arrays.copyOfRange(arrayPre, 1, leftIn.length + 1);
        int[] rightPre = Arrays.copyOfRange(arrayPre, leftIn.length + 1, arrayPre.length);
        // 这边应该从右边开始
        getPostArrayByPreAndIn2(rightPre, rightIn, res, pos);
        getPostArrayByPreAndIn2(leftPre, leftIn, res, pos);
    }

    /**
     * 找到内容所在index
     * @param value
     * @param array
     * @return
     */
    public static int findIndex(int value, int[] array){
        for (int i = 0; i < array.length ; i++){
            if(array[i] == value){
                return i;
            }
        }
        return 0;
    }

}
