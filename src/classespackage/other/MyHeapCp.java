package classespackage.other;

import classespackage.stackAndQueue.catDogQueue.Pet;
import dataConstruct.MyTreeNode;
import dataConstruct.MyTreeNodePlus;

import java.util.Comparator;

/**
 * @author swzhao
 * @data 2023/5/7 8:44
 * @Discreption <>设计一个没有扩容负担的堆结构(以树结构完成堆的调整)
 * 1、getHead 获取堆头
 * 2、getSize 获取堆size
 * 3、add 添加元素
 * 4、popHead 弹出堆头
 */
public class MyHeapCp<V> {

    /**
     * 堆头
     */
    private MyTreeNodePlus<V> head;

    /**
     * 堆尾
     */
    private MyTreeNodePlus<V> last;


    /**
     * 比较器
     */
    private Comparator<V> comparator;


    /**
     * 堆大小
     */
    private int size;

    /**
     * 初始化必须传入比较器
     * @param comparator
     */
    public MyHeapCp(Comparator<V> comparator) {
        this.head = null;
        this.last = null;
        this.comparator = comparator;
        this.size = 0;
    }

    /**
     * 获取堆头的值
     * @return
     */
    public V getHead() {
        return head== null ? null : head.getValue();
    }

    /**
     * 获取堆大小
     * @return
     */
    public int getSize() {
        return size;
    }


    /**
     * 常规：添加一个元素并上浮
     * 1、堆空情况
     * 2、应该挂在哪里的判断
     */
    public void add(V value) {
        MyTreeNodePlus<V> cur = new MyTreeNodePlus<>(value);
        // 堆是否是空的
        if (size == 0) {
            this.head = cur;
            this.last = cur;
            this.size++;
            return;
        }
        // 应该挂带哪
        //1、先判断last是否是根
        if (this.last == this.head) {
            setLeft(this.last, cur);
        }else {
            MyTreeNodePlus<V> parent = this.last.getParent();
            // 判断自己是左边还是右边
            if (parent.getLeft() == this.last) {
                // 如果自己是左边，则直接将cur放到右边
                setRight(parent, cur);
            }else {
                // 自己是右边，则先获取第一个为左子节点的祖先节点,也可能是根节点
                MyTreeNodePlus<V> tmp = this.last;
                while (tmp.getParent() != null && tmp.getParent().getLeft() != tmp) {
                    tmp = tmp.getParent();
                }
                // 如果tmp不是根节点，则获取其parent的右节点为起点，否则就以根节点为起点
                if (tmp.getParent() != null) {
                    tmp = tmp.getParent().getRight();
                }
                while (tmp.getLeft() != null) {
                    tmp = tmp.getLeft();
                }
                setLeft(tmp, cur);
            }
        }
        this.last = cur;
        this.size++;
        // 上浮操作
        heapInsertModify();
    }

    /**
     * 从last开始往上浮动
     */
    private void heapInsertModify() {
        MyTreeNodePlus<V> cur = this.last;
        MyTreeNodePlus<V> parent = this.last.getParent();
        while (cur.getParent() != null && comparator.compare(cur.getValue(), parent.getValue()) > 0) {
            // 交换parent和cur
            swap(parent, cur);
            // 如果cur为last说明是第一波，需要将last一直保持在最后面
            if (cur == this.last) {
                this.last = parent;
            }
            if (parent == this.head) {
                this.head = cur;
            }
            parent = cur.getParent();
        }
    }

    /**
     * 交换树节点
     * @param parent
     * @param cur
     */
    private void swap(MyTreeNodePlus<V> parent, MyTreeNodePlus<V> cur) {
        // 先将牵挂都安置好
        MyTreeNodePlus<V> pp = parent.getParent();
        MyTreeNodePlus<V> pl = parent.getLeft();
        MyTreeNodePlus<V> pr = parent.getRight();
        MyTreeNodePlus<V> curLeft = cur.getLeft();
        MyTreeNodePlus<V> curRight = cur.getRight();
        // 开始交换,看似复杂，实则三步走，面向cur
        // 1、cur接替parent，当儿子
        cur.setParent(pp);
        if (pp != null) {
            if (pp.getRight() == parent){
                pp.setRight(cur);
            }else {
                pp.setLeft(cur);
            }
        }
        // 2、cur当parent当儿子并接管parent的另一个儿子
        if (cur == pl) {
            setLeft(cur, parent);
            setRight(cur, pr);
        }else {
            setRight(cur, parent);
            setLeft(cur, pl);
        }
        // 3、让parent接管cur自己的儿子
        setLeft(parent, curLeft);
        setRight(parent, curRight);
    }

    /**
     * 将right节点设置为parent的右节点
     * @param parent
     * @param right
     */
    private void setRight(MyTreeNodePlus<V> parent, MyTreeNodePlus<V> right) {
        if (parent == null) {
            return;
        }else if (right == null) {
            parent.setRight(right);
        }else {
            parent.setRight(right);
            right.setParent(parent);
        }
    }

    /**
     * 由于当前树节点结构比较复杂，这里使用了函数进行封装，对外不暴露细节避免指针设置出错
     * @param parent
     * @param left
     */
    private void setLeft(MyTreeNodePlus<V> parent, MyTreeNodePlus<V> left) {
        if (parent == null) {
            return;
        }else if (left == null) {
            parent.setLeft(left);
        }else {
            parent.setLeft(left);
            left.setParent(parent);
        }
    }


    /**
     * 弹出堆头并重新下沉
     */
    public V popHead() {
        if (this.size == 0) {
            return null;
        }else if (this.size == 1) {
            V value = this.head.getValue();
            this.head = null;
            this.last = null;
            this.size = 0;
            return value;
        }else {
            // 首先元素大于1个
            V value = this.head.getValue();
            // 找到上一个last节点位置
            MyTreeNodePlus<V> lLast = getlLast();
            // 先将last剔出来
            if (last == last.getParent().getLeft()) {
                last.getParent().setLeft(null);
            }else {
                last.getParent().setRight(null);
            }
            last.setParent(null);
            // 将last替换head
            MyTreeNodePlus<V> hleft = this.head.getLeft();
            MyTreeNodePlus<V> hright = this.head.getRight();
            this.head.setRight(null);
            this.head.setLeft(null);
            this.last.setLeft(hleft);
            this.last.setRight(hright);
            this.head = this.last;
            this.last = lLast;
            // 交换完成后，进行一次下沉动作
            heapify();
            size--;
            return value;
        }
    }

    /**
     * 从头结点开始下沉
     */
    private void heapify() {
        MyTreeNodePlus<V> cur = this.head;
        MyTreeNodePlus<V> curLeft = cur.getLeft();
        while (curLeft != null) {
            MyTreeNodePlus<V> swapNode = comparator.compare(curLeft.getValue(), cur.getValue()) > 0 ? curLeft : cur;
            // 判断右节点
            if (cur.getRight() != null && comparator.compare(cur.getRight().getValue(), swapNode.getValue()) > 0) {
                swapNode = cur.getRight();
            }
            if (cur != swapNode) {
                swap(cur, swapNode);
            }
            // 如果交换的是last，需要将last一直保持在最后一个位置
            if (swapNode == this.last) {
                this.last = cur;
            }
            if (cur == this.head) {
                this.head = swapNode;
            }
            curLeft = cur.getLeft();
        }
    }

    /**
     * 找到last的上一个节点
     * @return
     */
    private MyTreeNodePlus<V> getlLast() {
        MyTreeNodePlus<V> cur = this.last;
        // cur是右节点，那么左兄弟一定是上一个last
        MyTreeNodePlus<V> parent = cur.getParent();
        if (parent.getRight() == cur) {
            return parent.getLeft();
        }
        // 否则的话找到根节点或者第一个作为左子树的祖先节点
        while (cur.getParent() != null && cur != cur.getParent().getRight()) {
            cur = cur.getParent();
        }
        if (cur.getParent() != null) {
            cur = cur.getParent().getLeft();
        }
        while (cur.getRight() != null) {
            cur = cur.getRight();
        }
        return cur;
    }


    public static void main(String[] args) {
        // 构建大根堆
        MyHeapCp<Integer> integerMyHeapCp = new MyHeapCp<>(((o1, o2) -> {
            if (o1 - o2 > 0) {
                return 1;
            } else if (o1 - o2 == 0) {
                return 0;
            } else {
                return -1;
            }
        }));


        integerMyHeapCp.getSize();
        integerMyHeapCp.getHead();
        integerMyHeapCp.add(1);
        integerMyHeapCp.add(5);
        integerMyHeapCp.add(3);
        integerMyHeapCp.add(9);
        integerMyHeapCp.add(0);

        integerMyHeapCp.popHead();
    }




}
