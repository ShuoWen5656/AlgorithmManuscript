package classespackage.stackAndQueue;

import classespackage.Constants;

import java.util.Stack;

/**
 * @Author swzhao
 * @Date 2021/11/19 21:56
 * @Discription<> 用栈来求解汉诺塔问题
 */
public class HanoiTower {


    /**
     * 入口函数
     * @param num 汉诺塔数量
     * @param left 左边：可以是字符串
     * @param mid 中间
     * @param right 右边
     * @param from 起点
     * @param to 终点
     * @return
     */
    public int hanioProblem(int num, String left, String mid, String right, String from, String to){
        if(num < Constants.NUM_1){
            return Constants.NUM_0;
        }
        return this.process(num, left, mid, right, from, to);
    }

    /**
     * 递归函数
     * @param num
     * @param left
     * @param mid
     * @param right
     * @param from
     * @param to
     * @return
     */
    private int process(int num, String left, String mid, String right, String from, String to){
        // 终止条件，就一个的时候为终止
        if(num == Constants.NUM_1){
            if(from.equals(mid) || to.equals(mid)){
                // 其中一个点是中间，则只需要一步即可
                System.out.println("Move 1 from :" + from + " to: " + to);
                // 返回步数
                return Constants.NUM_1;
            }else{
                // 都不是中点的情况移动两步，一定会经过中间
                System.out.println("Move 1 from :" + from + " to: " + mid);
                System.out.println("Move 1 from :" + mid + " to: " + to);
                return Constants.NUM_2;
            }
        }
        // 如果数量超过一个，则不是终止条件
        if(from.equals(mid) || to.equals(mid)){
            /**
             * 有一个是中间，分三步走：
             * 1、先移动num-1层到另外一边（交给递归）
             * 2、底层移动到目标
             * 3、num-1移动到目标（交给递归）
             */
            // 另一边一定是终点或者起点的对立面（其中有一个是mid）
            String other = (from.equals(left) || to.equals(left)) ? right : left;
            // num-1起点到对立面
            int part1StepNum = process(num-1, left, mid, right, from, other);
            // 最后一层到达目标
            System.out.println("Move " + num + " from :" + from + " to: " + to);
            int part2StepNum = Constants.NUM_1;
            // num-1起点到终点
            int part3StepNum = process(num-1, left, mid, right, other, to);
            return part1StepNum + part2StepNum + part3StepNum;
        }else{
            /**
             * 从一边到另外一边
             * 1、先移动num-1 到另外一边（递归）
             * 2、移动num到中间
             * 3、再移动num-1 到起点
             * 4、移动num到终点
             * 5、移动num-1到终点
             */
            // num-1层移动到终点
            int part1StepNum = process(num - 1, left, mid, right, from, to);
            // num移动到中间
            System.out.println("Move " + num + " from :" + from + " to: " + mid);
            int part2StepNum = Constants.NUM_1;
            // num-1移动到起点
            int part3StepNum = process(num -1, left, mid, right, to, from);
            // 移动num到终点
            System.out.println("Move " + num + " from :" + mid + " to: " + to);
            int part4StepNum = Constants.NUM_1;
            // 移动num-1到终点
            int part5StepNum = process(num -1, left, mid, right, from, to);
            return part1StepNum + part2StepNum + part3StepNum + part4StepNum + part5StepNum;
        }
    }

    /**
     * 方法二：栈方式实现
     * 首先每一个盘都有四种选择走法
     * 1、左-中、中-左、中-右、右到中
     * 2、每一个状态都面临上面四种抉择
     * 3、四种抉择中通过以下2条定律就能够筛选出能够使用的唯一那条路
     *  a)移动不违反大压小原则，需要过去的圆盘不能比目标栈的栈顶大
     *  b)相邻不可逆：如果前一个动作是左-中，那么下一个动作一定不是中-左，并且不可能重复上一个步骤（违反大压小）
     * 总结：任何时刻，四个动作都只有一个满足ab原则，剩下三个一定违反原则
     * @param num
     * @param left
     * @param mid
     * @param right
     */
    public static int hanoiTower2(int num, String left, String mid, String right){
        Stack<Integer> lS = new Stack<>();
        Stack<Integer> mS = new Stack<>();
        Stack<Integer> rS = new Stack<>();
        // 盘底需要最大值
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        // 先将圆盘放入到最左边的柱子上
        for (int i = num; i > 0; i--){
            lS.push(i);
        }
        // 上一个动作
        ActionEnum[] record = new ActionEnum[]{ActionEnum.NO};
        // 总步数
        int step = 0;
        // 每一次都是四种尝试
        while (rS.size() < num + 1){
            step += fStack2tStack(record, ActionEnum.M2L, ActionEnum.L2M, lS, mS, left, mid);
            step += fStack2tStack(record, ActionEnum.L2M, ActionEnum.M2L, mS, lS, mid, left);
            step += fStack2tStack(record, ActionEnum.R2M, ActionEnum.M2R, mS, rS, mid, right);
            step += fStack2tStack(record, ActionEnum.M2R, ActionEnum.R2M, rS, mS, right, mid);
        }
        return step;
    }

    /**
     * 判断走法
     * @param record
     * @param pre
     * @param now
     * @param fStack
     * @param tStack
     * @param from
     * @param to
     * @return
     */
    private static int fStack2tStack(ActionEnum[] record, ActionEnum pre, ActionEnum now, Stack<Integer> fStack, Stack<Integer> tStack, String from, String to) {
        // 上一波动作不可逆，并且不能出现大压小的问题就可以走
        if (record[0] != pre && fStack.peek() < tStack.peek()){
            System.out.printf("Move %d from %s to %s\n", fStack.peek(), from, to);
            tStack.push(fStack.pop());
            record[0] = now;
            return 1;
        }
        return 0;
    }


    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
//        HanoiTower hanoiTower = new HanoiTower();
        // 两个盘从左边到右边
        HanoiTower.hanoiTower2(2, "left", "mid", "right");
    }



}
