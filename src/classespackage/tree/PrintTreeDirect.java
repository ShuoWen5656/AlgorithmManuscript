package classespackage.tree;

import classespackage.CommonUtils;
import classespackage.Constants;
import com.sun.jndi.cosnaming.CNCtx;
import dataConstruct.MyTreeNode;

/**
 *
 * @author swzhao
 * @date 2022/3/9 7:08 下午
 * @Discreption <>如何较为直观的打印二叉树
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
        printTree2(root.getRight(), count + 1, Constants.TYPE_RIGHT);
        for (int i = 0; i < count * 17; i++){
            // 打印空格
            System.out.print(" ");
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
            System.out.print(" ");
        }
        if(Constants.TYPE_ROOT.equals(type)){
            // 根节点
            System.out.print("H"+data+"H");
        }else if(Constants.TYPE_RIGHT.equals(type)){
            System.out.print("v"+data+"v");
        }else if(Constants.TYPE_LEFT.equals(type)){
            System.out.print("^"+data+"^");
        }
        for (int i = 0; i < suf-1; i++){
            System.out.print(" ");
        }
        System.out.println(" ");
        printTree2(root.getLeft(), count + 1, Constants.TYPE_LEFT);

     }


    public static void myPrint(MyTreeNode root){
        if (root == null){
            return;
        }

        myProcess(root, Constants.TYPE_ROOT,0);
     }

    private static void myProcess(MyTreeNode root, String type ,int level) {
        if (root == null){
            return;
        }
        myProcess(root.getRight(), Constants.TYPE_RIGHT,level+1);
        // 中
        Integer data = root.getData();
        String label = Constants.EMPTY_STR;
        if(Constants.TYPE_ROOT.equals(type)){
            label = "H";
        }else if(Constants.TYPE_RIGHT.equals(type)){
            label = "v";
        }else if(Constants.TYPE_LEFT.equals(type)){
            label = "^";
        }
        StringBuilder sb = new StringBuilder(label + data + label);
        for (int i = 0; i < 17 - sb.length(); i++){
            sb.append(" ");
        }
        for (int i = 0; i < level*17; i++){
            System.out.print(" ");
        }
        System.out.println(sb.toString());
        myProcess(root.getLeft(), Constants.TYPE_LEFT,level+1);
    }


    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getSearchMyTreeNode();

        myPrint(root);
    }

}
