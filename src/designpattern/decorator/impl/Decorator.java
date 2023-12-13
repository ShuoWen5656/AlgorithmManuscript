package designpattern.decorator.impl;

import designpattern.decorator.Component;

/**
 * @author swzhao
 * @data 2023/2/16 20:58
 * @Discreption <> 装饰者核心类，
 */
public class Decorator implements Component {

    /**
     * 入参
     */
    private Component component;


    /**
     * 接收一个component,也就是具体的房子交给装饰者类
     * @param component
     */
    public Decorator(Component component) {
        this.component = component;
    }

    /**
     * 这里的重写不是实现自己的逻辑，而是帮component做了代理让外界调用op方法的时候像调用实际的op一般
     */
    @Override
    public void operation() {
        component.operation();
    }

}
