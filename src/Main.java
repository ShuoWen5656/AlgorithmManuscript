import classespackage.HannioTower;
import classespackage.MaxWindow;
import classespackage.MyStack1;
import classespackage.MyStack2;

import java.util.*;

public class Main {

    public static void main(String[] args) {


        try{
//            List<Integer> integers = Arrays.asList(2, 1, 5, 3, 9, 0);


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
//            MyStack2 myStack2 = new MyStack2();
//            for (Integer integer : integers){
//                myStack2.push(integer);
//            }
//            System.out.println(myStack2.toString());
//            System.out.println(myStack2.getMin());


            /**
             * 汉诺塔测试用例
             */
//            HannioTower hannioTower = new HannioTower();
//            int step = hannioTower.hanioProblem(100, "left", "mid", "right", "left", "right");
//            System.out.println("步数:" + step);

            /**
             * 窗口最大值测试用例
             */
            int[] array = {4, 3, 5, 4, 3, 3, 6, 7};
            int[] maxArray = MaxWindow.getMaxWindow(array, 3);
            System.out.println(Arrays.toString(maxArray));
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
