import classespackage.MyStack1;
import classespackage.MyStack2;

import java.util.*;

public class Main {

    public static void main(String[] args) {


        try{
            List<Integer> integers = Arrays.asList(2, 1, 5, 3, 9, 0);


            /**
             * MyStack1测试用例
             */
//        MyStack1 myStack1 = new MyStack1();
//        myStack1.push(2);
//        myStack1.push(1);
//        myStack1.push(5);
//        myStack1.push(3);
//        System.out.println(myStack1.toString());
//        System.out.println(myStack1.getmin());

//        Class clazz = MyStack1.class;

            /**
             * MyStack2 测试用例
             */
            MyStack2 myStack2 = new MyStack2();
            for (Integer integer : integers){
                myStack2.push(integer);
            }
            System.out.println(myStack2.toString());
            System.out.println(myStack2.getMin());
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
