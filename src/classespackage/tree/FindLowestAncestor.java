package classespackage.tree;

import classespackage.CommonUtils;
import dataConstruct.LowestTreeNodeHelper;
import dataConstruct.MyTreeNode;
import dataConstruct.Query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author swzhao
 * @date 2022/4/11 5:37 下午
 * @Discreption <>在二叉树中找到两个节点的最近公共祖先
 * 给定head，o1和o2
 */
public class FindLowestAncestor {


    /**
     * 入口方法
     * 1、后续遍历，返回的时候判断左右子节点是否时O1或者O2，如果都是，则当前节点就是最近的节点，
     * 2、如果不是，向上返回不是null的节点即可
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    public static MyTreeNode findLowestAncestor(MyTreeNode root, MyTreeNode o1, MyTreeNode o2){
        try{
            if(root == null || root == o1 || root == o2){
                // 将o1和o2还有null都保留到上一层
                return root;
            }
            MyTreeNode left = findLowestAncestor(root.getLeft(), o1, o2);
            MyTreeNode right = findLowestAncestor(root.getRight(), o1, o2);
            // 最后处理自己
            if(left != null && right != null){
                // 左边右边就是o1和o2
                return root;
            }
            return left != null? left : right;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法二，利用空间来获取公共节点
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    public static MyTreeNode findLowestAncestor2(MyTreeNode root, MyTreeNode o1, MyTreeNode o2){
        try{
            // child-parent
            Map<MyTreeNode, MyTreeNode> map = new HashMap<>();
            map.put(root, null);
            // 填装
            fillMap(root, map);
            // new一个容器用来装o1到root的所有节点
            MyTreeNode tem = o1;
            HashSet<MyTreeNode> o1Set = new HashSet<>();
            while (tem != null){
                // 将当前节点加入
                o1Set.add(tem);
                // 将当前节点的父节点拿出来再次循环
                tem = map.get(tem);
            }
            // o2开始循环找到第一个在o1Set里面的元素就是公共头
            tem = o2;
            while (tem != null){
                if(o1Set.contains(tem)){
                    return tem;
                }
                tem = map.get(tem);
            }
            // 正常来说应该右公共节点，如果没有则while循环结束
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将root的所有节点都存到map中
     * @param root
     */
    public static void fillMap(MyTreeNode root, Map<MyTreeNode, MyTreeNode> map ){
        if(root == null){
            return;
        }
        if(root.getLeft() != null){
            map.put(root.getLeft(), root);
        }
        if(root.getRight() != null){
            map.put(root.getRight(), root);
        }
        fillMap(root.getLeft(), map);
        fillMap(root.getRight(), map);
    }

    /**
     * 进阶解法3：
     * 将每一个节点和与该节点最近公共祖先的节点放到map<map>中
     * 查直接查就行
     * 1、首先cur节点和cur节点中左右子树中的节点之间最近的公共祖先都是cur
     * 2、其他的节点需要递归获取
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    public static MyTreeNode findLowestAncestor3(MyTreeNode root ,MyTreeNode o1, MyTreeNode o2){
        try{
            // 保存节点1与其所有节点的最短祖先map
            Map<MyTreeNode, Map<MyTreeNode, MyTreeNode>> map = new HashMap<>();
            initMap(root , map);
            // 填充
            setMap(root, map);
            // 查找
            if(map.get(o1) != null){
                return map.get(o1).get(o2);
            }
            if(map.get(o2) != null){
                return map.get(o2).get(o1);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void initMap(MyTreeNode root ,Map<MyTreeNode, Map<MyTreeNode, MyTreeNode>> map){
        if(root == null){
            return;
        }
        map.put(root, new HashMap<>());
        initMap(root.getLeft(), map);
        initMap(root.getRight(), map);
    }

    /**
     * 填充map
     * 1、填充所有节点跟自己的最短距离
     * 2、其次填充左右子节点之间的最短距离，左边每一个节点都要遍历右边的每一个节点
     * @param root
     * @param map
     */
    public static void setMap(MyTreeNode root ,Map<MyTreeNode, Map<MyTreeNode, MyTreeNode>> map){
        if(root == null){
            return;
        }
        // 将当前节点和子节点都写入map
        recordHead(root.getLeft(), root, map);
        recordHead(root.getRight(), root, map);
        // 将左子节点和右子节点中的每一对节点都放入map中
        recordSub(root, map);
        setMap(root.getLeft(), map);
        setMap(root.getRight(), map);
    }


    /**
     * 将孩子节点全部遍历一遍，每一个孩子跟自己的最近节点都是自己
     * @param child
     * @param root
     * @param map
     */
    public static void recordHead(MyTreeNode child , MyTreeNode root,Map<MyTreeNode, Map<MyTreeNode, MyTreeNode>> map){
        if(child == null){
            return;
        }
        map.get(child).put(root, root);
        recordHead(child.getLeft(), root, map);
        recordHead(child.getRight(), root, map);
    }

    /**
     * 对每一个孩子都做一遍
     * 将自己和孩子的左右子节点全部遍历一遍放入map中
     * @param root
     * @param map
     */
    public static void recordSub(MyTreeNode root, Map<MyTreeNode, Map<MyTreeNode, MyTreeNode>> map){
        if(root == null){
            return;
        }
        leftRecord(root.getLeft(), root.getRight(), root, map);
        recordSub(root.getLeft(), map);
        recordSub(root.getRight(), map);
    }

    public static void leftRecord(MyTreeNode left, MyTreeNode right, MyTreeNode root,Map<MyTreeNode, Map<MyTreeNode, MyTreeNode>> map){
        if (left == null){
            return;
        }
        rightRecord(left ,right, root, map);
        leftRecord(left.getLeft(), right, root, map);
        leftRecord(left.getRight(), right, root, map);
    }

    /**
     * 遍历右子树，将右子树的全部节点都放入map中
     * @param right
     * @param root
     * @param map
     */
    public static void rightRecord(MyTreeNode left ,MyTreeNode right, MyTreeNode root, Map<MyTreeNode, Map<MyTreeNode, MyTreeNode>> map){
        if (right == null){
            return;
        }
        map.get(left).putIfAbsent(right, root);
        rightRecord(left ,right.getLeft(), root, map);
        rightRecord(left, right.getRight(), root, map);
    }


    /**
     * 判断公共祖先
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    public static MyTreeNode publicAncestors(MyTreeNode root, MyTreeNode o1, MyTreeNode o2){
        if (root == null || o1 == null || o2 == null){
            return null;
        }
        // 存储公祖先
        MyTreeNode[] record = new MyTreeNode[1];
        myProcess(root, o1, o2, record);
        return record[0];
    }

    /**
     * 每一层递归返回值包含：
     * 1、boolean数组长度为2， 0：是否包含5， 1是否包含8
     * 2、record长度为1，判断是否已经出现了公共祖先
     * @param root
     * @param o1
     * @param o2
     * @param record
     * @return
     */
    private static boolean[] myProcess(MyTreeNode root, MyTreeNode o1, MyTreeNode o2, MyTreeNode[] record) {
        if (root == null){
            return new boolean[]{false, false};
        }

        boolean[] leftR = myProcess(root.getLeft(), o1, o2, record);
        boolean[] rightR = myProcess(root.getRight(), o1, o2, record);
        // 首先判断是否已经存在公共祖先
        if (record[0] != null){
            // 已经出现公共祖先了，就直接返回
            return new boolean[]{true, true};
        }else {
            // 说明还没有出现公共祖先
            boolean[] curR = new boolean[2];
            curR[0] = leftR[0] || rightR[0];
            curR[1] = leftR[1] || rightR[1];
            if (curR[0] && curR[1]){
                record[0] = root;
            }else {
                // 判断一下自己
                if (root == o1){
                    curR[0] = true;
                }else if (root == o2){
                    curR[1] = true;
                }
            }
            return curR;
        }
    }


    /**
     * 方法二：
     * 制作map，向上寻找公共祖先
     * @param root
     * @param r1
     * @param r2
     * @return
     */
    public static MyTreeNode publicAncestors2(MyTreeNode root, MyTreeNode r1, MyTreeNode r2){
        if (root == null || r1 == null || r2 == null){
            return null;
        }
        Map<MyTreeNode, MyTreeNode> map = CommonUtils.convertTree2Map(root);
        Set<MyTreeNode> r1Set = new HashSet<>();

        MyTreeNode cur = r1;
        while (map.containsKey(cur)){
            r1Set.add(cur);
            cur = map.get(cur);
        }
        cur = r2;
        while (map.containsKey(cur)){
            if (r1Set.contains(cur)){
                return cur;
            }
            cur = map.get(cur);
        }
        return null;
    }


    /**
     * 方法三
     * 时间和空间都是n^2 但是可以计算出所有节点之间的最短公共祖先
     * @param root
     * @param r1
     * @param r2
     * @return
     */
    public static MyTreeNode publicAncestors3(MyTreeNode root, MyTreeNode r1, MyTreeNode r2){
        LowestTreeNodeHelper helper = new LowestTreeNodeHelper(root);
        return helper.getLowestAncestor(r1, r2);
    }



    public static void main(String[] args) {
        MyTreeNode root = CommonUtils.getCompleteBinaryTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8});

        CommonUtils.printTree(root);


        MyTreeNode target8 = CommonUtils.findFromTree(root, 8);
        MyTreeNode target7 = CommonUtils.findFromTree(root, 5);


        System.out.println(publicAncestors3(root, target8, target7));


    }


}
