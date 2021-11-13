package classespackage;

import java.util.Stack;

/**
 * @Author swzhao
 * @Date 2021/11/13 12:09
 * @Discription<> 最小值栈的第二种是实现形式：比起第一种，第二种返回的时间快，空间会多一些，第一种（MyStack1）空间会节省，时间会多一些
 */
public class MyStack2 {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public MyStack2() {
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    /**
     * 如果当前值小于最小值，则两个栈都放入最小值，如果不是，最小值栈放最小值，data栈放当前值,时间复杂度O(1) 空间O(n)
     * @param num
     */
    public void push(Integer num){
        if(dataStack.isEmpty()){
            this.dataStack.push(num);
            this.minStack.push(num);
            return;
        }
        if(this.getMin() >= num){
            // 当前push了一个最小值，则data和min栈全都放入最小值
            this.dataStack.push(num);
            this.minStack.push(num);
        }else{
            this.minStack.push(this.getMin());
            this.dataStack.push(num);
        }
    }


    public Integer pop(){
        if(dataStack.isEmpty()){
            throw new RuntimeException("当前栈dataStack为空，无法获取");
        }
        minStack.pop();
        return dataStack.pop();
    }


    public Integer getMin(){
        if(minStack.isEmpty()){
            throw new RuntimeException("当前栈dataStack为空， 无法获取最小值");
        }
        return minStack.peek();
    }


    @Override
    public String toString() {
        return "MyStack2{" +
                "dataStack=" + dataStack +
                ", minStack=" + minStack +
                '}';
    }
}
