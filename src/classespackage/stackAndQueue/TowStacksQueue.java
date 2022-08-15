package classespackage.stackAndQueue;

import java.util.Stack;
import java.util.logging.Logger;

/**
 * @Author swzhao
 * @Date 2021/11/13 12:41
 * @Discription<> 双栈实现队列：pop时一个栈倒入另一个，另一个再pop即可
 */
public class TowStacksQueue {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public TowStacksQueue() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }

    /**
     * 队列进
     * @param num
     */
    public void add(Integer num){
        inStack.push(num);
    }

    /**
     * 队列出一个
     * @return
     */
    public Integer poll(){
        if(inStack.isEmpty() && outStack.isEmpty()){
            throw new RuntimeException("双栈空,没有数据了~");
        }
        if(outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    /**
     * 队列尾巴元素
     * @return
     */
    public Integer peek(){
        if(inStack.isEmpty() && outStack.isEmpty()){
            throw new RuntimeException("双栈空,没有数据了~");
        }
        if(outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    public boolean isEmpty(){
        return inStack.isEmpty() && outStack.isEmpty();
    }


    @Override
    public String toString() {
        return "TowStacksQueue{" +
                "inStack=" + inStack +
                ", outStack=" + outStack +
                '}';
    }

    //private static final Logger log = Logger.getLogger("TowStacksQueue");


    public static void main(String[] args) {
        TowStacksQueue towStacksQueue = new TowStacksQueue();
        int[] arr = {2, 1, 4, 5, 3};
        // 随机吐出来一个，验证半途中poll
        int index = (int)(Math.random() * arr.length);
        for (int i = 0; i < arr.length; i++){
            System.out.printf("当前进入字符%d\n", arr[i]);
            towStacksQueue.add(arr[i]);
            System.out.printf("当前队列：%s\n", towStacksQueue.toString());
            if (i == index){
                System.out.printf("随机吐出:%d\n", towStacksQueue.poll());
            }
        }
        // 吐
        while (!towStacksQueue.isEmpty()){
            Integer poll = towStacksQueue.poll();
            System.out.printf("吐出当前队列头:%s\n", poll);
            System.out.printf("当前队列：%s\n", towStacksQueue.toString());
        }
    }


}
