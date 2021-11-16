package classespackage;

import java.util.Stack;

/**
 * @Author swzhao
 * @Date 2021/11/16 21:53
 * @Discription<> 将一个栈通过递归方式逆序化
 */
public class ReverseStackUtils{

    /**
     * 递归取栈底并放入
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


}
