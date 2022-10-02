package dataConstruct;

/**
 * @author swzhao
 * @date 2022/4/12 9:59 上午
 * @Discreption <>查询类，包含两个树节点
 * 一个query的实例表示o1和o2的最近祖先的查询
 */
public class Query {

    MyTreeNode o1;
    MyTreeNode o2;


    public Query(MyTreeNode o1, MyTreeNode o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public MyTreeNode getO1() {
        return o1;
    }

    public void setO1(MyTreeNode o1) {
        this.o1 = o1;
    }

    public MyTreeNode getO2() {
        return o2;
    }

    public void setO2(MyTreeNode o2) {
        this.o2 = o2;
    }
}
