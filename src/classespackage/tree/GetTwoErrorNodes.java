package classespackage.tree;

import dataConstruct.MyTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swzhao
 * @date 2022/3/31 8:48 下午
 * @Discreption <>调整搜索二叉树中两个错误的节点
 */
public class GetTwoErrorNodes {


    /**
     * 1、通过二叉树的中序遍历可以发现两次降序节点，第一个错误节点一定在第一次较大的地方，第二个错误节点一定在第二次较小的地方
     * @return
     */
    public static MyTreeNode[] getTwoErrorNodes1(MyTreeNode root){
        MyTreeNode[] res = new MyTreeNode[2];
        List<MyTreeNode> temArray = new ArrayList<>();
        getTowNodes2(root, res, temArray);
        return res;
    }

    /**
     * 先序遍历
     * @param root
     * @param res
     * @param temArray
     */
    public static void getTowNodes2(MyTreeNode root, MyTreeNode[] res, List<MyTreeNode> temArray){
        if(root == null){
            return;
        }
        Integer value = root.getData();
        if(temArray.isEmpty()){
            temArray.add(root);
        }else{
            if(value > temArray.get(temArray.size() - 1).getData()){
                temArray.add(root);
            }else{
                if(res[0] == null){
                    // 第一次降序,选大的
                    res[0] = temArray.get(temArray.size() - 1);
                    // 这里不删除会导致后面的都比这个小的话就会产生一次降序，应该保持顺序
                    temArray.remove(temArray.size() - 1);
                    temArray.add(root);
                }else if(res[1] == null){
                    // 第二次降序,这次选择小的
                    res[1] = root;
                }
            }
        }
        getTowNodes2(root.getLeft(), res, temArray);
        getTowNodes2(root.getRight(), res, temArray);
    }





}
