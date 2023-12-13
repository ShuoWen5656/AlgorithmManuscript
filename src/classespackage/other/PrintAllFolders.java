package classespackage.other;

import classespackage.stackAndQueue.catDogQueue.Pet;

/**
 * @author swzhao
 * @data 2022/7/4 21:40
 * @Discreption <> 折纸问题
 * 问题：将折纸平放在桌子上，下边缘往上边缘对折，折痕朝下，对折两次，为下下上，求对折n次后的上下顺序
 * 时间O(2^n) 空间 O(n)
 */
public class PrintAllFolders {


    /**
     * 主方法：
     * 将每一次的对折新增的上下次序记录下来，发现新增的2^n个折痕，全部折痕纵向形成完全二叉树，利用右中左顺序遍历树即可
     * @param num
     */
    public static void printAllFolders(int num){
        try {
            process(1, num, true);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    private static void process(int i, int num, boolean down) {
        if (i > num){
            return;
        }
        // 打印右边
        process(i+1, num, true);
        System.out.println(down ? "down" : "up");
        // 打印左边
        process(i+1, num, false);
    }


    /**
     * 二轮测试：纸张折叠num次之后，打印折痕
     * @param num
     */
    private static void printAllFoldersCp1(int num) {
        if (num <= 0) {
            return;
        }
        processCp1(num, null, null);
    }

    /**
     * 中序遍历，递归打印折痕
     * @param num
     * @param parent 父节点是上还是下
     * @param isLeft 是否左子节点
     */
    private static void processCp1(int num, Boolean parent, Boolean isLeft) {
        if (num == 0) {
            // 已经减到0了直接返回
            return;
        }
        Boolean cur = false;
        // 先判断自己的是上还是下
        if (parent == null || isLeft == null) {
            // 根节点直接下
            cur = false;
        }else if (!parent && isLeft) {
            // 根节点是下并且自己是左子树
            cur = false;
        }else if (!parent && !isLeft) {
            // 根节点是下，并且自己是右子树
            cur = true;
        }else if (parent && isLeft) {
            // 上左子树
            cur = false;
        }else if (parent && !isLeft) {
            cur = true;
        }
        // 判断完成后开始下一轮
        processCp1(num - 1, cur, true);
        // 输出自己
        System.out.println(cur ? "上" : "下");
        // 输出右边
        processCp1(num - 1, cur, false);
    }


    public static void main(String[] args) {
        printAllFolders(4);
    }


}
