package dataConstruct;

/**
 * @author swzhao
 * @date 2022/3/28 4:27 下午
 * @Discreption <>记录公共类，用来封装信息的
 */
public class Record {

    private int lv;

    private int rv;


    public Record(int lv, int rv) {
        this.lv = lv;
        this.rv = rv;
    }


    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getRv() {
        return rv;
    }

    public void setRv(int rv) {
        this.rv = rv;
    }
}
