package classespackage.stackAndQueue;

import java.util.Stack;

/**
 * 可以随时返回最小值的栈数据结构
 */
public class MyStack1 {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;


    public MyStack1() {
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    /**
     * 弹出
     * @return
     */
    public int pop(){
        if(this.dataStack.isEmpty()){
            throw new RuntimeException("dataStack is Empty");
        }
        Integer integer = this.dataStack.pop();
        if(integer == this.getmin()){
            this.minStack.pop();
        }
        return integer;
    }

    /**
     * 进
     * @param num
     */
    public void push(Integer num){
        if(this.minStack.isEmpty() || this.getmin() >= num){
            this.minStack.push(num);
        }
        dataStack.push(num);
    }

    /**
     * 获取最小值
     * @return
     */
    public int getmin(){
        if(this.minStack.isEmpty()){
            throw new RuntimeException("minStack is Empty");
        }else{
            return this.minStack.peek();
        }
    }

    @Override
    public String toString() {
        return "MyStack1{" +
                "dataStack=" + dataStack +
                ", minStack=" + minStack +
                '}';
    }
}
