package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author swzhao
 * @date 2022/4/8 12:31 下午
 * @Discreption <>根据后序数组重建搜索二叉树
 */
public class IsPostArray {

    /**
     * 函数入口
     * @param array
     * @return
     */
    public static boolean isPostArray(int[] array){
        try{
            if(array == null || array.length == 0){
                return false;
            }
            return isPostArray2(array, 0, array.length - 1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递归查询每一个子树是否是搜索二叉树
     * less变量代表最后一个比根节点小的index，more代表第一个比根大的index，如果less不在more后面，则不是搜索二叉树
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static boolean isPostArray2(int[] array, int start, int end){
        if(array.length == 1){
            //  只有一个元素时算搜索二叉树
            return true;
        }
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++){
            if(array[i] < array[end]){
                // 小于应该是左子节点
                less = i;
            }else{
                // 大于应该是右边的节点,这里代表第一个大于end的节点位置
                more = more == end? i : more;
            }
        }
        if(less == -1 || more == end){
            // 说明当前树是单边树，只判断一边即可
            return isPostArray2(array, start, end - 1);
        }
        if(less != more - 1){
            // 说明不是搜索二叉树
            return false;
        }
        // 当前树确实左边都比自己小，右边都比自己大，现在判断左右子树如何
        return isPostArray2(array, start, less) && isPostArray2(array, more, end - 1);
    }

    /**
     * 进阶：将数组转为搜索二叉树
     * @param array
     */
    public static MyTreeNode postArrayToBST(int[] array){
        try {
            if(array == null || array.length == 0){
                return null;
            }
            return post2BST(array, 0, array.length - 1);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 转搜索二叉树
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static MyTreeNode post2BST(int[] array, int start, int end){
        if(start > end){
            // 这里就是叶子节点的时候，less=-1或者more=end
            return null;
        }
        // 将当前根节点建立起来
        MyTreeNode head = new MyTreeNode(array[end]);
        // 找到当前的左右子节点用来递归
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++){
            if(array[i] < array[end]){
                // 小于应该是左子节点
                less = i;
            }else{
                // 大于应该是右边的节点,这里代表第一个大于end的节点位置
                more = more == end? i : more;
            }
        }
        // 设置当前的子节点
        head.setLeft(post2BST(array, start, less));
        head.setRight(post2BST(array, more, end - 1));
        return head;
    }


    /**
     * 判断是否能够成为搜索二叉树的后续遍历
     * @param arr
     * @return
     */
    public static boolean isSearch(int[] arr){
        if (arr == null){
            return false;
        }
        // 判断从0到length-1是否是二叉树
        return process(arr, 0, arr.length-1);
    }

    private static boolean process(int[] arr, int start, int end) {
        int head = arr[end];
        // 最后一个小值
        int lastMin = -1;
        // 第一个大值
        int firstMax = -1;
        int index = start;
        while (index < end){
            if (arr[index] < head){
                lastMin = index;
            }else {
                firstMax = firstMax == -1? index : firstMax;
            }
            index ++;
        }
        if (lastMin == -1 && firstMax == -1){
            return true;
        }
        // 到这里至少存在一个不是-1的
        // 默认能够形成搜索二叉树
        boolean left = true;
        boolean right = true;
        if (firstMax == -1){
            // 全都小于head
            left = process(arr, start, lastMin);
        }else if (lastMin == -1){
            right = process(arr, firstMax, end - 1);
        }else {
            // 两个都存在
            if (firstMax < lastMin){
                return false;
            }
            left = process(arr, start, lastMin);
            right = process(arr, firstMax, end - 1);
        }
        return left && right;
    }


    /**
     * 将二叉树后续遍历结果存储到arr数组中
     * @param root
     * @return
     */
    public static int[] savePosOrder2Arr(MyTreeNode root){
        ArrayList<Integer> integers = new ArrayList<>();

        process2(root, integers);

        int[] ints = new int[integers.size()];
        for (int i = 0; i < ints.length; i++){
            ints[i] = integers.get(i);
        }
        return ints;
    }

    private static void process2(MyTreeNode root, ArrayList<Integer> integers) {
        if (root == null){
            return;
        }
        process2(root.getLeft(), integers);
        process2(root.getRight(), integers);
        integers.add(root.getData());
    }


    /**
     * 重建搜索二叉树
     * @param arr
     * @return
     */
    public static MyTreeNode buildTree(int[] arr){
        if (arr == null || !isSearch(arr)){
            return null;
        }
        return processForBuild(arr, 0, arr.length-1);
    }

    private static MyTreeNode processForBuild(int[] arr, int start, int end) {
        if (start == end){
            return new MyTreeNode(arr[start]);
        }
        int rootV = arr[end];
        // 当前节点
        MyTreeNode root = new MyTreeNode(rootV);

        // 计算分界点
        int minIndex = -1;
        int index = start;
        while (arr[index] < rootV){
            minIndex = index;
            index ++;
        }
        MyTreeNode left = null;
        MyTreeNode right = null;
        if (minIndex == -1){
            // 都比rootV大
            right = processForBuild(arr, start, end-1);
        } else if (minIndex + 1 == end){
            // 说明全比end大
            left = processForBuild(arr, start, minIndex);
        }
        else {
            left = processForBuild(arr, start, minIndex);
            right = processForBuild(arr, minIndex+1, end-1);
        }
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getSearchMyTreeNode();
        TreeUtils.printPosOrder(root);
        System.out.print("\n\n");

        CommonUtils.printTree(root);
        int[] ints = savePosOrder2Arr(root);
        System.out.println(isSearch(ints));

        MyTreeNode rootNew = buildTree(ints);
        System.out.print("\n\n\n\n\n\n\n\n\n");
        CommonUtils.printTree(rootNew);


    }

}
