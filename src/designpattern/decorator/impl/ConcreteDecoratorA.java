package designpattern.decorator.impl;

import designpattern.decorator.Component;

/**
 * @author swzhao
 * @data 2023/2/16 21:01
 * @Discreption <> 装饰者A，给房子装一个茶几A
 */
public class ConcreteDecoratorA extends Decorator {



    /**
     * 接收一个component,也就是具体的房子交给装饰者类
     *
     * @param component
     */
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        // 居住属性
        super.operation();
        // 放置茶几A,通过这种方式，在实现时，执行了基本功能的同时还可以执行新的逻辑，即装饰模式
        opA();
    }

    /**
     * 放置茶几A
     */
    private void opA() {

    }

}
