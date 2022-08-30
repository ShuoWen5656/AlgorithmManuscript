package dataConstruct;

/**
 * @author swzhao
 * @date 2021/12/18 8:54 下午
 * @Discreption <>公共数据结构：单链表数据结构
 */
public class LinkNode{

    private Integer value;

    private LinkNode next;

    private LinkNode radomNode;

    public LinkNode(Integer value) {
        this.value = value;
    }

    public LinkNode(Integer value, LinkNode next) {
        this.value = value;
        this.next = next;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public LinkNode getRadomNode() {
        return radomNode;
    }

    public void setRadomNode(LinkNode radomNode) {
        this.radomNode = radomNode;
    }


    //@Override
    //public String toString() {
    //    return "LinkNode{" +
    //            "value=" + value +
    //            ", next=" + next +
    //            ", radomNode=" + radomNode +
    //            '}';
    //}
}
