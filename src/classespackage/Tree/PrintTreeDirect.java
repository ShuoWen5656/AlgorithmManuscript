package classespackage.Tree;

import classespackage.Constants;
import dataConstruct.MyTreeNode;

/**
 *
 * @author swzhao
 * @date 2022/3/9 7:08 下午
 * @Discreption <>
 */
public class PrintTreeDirect {

    /**
     * 如何较为直观的打印二叉树
     * @param root
     */
    public static void printTree(MyTreeNode root){
         try {
             if(root == null){
                 return;
             }
             printTree2(root, 0, Constants.TYPE_ROOT);
         }catch (Exception e){
             e.printStackTrace();
         }
     }


    /**
     * 右递归打印二叉树
     * @param root
     */
    public static void printTree2(MyTreeNode root, int count, String type){
        if(root == null){
            return;
        }
        int tem = count;
        printTree2(root.getRight(), count + 1, Constants.TYPE_RIGHT);
        for (int i = 0; i < count * 17; i++){
            // 打印空格
            System.out.println(" ");
        }
        Integer data = root.getData();
        // 计算位数
        int count2 = 0;
        int sub = 1;
        while (data / sub > 0) {
            sub = sub*10;
            count2++;
        }
        int pre = (17 - (count2 + 2)) >>> 1;
        int suf = 17 - pre;
        for (int i = 0; i < pre; i++){
            System.out.println(" ");
        }
        if(Constants.TYPE_ROOT.equals(type)){
            // 根节点
            System.out.println("H"+data+"H");
        }else if(Constants.TYPE_RIGHT.equals(type)){
            System.out.println("v"+data+"v");
        }else if(Constants.TYPE_LEFT.equals(type)){
            System.out.println("^"+data+"^");
        }
        for (int i = 0; i < suf; i++){
            System.out.println(" ");
        }
        printTree2(root.getLeft(), count + 1, Constants.TYPE_LEFT);

     }
}
