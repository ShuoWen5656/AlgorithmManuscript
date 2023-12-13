package classespackage.stackAndQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author swzhao
 * @Date 2021/11/16 21:53
 * @Discription<> 如何仅用递归函数和栈操作逆序一个栈
 */
public class ReverseStackUtils{

    /**
     * 递归取栈底并放入,通用方法
     */
    public static <T> void reverse(Stack<T> stack){
        try {
            if(stack.isEmpty()){
                return;
            }else{
                T t = getAndRemoveLastElement(stack);
                reverse(stack);
                stack.push(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return;
    }


    /**
     * 取栈底值
     * 1、递归的截止条件一定要在调用递归方法之前
     * 2、递归方法之前就是营造一个能够截止递归的操作，如stack.pop()，而截止递归操作就是为了获取栈底
     * 3、递归方法之后则是处理本次递归的剩余逻辑，如 将栈顶再放回去
     */
    public static <T> T getAndRemoveLastElement(Stack<T> stack){
        try {
            T t = stack.pop();
            if(stack.isEmpty()){
                return t;
            }else{
                T t2 = getAndRemoveLastElement(stack);
                stack.push(t);
                return t2;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 普通方法，intger类型
     * @param stack
     * @return
     */
    public static void reverseForInt(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        Integer curlast = getLastAndRemoveForInt(stack);
        reverse(stack);
        stack.push(curlast);
    }


    public static Integer getLastAndRemoveForInt(Stack<Integer> stack){
        Integer pop = stack.pop();
        if (stack.isEmpty()){
            return pop;
        }
        Integer last = getLastAndRemoveForInt(stack);
        stack.push(pop);
        return last;
    }

    /**
     * 测试用例
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        Stack<Integer> testStack = new Stack<>();
        Arrays.stream(arr).forEach(u -> testStack.push(u));
        System.out.println(testStack.toString());
        // 逆序
        ReverseStackUtils.reverseForInt(testStack);
        System.out.println(testStack.toString());
    }


}
