package classespackage.tree;

import classespackage.CommonUtils;
import classespackage.Constants;
import dataConstruct.MyTreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swzhao
 * @date 2022/3/29 8:58 上午
 * @Discreption <>二叉树的按层打印和zigZag打印
 */
public class ZigZagPrint {

    /**
     * 按层打印的方式两种实现：
     * 一、先序遍历，每一层都用count记录并放入map中，map存储一个list，每一层都是从左孩子开始，所以保证顺序，最后从1开始遍历map即可
     * 二、两个指针last指向每一行的最后一个节点，nlast在每一个元素进队列的时候，都指向刚进队列的那个元素
     * @param root
     */
    public static void printByLevel(MyTreeNode root){
        try {
            MyTreeNode last = root;
            // nlast为queue的最后一个元素，所以当前打印元素如果是last，则nlast一定是下一层的最后一个元素
            MyTreeNode nlast = null;
            int level = 0;
            // 堆遍历
            Queue<MyTreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                MyTreeNode poll = queue.poll();
                // 打印
                MyTreeNode left = poll.getLeft();
                MyTreeNode right = poll.getRight();
                if(left != null){
                    queue.offer(left);
                    nlast = left;
                }
                if (right != null){
                    queue.offer(right);
                    nlast = right;
                }
                // 打印和判断
                if(poll == last){
                    last = nlast;
                    level ++;
                    // 当level更新时需要换行
                    System.out.println();
                }
                System.out.println("level" + level + ":" + poll.getData());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * zigZag打印就是从左到右再从右到左打印过程
     * 1、申请一个双端队列
     * 2、判断当前场景：
     *      · 当前场景是从左到右：遍历节点全部从头部弹出，并将当前弹出节点的孩子节点从尾巴放入，左先进，右再进！！！
     *      · 记录一个last表示每一层遍历的最后一个节点（其实就是上一层遍历时第一个进入双端队列的节点，因为是盘旋遍历）
     *      · 如果当前节点为last了，说明要环规则，如果当前是左->右，应该换成右->左
     * @param root
     */
    public static void zigZagPrint(MyTreeNode root){
        try {
            Deque<MyTreeNode> deque = new LinkedList<>();
            deque.addFirst(root);
            MyTreeNode last = root;
            // 当前是否是从左到右的顺序
            boolean isLeft = true;
            while (!deque.isEmpty()){
                if(isLeft){
                    // 从左到右的顺序遍历：头部弹出，尾巴放入，左右顺序
                    MyTreeNode head = deque.pollFirst();
                    MyTreeNode left = head.getLeft();
                    MyTreeNode right = head.getRight();
                    if(left != null){
                        deque.addLast(left);
                    }
                    if(right != null){
                        deque.addLast(right);
                    }
                    if(head == last){
                        // 需要换方向了
                        isLeft = false;
                        // last 为第一个进队列的，也就是当前的队头
                        last = deque.peekFirst();
                        System.out.println();
                    }
                    // 打印
                    System.out.println(head.getData());
                }else{
                    // 从右往左的顺序遍历：尾部弹出，头部放入，右左的顺序
                    MyTreeNode tail = deque.pollLast();
                    MyTreeNode left = tail.getLeft();
                    MyTreeNode right = tail.getRight();
                    if(right != null){
                        deque.addFirst(right);
                    }
                    if(left != null){
                        deque.addFirst(left);
                    }
                    if(tail == last){
                        // 需要换方向了
                        isLeft = true;
                        // last 为第一个进队列的，也就是当前的队尾巴
                        last = deque.peekLast();
                        System.out.println();
                    }
                    // 打印
                    System.out.println(tail.getData());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 方法二
     * @param root
     */
    public static void myZigZag(MyTreeNode root){
        if (root == null){
            return;
        }
        Deque<MyTreeNode> deque = new LinkedList<>();
        deque.offer(root);
        // 从对尾进，从队头出 (<-<-)
        boolean flag = true;
        // 临时存储当前层的最后一个节点,用来切换flag
        MyTreeNode last = root;
        int level = 1;
        System.out.printf("the %d level : ", level);
        while (!deque.isEmpty()){
            MyTreeNode cur = flag ? deque.pollFirst() : deque.pollLast();
            // 先输出
            System.out.print(" " + cur.getData());
            if (flag){
                // 先进左再进右(进队尾)
                if (cur.getLeft() != null){
                    deque.offerLast(cur.getLeft());
                }
                if (cur.getRight() != null){
                    deque.offerLast(cur.getRight());
                }
            }else {
                // 先进右再进左(进队头)
                if (cur.getRight() != null){
                    deque.offerFirst(cur.getRight());
                }
                if (cur.getLeft() != null){
                    deque.offerFirst(cur.getLeft());
                }
            }
            if (!deque.isEmpty() && cur == last){
                // 遍历到最后一个了
                // 1、层数增加
                level++;
                // 2、打印下一层开头
                System.out.printf("\nthe %d level : ", level);
                // last 给下一层最后一个
                last = flag ? deque.peekFirst() : deque.peekLast();
                // 调换dq弹出方向
                flag = !flag;
            }
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getSearchMyTreeNode();
        CommonUtils.printTree(root);
        myZigZag(root);
    }



}
