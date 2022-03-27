package classespackage.stackAndQueue;

import java.util.Stack;

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


}
