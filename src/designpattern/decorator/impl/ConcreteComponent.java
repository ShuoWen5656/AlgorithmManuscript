package designpattern.decorator.impl;

import designpattern.decorator.Component;

/**
 * @author swzhao
 * @data 2023/2/16 20:57
 * @Discreption <>  具有实际实现的component类型，相当于图纸变成了房子
 */
public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        // 居住属性
    }
}
