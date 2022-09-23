package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.MyTreeNode;
import dataConstruct.Record;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author swzhao
 * @date 2022/3/28 3:10 下午
 * @Discreption <>找到二叉树中符合搜索二叉树的最大拓扑结构的大小
 */
public class BSTTopSize {


    /**
     * 方法一：
     *      · 获取以h为头节点的最大拓扑顺序：首先遍历所有节点，将所有节点进行从头开始查找，如果查找到了，则在拓扑结构中，如果不在，则不在拓扑结构中
     * 遍历入参，将所有节点都遍历一遍，更新最大节点数量并返回即可。
     * @param root
     * @return
     */
    public static int getBstTopSize1(MyTreeNode root){
        try {
            // 选择先序遍历
            int maxNum = getBstTopSize12(root);
            return maxNum;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public static int getBstTopSize12(MyTreeNode root){
        if(root == null){
            // 最大拓扑结构数量为0
            return 0;
        }
        // 计算以自己为头节点的最大拓扑数量
        int curMaxNum = getMax(root);
        // 计算左子树中最大拓扑数量
        int leftMaxNum = getBstTopSize12(root.getLeft());
        int rightMaxNum = getBstTopSize12(root.getRight());
        // 选出三个中的最大值返回到上层
        return Math.max(Math.max(curMaxNum, leftMaxNum), rightMaxNum);
    }

    // 获取以当前root为头节点的最大拓扑结构的大小
    public static int getMax(MyTreeNode root){
        // 结果
        int count = 0;
        // 堆遍历
        Queue<MyTreeNode> myTreeNodeQueue = new LinkedList<>();
        myTreeNodeQueue.offer(root);
        while (!myTreeNodeQueue.isEmpty()){
            MyTreeNode poll = myTreeNodeQueue.poll();
            MyTreeNode left = poll.getLeft();
            MyTreeNode right = poll.getRight();
            if(left != null){
                myTreeNodeQueue.offer(left);
            }
            if(right != null){
                myTreeNodeQueue.offer(right);
            }
            if(getValueByBST(root, poll.getData())){
                // 能够找到目标值
                count ++;
            }
        }
        return count;
    }

    public static int myMax(MyTreeNode root){
        if (root == null){
            return 0;
        }

        return myPosOrder(root);

    }

    private static int myPosOrder(MyTreeNode root) {
        if (root == null){
            return 0;
        }
        int leftMax = myPosOrder(root.getLeft());
        int rightMax = myPosOrder(root.getRight());

        // 计算当前
        int cur = getMaxByRoot(root);
        return Math.max(cur, Math.max(leftMax, rightMax));
    }

    private static int getMaxByRoot(MyTreeNode root) {
        if (root == null){
            return 0;
        }
        // 层序遍历
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()){
            MyTreeNode cur = queue.poll();
            // 从root开始
            MyTreeNode tem = root;
            while (tem != null){
                if (cur.getData().equals(tem.getData())){
                    // 找到了
                    count++;
                    break;
                }else if (cur.getData() > tem.getData()){
                    tem = tem.getRight();
                }else {
                    tem = tem.getLeft();
                }
            }
            if (cur.getLeft() != null){
                queue.offer(cur.getLeft());
            }
            if (cur.getRight() != null){
                queue.offer(cur.getRight());
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getSearchMyTreeNode();
        MyTreeNode node10 = new MyTreeNode(97);
        MyTreeNode node9 = new MyTreeNode(99);
        node10.setRight(root);
        node10.setLeft(node9);
        PrintTreeDirect.myPrint(node10);
        System.out.print("\n\n\n\n\n\n\n\n\n");
        System.out.println(myMax(node10));
    }



    /**
     * 判断是否能够找到目标值
     * @param root
     * @param target
     * @return
     */
    public static boolean getValueByBST(MyTreeNode root, int target){
        MyTreeNode cur = root;
        boolean hasFind = false;
        while (cur != null){
            if(target < cur.getData()){
                // 左边
                cur = cur.getLeft();
            }
            if(target > cur.getData()){
                cur = cur.getRight();
            }
            if(target == cur.getData()){
                // 相等，说明找到了
                hasFind = true;
                break;
            }
        }
        return hasFind;
    }

    /**
     * 方法二：
     *      · 主体是一个后续遍历
     *      · 孩子节点首先进行最大拓扑结构的贡献值更新，最后计算当前整个树的拓扑更新，每一个节点记录到map中
     *      · 每一个节点都有贡献值（m,n）其中，m是左孩子的贡献值，n是右孩子的贡献值
     *      · 每次后续遍历到一个节点后，通过该节点重新更新贡献值，并更新当前节点的贡献值，更新之后返回当前贡献值和左边孩子还是右边孩子返回的最大贡献值的最大者 ，再返回上一层
     * @param root
     * @return
     */
    public static int getBstTopSize2(MyTreeNode root){
        try{
            // 用来保存树里面每一个节点对应的左右贡献
            Map<MyTreeNode, Record> nodeRecordMap = new HashMap<>();
            // 后续遍历开始，最后返回最大拓扑结构数量
            return posOrderBTS(root, nodeRecordMap);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 后续遍历并处理当前节点
     * @param root
     * @param nodeRecordMap
     * @return
     */
    public static int posOrderBTS(MyTreeNode root, Map<MyTreeNode, Record> nodeRecordMap){
        if(root == null){
            return 0;
        }
        // 获取左边的最大拓扑数量:未考虑root
        int lmax = posOrderBTS(root.getLeft(), nodeRecordMap);
        // 获取右边最大的拓扑数量：未考虑root
        int rmax = posOrderBTS(root.getRight(), nodeRecordMap);
        // 处理当前节点：1、以当前value更新左右子树的贡献值, 2、将自己的贡献值放进map中
        int value = root.getData();
        modifyMap(root, value, nodeRecordMap, true);
        modifyMap(root, value, nodeRecordMap, false);
        // 更新完之后,获取当前贡献值
        // 左孩子贡献值
        int lv = nodeRecordMap.get(root.getLeft()) == null ? 0 : nodeRecordMap.get(root.getLeft()).getLv() + nodeRecordMap.get(root.getLeft()).getRv() + 1;
        int rv = nodeRecordMap.get(root.getRight()) == null ? 0 : nodeRecordMap.get(root.getRight()).getLv() + nodeRecordMap.get(root.getRight()).getRv() + 1;
        // 这里解释一下： 有可能在当前root的影响之下，拓扑还没有之前左孩子或者右孩子计算出来的数量多。
        return Math.max(lv + rv + 1, Math.max(lmax, rmax));
    }


    /**
     * 更新map中的record值，以当前value
     * 1、左边就一直遍历左孩子的右边界，只要有一个大于value直接返回上一层，上一层剪掉当前value的全部贡献，更新map并返回当前节点的总贡献
     * @param root
     * @param value
     * @param nodeRecordMap
     * @param isLeft
     * @return
     */
    public static int modifyMap(MyTreeNode root, int value, Map<MyTreeNode, Record> nodeRecordMap, boolean isLeft){
        MyTreeNode child = isLeft ? root.getLeft() : root.getRight();
        if(child == null || nodeRecordMap.get(child) == null){
            return 0;
        }
        if((isLeft && child.getData() > value)
                || (!isLeft && child.getData() > value)){
            // 这种情况说明需要将当前树节点裁剪掉
            Record record = nodeRecordMap.get(child);
            // 将当前树的全部贡献返回
            return record.getLv() + record.getRv() + 1;
        }else{
            if(isLeft){
                // 左边需要取右边界
                int cut = modifyMap(child.getRight(), value, nodeRecordMap, isLeft);
                // 到这里返回左子树的cut节点，将当前节点的数量更新
                Record record = nodeRecordMap.get(child);
                // 剪掉分支
                record.setRv(record.getRv() - cut);
                // 返回最新的record记录
                return record.getLv() + record.getLv() + 1;
            }else{
                // 右边需要取左边界
                int cut = modifyMap(child.getLeft(), value, nodeRecordMap, isLeft);
                // 到这里返回右子树的cut节点，将当前节点的左数量更新
                Record record = nodeRecordMap.get(child);
                // 剪掉分支
                record.setLv(record.getLv() - cut);
                // 返回最新的record记录
                return record.getLv() + record.getLv() + 1;
            }
        }
    }

}
