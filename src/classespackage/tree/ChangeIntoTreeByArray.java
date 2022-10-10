package classespackage.tree;

import classespackage.CommonUtils;
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


    /**
     * 根据先序遍历和中序遍历还原二叉树
     * @param arrPre
     * @param arrMid
     * @return
     */
    public static MyTreeNode convert2TreeFromPreAndMid(int[] arrPre, int[] arrMid) {
        if (arrPre == null || arrMid == null
                || arrPre.length != arrMid.length) {
            throw new RuntimeException("入参数组不合法");
        }
        return processForPreAndMid(arrPre, arrMid, 0, arrPre.length-1, 0, arrMid.length-1);
    }

    /**
     * 先序和中序的递归方法
     * @param arrPre
     * @param arrMid
     * @param preStart
     * @param preEnd
     * @param midStart
     * @param midEnd
     * @return
     */
    private static MyTreeNode processForPreAndMid(int[] arrPre, int[] arrMid,
                                                  int preStart, int preEnd, int midStart, int midEnd) {
        if (preStart > preEnd || midStart > midEnd){
            return null;
        }
        if (preStart == preEnd || midStart == midEnd){
            return new MyTreeNode(arrPre[preStart]);
        }
        // 当前根节点value
        int curValue = arrPre[preStart];
        MyTreeNode root = new MyTreeNode(curValue);
        // 当前层在中序遍历的index
        int curIndexInMid = findIndex(curValue, arrMid);
        // 右边子树最后一个节点，这里为了好区分就多了一个变量
        int lastRightIndexInPre = curIndexInMid - midStart + preStart;
        MyTreeNode left = processForPreAndMid(arrPre, arrMid, preStart + 1, lastRightIndexInPre, midStart, curIndexInMid-1);
        MyTreeNode right = processForPreAndMid(arrPre, arrMid, lastRightIndexInPre + 1, preEnd, curIndexInMid + 1, midEnd);
        root.setLeft(left);
        root.setRight(right);
        return root;
    }
    /**
     * 根据中序和后序遍历还原二叉树
     * @param arrPos
     * @param arrMid
     * @return
     */
    public static MyTreeNode convert2TreeFromMidAndPos(int[] arrMid, int[] arrPos) {
        if (arrMid == null || arrPos == null
                || arrPos.length != arrMid.length) {
            throw new RuntimeException("入参数组不合法");
        }
        return processForMidAndPos(arrPos, arrMid, 0, arrPos.length-1, 0, arrMid.length-1);
    }

    /**
     * 中序和后序结合的递归方法
     * @param arrPos
     * @param arrMid
     * @param posStart
     * @param posEnd
     * @param midStart
     * @param midEnd
     * @return
     */
    private static MyTreeNode processForMidAndPos(int[] arrPos, int[] arrMid, int posStart, int posEnd, int midStart, int midEnd) {
        if (posStart > posEnd || midStart > midEnd){
            return null;
        }
        if (posStart == posEnd || midStart == midEnd){
            return new MyTreeNode(arrPos[posStart]);
        }
        int curValue = arrPos[posEnd];
        MyTreeNode root = new MyTreeNode(curValue);
        int curValueInMid = findIndex(curValue, arrMid);
        int rightStartInPos = posEnd - (midEnd - curValueInMid);
        MyTreeNode left = processForMidAndPos(arrPos, arrMid, posStart, rightStartInPos - 1, midStart, curValueInMid - 1);
        MyTreeNode right = processForMidAndPos(arrPos, arrMid, rightStartInPos, posEnd - 1, curValueInMid + 1, midEnd);
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    /**
     * 根据先序和后序遍历重构二叉树
     * @param arrPos
     * @param arrPre
     * @return
     */
    public static MyTreeNode convert2TreeFromPreAndPos(int[] arrPre, int[] arrPos) {

        if (arrPre == null || arrPos == null
                || arrPos.length != arrPre.length) {
            throw new RuntimeException("入参数组不合法");
        }
        return processForPreAndPos(arrPre, arrPos, 0, arrPre.length-1, 0, arrPos.length-1);
    }

    /**
     * 中序和后序结合的递归方法
     * 要求树必须是除了叶子节点以外，其他节点左右节点都存在的树
     * 否则就会出现歧义
     * @param preStart
     * @param preEnd
     * @param posStart
     * @param posEnd
     * @return
     */
    private static MyTreeNode processForPreAndPos(int[] arrPre, int[] arrPos, int preStart, int preEnd, int posStart, int posEnd) {
        if (posStart > posEnd || preStart > preEnd){
            return null;
        }
        if (posStart == posEnd || preStart == preEnd){
            return new MyTreeNode(arrPos[posStart]);
        }
        int curValue = arrPre[preStart];
        MyTreeNode root = new MyTreeNode(curValue);
        // 左子树的根节点
        int leftRootValue = arrPre[preStart + 1];
        int leftRootIndexInPos = findIndex(leftRootValue, arrPos);
        int leftRangeIndexInPre = preStart + 1 + (leftRootIndexInPos - posStart);
        MyTreeNode left = processForPreAndPos(arrPre, arrPos,preStart + 1, leftRangeIndexInPre, posStart, leftRootIndexInPos);
        MyTreeNode right = processForPreAndPos(arrPre, arrPos, leftRangeIndexInPre + 1, preEnd, leftRootIndexInPos + 1, posEnd - 1);
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    /**
     * 通过先序和中序获取后序遍历数组，不能重构二叉树
     * @return
     */
    public static int[] generateArrByPreAndMid(int[] preArr, int[] midArr){

        if (preArr == null || midArr == null
                || preArr.length != midArr.length){
            return null;
        }
        int[] posArr = new int[preArr.length];
        processForGenerateArrByPreAndMid(preArr, midArr, 0, preArr.length - 1, 0, midArr.length - 1, posArr, preArr.length - 1);
        return posArr;
    }

    private static void processForGenerateArrByPreAndMid(int[] preArr, int[] midArr, int preStart, int preEnd, int midStart, int midEnd,
                                                         int[] posArr, int nextEnd) {
        if (preStart > preEnd || midStart > midEnd){
            return;
        }
        if (preStart == preEnd){
            posArr[nextEnd] = preArr[preStart];
        }
        int cur = preArr[preStart];
        // 将pre的第一个值放入pos的最后一个地方
        posArr[nextEnd] = cur;

        int midIndex = findIndex(cur, midArr);
        int preNewEnd = preStart + midIndex - midStart;
        processForGenerateArrByPreAndMid(preArr, midArr, preStart + 1, preNewEnd, midStart, midIndex-1, posArr, nextEnd - (preEnd - preNewEnd + 1));
        processForGenerateArrByPreAndMid(preArr, midArr, preNewEnd + 1, preEnd, midIndex + 1, midEnd, posArr, nextEnd-1);
    }


    public static void main(String[] args) {
        int[] ints = generateArrByPreAndMid(new int[]{1, 2, 4, 8, 5, 3, 6, 7}, new int[]{8, 4, 2, 5, 1, 6, 3, 7});

        System.out.println(ints);
    }
}
