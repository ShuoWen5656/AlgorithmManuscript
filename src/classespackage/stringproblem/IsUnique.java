package classespackage.stringproblem;

import java.util.HashMap;

/**
 * @author swzhao
 * @data 2022/5/9 21:20
 * @Discreption <>判断字符数组中是否所有字符都只出现过一次
 */
public class IsUnique {

    /**
     * 方法一：
     * 空间限制O(n)
     * @param chars
     * @return
     */
    public static boolean isUnique(char[] chars){
        try {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < chars.length; i ++){
                if(map.containsKey(chars[i])){
                    return false;
                }else{
                    map.put(chars[i], 1);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 方法二：在空间限制为O(1)的情况下实现复杂度尽量低的方式
     * 使用堆排序能够做到空间O（1）时间O（nlogn）
     * @param chars
     * @return
     */
    public static boolean isUnique2(char[] chars){
        try {
            // 获取长度
            int len = chars.length;
            // 构建最值堆(len-2)/2) 为数组组成的堆中的最后一行的最左边元素
            boolean isUnique = true;
            // 从孩子节点开始往上遍历进行堆排序，for循环之后产生最大值到根节点
            for (int i = (len-2)/2; i >= 1; i --){
                isUnique = heapTop(chars, i, len - 1);
            }
            return isUnique;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将以parent为根节点的小堆进行堆排序
     * 比较parent的两个孩子是否比自己大，如果比自己大，则往下走
     * @param chars
     * @param parent
     * @param len
     */
    public static boolean heapTop(char[] chars, int parent, int len){
        // 存储当前节点
        char temp = chars[parent];
        // 这里的孩子节点为2n+1
        int child = parent * 2 + 1;
        // 这里循环往下比较是否需要将parent下沉
        while (child <= len){
            // 首先判断右边是否比左边大，选出最大的孩子
            if(chars[child] == chars[child + 1]){
                // 相等了，返回false
                return false;
            }else if(chars[child] < chars[child + 1]){
                // 右边大一点，将右边的设置为最大孩子
                child = child+1;
            }
            // 判断是否大于parent
            if(chars[parent] == chars[child]){
                return false;
            }else if(chars[parent] > chars[child]){
                // parent大于child不需要接下来的步骤了
                break;
            }else {
                // parent需要下沉
                chars[parent] = chars[child];
                // parent节点更新为child
                parent = child;
                // child继续遍历到新的parent的child
                child = 2 * child + 1;
            }
        }
        // 将parent复制给当前的child
        chars[parent] = temp;
        // 到这里说明这次的堆排序没有遇到相同的char
        return true;
    }


}
