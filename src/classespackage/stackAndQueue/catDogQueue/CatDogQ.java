package classespackage.stackAndQueue.catDogQueue;

/**
 * @author swzhao
 * @date 2021/11/17 9:46 下午
 * @Discreption <>猫狗封装类：由于猫狗类不可以修改，因此外部再添加一个变量
 */
public class CatDogQ {
    /**
     * 时间戳，相当于当前实例进来的时间
     */
    private Integer count;

    private Pet p;

    public CatDogQ(Integer count, Pet p) {
        this.count = count;
        this.p = p;
    }

    public Integer getCount() {
        return count;
    }

    public Pet getP() {
        return p;
    }

    /**
     * 获取类型
     * @return
     */
    public String getType(){
        return p.getType();
    }

    @Override
    public String toString() {
        return "CatDogQ{" +
                "count=" + count +
                ", p=" + p +
                '}';
    }
}
