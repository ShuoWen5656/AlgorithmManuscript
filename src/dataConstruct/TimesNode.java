package dataConstruct;

/**
 * @author swzhao
 * @data 2022/7/29 21:34
 * @Discreption <> 计数node
 */
public class TimesNode {

    private String str;

    private Integer times;


    public TimesNode(String str, Integer times) {
        this.str = str;
        this.times = times;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
