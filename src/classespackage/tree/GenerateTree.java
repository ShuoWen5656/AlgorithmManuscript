package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;

/**
 * @author swzhao
 * @date 2022/4/9 10:42 上午
 * @Discreption <>通过有序数组生成平衡搜索二叉树
 */
public class GenerateTree {


    /**
     * 根据中序遍历的array结果，重新生成tree
     * 1、因为是平衡的，所以每一次递归的中点就是根节点
     * @param array
     * @return
     */
    public static MyTreeNode generateTree(int[] array){
        try {
            if(array == null){
                return null;
            }
            return generateTree2(array, 0, array.length - 1);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public static MyTreeNode generateTree2(int[] array, int start, int end){
        if(start > end){
            return null;
        }
        int mid = (start + end)/2;
        int midValue = array[mid];
        MyTreeNode root = new MyTreeNode(midValue);
        root.setLeft(generateTree2(array, 0, mid));
        root.setRight(generateTree2(array, mid + 1, end));
        return root;
    }



    public static MyTreeNode generate(int[] arr){
        if (arr == null){
            return null;
        }
        return process(arr, 0, arr.length-1);
    }

    private static MyTreeNode process(int[] arr, int start, int end) {
        if (start > end){
            return null;
        }
        if (start == end){
            return new MyTreeNode(arr[start]);
        }
        int midIndex = (start + end)/2;
        int midValue = arr[midIndex];

        MyTreeNode root = new MyTreeNode(midValue);

        root.setLeft(process(arr, start, midIndex - 1));
        root.setRight(process(arr, midIndex + 1, end));
        return root;
    }


    public static void main(String[] args) {
        MyTreeNode root = generate(new int[]{1, 3, 5, 6, 7, 8});
        CommonUtils.printTree(root);
    }

}
