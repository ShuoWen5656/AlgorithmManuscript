package classespackage.other;

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

}
