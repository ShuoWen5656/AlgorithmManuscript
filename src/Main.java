import classespackage.MyStack1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        MyStack1 myStack1 = new MyStack1();
        myStack1.push(2);
        myStack1.push(1);
        myStack1.push(5);
        myStack1.push(3);

        System.out.println(myStack1.toString());
        System.out.println(myStack1.getmin());

//        Map<String, String> map = new  HashMap<>();
//        map.put("1", "hehe");
//
//        System.out.println(map.toString());
    }
}
