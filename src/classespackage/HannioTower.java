package classespackage;

/**
 * @Author swzhao
 * @Date 2021/11/19 21:56
 * @Discription<> 汉诺塔，返回步数，输出走法
 */
public class HannioTower {


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
     * 处理函数
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


}
