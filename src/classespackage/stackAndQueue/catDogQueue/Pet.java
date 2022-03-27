package classespackage.stackAndQueue.catDogQueue;

/**
 * @author swzhao
 * @date 2021/11/17 9:43 下午
 * @Discreption <> 动物父类
 */
public class Pet {
    /**
     * 动物类型
     */
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
