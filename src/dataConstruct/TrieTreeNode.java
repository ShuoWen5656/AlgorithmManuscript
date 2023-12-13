package dataConstruct;

/**
 * @author swzhao
 * @data 2022/5/23 19:46
 * @Discreption <> 前缀树（字典树）结构
 */
public class TrieTreeNode {

    /**
     * 有多少个字符串公用这个节点
     */
    private int path;
    /**
     * 有多少个字符串以这个节点结尾
     */
    private int end;
    /**
     * 当前节点下面挂载了多少条路径
     */
    private TrieTreeNode[] map;

    public TrieTreeNode(int path, int end, TrieTreeNode[] map) {
        this.path = path;
        this.end = end;
        this.map = map;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public TrieTreeNode[] getMap() {
        return map;
    }

    public void setMap(TrieTreeNode[] map) {
        this.map = map;
    }
}
