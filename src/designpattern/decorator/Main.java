package designpattern.decorator;

import designpattern.decorator.impl.*;

/**
 * @author swzhao
 * @data 2023/2/16 21:07
 * @Discreption <>
 */
public class Main {


    /**
     * 测试装饰者模式
     * @param args
     */
    public static void main(String[] args) {
        // 建造一个房子
        ConcreteComponent concreteComponent = new ConcreteComponent();
        // 装饰房子,返回的不是装饰类，而是具有op功能的component
        Component decorator = new Decorator(concreteComponent);
        // 装饰一个茶几A
        Component concreteDecoratorA = new ConcreteDecoratorA(concreteComponent);
        // 在A的基础上增加一个沙发C
        ConcreteDecoratorC concreteDecoratorC = new ConcreteDecoratorC(concreteDecoratorA);
        // 在基本基础上增加一个茶几B
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(concreteComponent);
        // 总的来说，基础就是对象，建造出一个基本对象，每装饰一次就会生成一个新的对象，新的对象可以调用op方法
        // 基本属性
        decorator.operation();
        // 有茶几A
        concreteDecoratorA.operation();
        // 有茶几A和沙发C
        concreteDecoratorC.operation();
    }
}
