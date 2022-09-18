package classespackage.tree;

import classespackage.CommonUtils;
import classespackage.Constants;
import dataConstruct.LinkNode;
import dataConstruct.MyTreeNode;

import java.util.List;
import java.util.Stack;

/**
 * @author swzhao
 * @date 2022/3/12 9:14 下午
 * @Discreption <>遍历二叉树的神级方法
 */
public class MorrisTree {


    /**
     * 1、二叉树的null节点为二叉树的节点数量+1
     * 中序遍历:
     * 1、将左子树的最右边节点指向当前节点，继续左子树
     * 2、判断当前节点左子树最右节点是否已经指向自己：
     *  ·指向自己：将最右节点的右节点清空，打印当前节点
     *  ·不指向自己：返回步骤1，完事儿后继续步骤2
     * @param root
     * @param nodeList
     */
    public static void morrisTree(MyTreeNode root, List<MyTreeNode> nodeList){
        if(root == null){
            return;
        }
        MyTreeNode cur1 = root;
        MyTreeNode cur2 = null;
        // 这里的结束条件为最后的右节点指向null，正常的要么又节点有值，要么指向上层节点
        while (cur1 != null){
            // 获取左节点,一直获取左边，经历步骤1
            cur2 = cur1.getLeft();
            if(cur2 != null){
                // 找到左子节点的最右节点，判断最右节点的条件有两个，要么右节点null，要么等于cur1
                while (cur2.getRight() != null && cur2.getRight() != cur1){
                    cur2 = cur2.getRight();
                }
                // 结束后，cur2为cur1的左子节点的最右节点
                // 判断步骤2，右节点是否指向cur1
                if(cur2.getRight() == null){
                    // 说明当前还在步骤一，没有完，那么就要将当前的right指向cur1
                    cur2.setRight(cur1);
                    // 继续下一个左节点
                    cur1 = cur1.getLeft();
                    continue;
                }else{
                    // 到这里说明cur2的右节点已经指向了cur1，这时已经遍历完了cur1的左半部分，清空cur2的右节点，还原
                    cur2.setRight(null);
                }
            }
            // 如果cur2为null，说明步骤一结束,cur1指向最左边，直接输出即可
            // 如果cur2最右节点指向了cur1说明cur1的左边已经遍历完毕。
            nodeList.add(cur1);
            cur1 = cur1.getRight();
        }
    }

    /**
     * 先序遍历
     * @param root
     * @param nodeList
     */
    public static void morrisTree2(MyTreeNode root, List<MyTreeNode> nodeList){
        try{
            if(root == null){
                return;
            }
            MyTreeNode cur1 = root;
            MyTreeNode cur2 = null;
            while (cur1 != null){
                cur2 = cur1.getLeft();
                if(cur2 != null){
                    // 找到最右边的节点
                    while (cur2.getRight() != null && cur2.getRight() != cur1){
                        cur2 = cur2.getRight();
                    }
                    if(cur2.getRight() == null){
                        cur2.setRight(cur1);
                        // 步骤一的时候输出cur1
                        nodeList.add(cur1);
                        cur1 = cur1.getLeft();
                        continue;
                    }else{
                        // 回旋到这清空right，进入下一个right
                        cur2.setRight(null);
                    }
                }else {
                    // 这里的else为了防止回旋后，再输出cur1的情况，先序遍历回旋回去以后不需要再输出了
                    // cur2==null的时候说明步骤一到最后了，cur1的父节点已经进队列了，这时候到cur1了
                    nodeList.add(cur1);
                }
                cur1 = cur1.getRight();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 后续遍历
     * @param root
     * @param nodeList
     */
    public static void morrisTree3(MyTreeNode root, List<MyTreeNode> nodeList){
        if(root == null){
            return;
        }
        MyTreeNode cur1 = root;
        MyTreeNode cur2 = null;
        while (cur1 != null){
            cur2 = cur1.getLeft();
            if(cur2 != null){
                while (cur2.getRight() != null && cur2.getRight() != cur1){
                    cur2.getRight();
                }
                if (cur2.getRight() == null){
                    cur2.setRight(cur1);
                    cur1 = cur1.getLeft();
                    continue;
                }else{
                    cur2.setRight(null);
                    // 步骤2回旋时，将cur1的左节点的右边届全部打印
                    printEdge(cur1.getLeft(), nodeList);
                }
            }
            cur1 = cur1.getRight();
        }
        // 因为节点打印的情况都是左子树的右边界，最后还剩下跟节点没有打印
        printEdge(root, nodeList);
    }



    public static void myMorris(MyTreeNode root){
        if (root == null){
            return;
        }
        MyTreeNode cur = root;
        while (cur != null){
            MyTreeNode left = cur.getLeft();
            if (left != null){
                while (left.getRight() != null && left.getRight() != cur){
                    left = left.getRight();
                }
                if (left.getRight() == null){
                    System.out.print(cur.getData());
                    left.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                }
                // left == cur
                left.setRight(null);
                cur = cur.getRight();
            }else {
                System.out.print(cur.getData());
                cur = cur.getRight();
            }
        }
    }

    public static void myMirrisMid(MyTreeNode root){
        if (root == null){
            return;
        }
        MyTreeNode cur = root;
        while (cur != null){
            MyTreeNode left = cur.getLeft();
            if (left != null){
                while (left.getRight() != null && left.getRight() != cur){
                    left = left.getRight();
                }
                if (left.getRight() == null){
                    left.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                }
                System.out.print(cur.getData());
                left.setRight(null);
                cur = cur.getRight();
            }else {
                System.out.print(cur.getData());
                cur = cur.getRight();
            }
        }
    }

    public static void myMirrisPos(MyTreeNode root){
        if (root == null){
            return;
        }
        MyTreeNode cur = root;
        while (cur != null){
            MyTreeNode left = cur.getLeft();
            if (left != null){
                // 辅助栈，这里不通过翻转降低空间复杂度了麻烦
                Stack<MyTreeNode> stack = new Stack<>();
                stack.push(left);
                while (left.getRight() != null && left.getRight() != cur){
                    left = left.getRight();
                    stack.push(left);
                }
                if (left.getRight() == null){
                    left.setRight(cur);
                    cur = cur.getLeft();
                    continue;
                }
                // 走到这里说明left到了回到上一层的地方
                while (!stack.isEmpty()){
                    System.out.print(stack.pop().getData());
                }
                cur = left.getRight();
                left.setRight(null);
                //if (cur.getLeft() != null && cur.getLeft() != left){
                //    System.out.print(cur.getLeft().getData());
                //}
                cur = cur.getRight();
            }else {
                //System.out.print(cur.getData());
                cur = cur.getRight();
            }
        }
        cur = root;
        Stack<MyTreeNode> helper = new Stack<>();
        while (cur != null){
            helper.push(cur);
            cur = cur.getRight();
        }
        while (!helper.isEmpty()){
            System.out.print(helper.pop().getData());
        }
    }



    public static void main(String[] args) {
        MyTreeNode head = CommonUtils.getSearchMyTreeNode();
        PrintTreeDirect.myPrint(head);
        TreeUtils.printPosOrder(head);
        System.out.print("\n");
        myMirrisPos(head);
    }



    /**
     * 打印右边界
     * @param root
     * @param nodeList
     */
    public static void printEdge(MyTreeNode root, List<MyTreeNode> nodeList){
        // 反转右边界,返回尾部
        MyTreeNode tail = reverseEdge(root);
        MyTreeNode cur = tail;
        while (cur != null){
            nodeList.add(tail);
            cur = cur.getRight();
        }
        // 还原二叉树
        reverseEdge(tail);
    }

    /**
     * 反转二叉树右边界
     * @param root
     * @return
     */
    public static MyTreeNode reverseEdge(MyTreeNode root){
        if(root == null){
            return null;
        }
        MyTreeNode pre = null;
        MyTreeNode next = null;
        while (root != null){
            // next先保存下一个节点
            next = root.getRight();
            // 往回指
            root.setRight(pre);
            // pre往下走一个
            pre = root;
            // root往下走一个
            root = next;
        }
        return pre;
    }

}
