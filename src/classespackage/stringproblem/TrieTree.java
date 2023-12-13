package classespackage.stringproblem;

import dataConstruct.TrieTreeNode;

/**
 * @author swzhao
 * @data 2022/5/23 19:48
 * @Discreption <> 字典树（前缀树）的实现
 */
public class TrieTree {

    private TrieTreeNode root;

    public TrieTree() {
        this.root = new TrieTreeNode(0 ,0, new TrieTreeNode[26]);
    }


    /**
     * 插入一个单词
     * @param word
     */
    public void insert(String word){
        if (word == null || word.length() == 0){
            return;
        }
        TrieTreeNode[] map = root.getMap();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++){
            char charCur = chars[i];
            int index = charCur - 'a';
            TrieTreeNode treeCur = map[index];
            if(treeCur == null){
                // 说明没有查到对应node，应该新建一个
                // 先默认不是结尾节点
                map[index] = new TrieTreeNode(1, 0, new TrieTreeNode[26]);
            }else {
                // 说明有节点，path++
                treeCur.setPath(treeCur.getPath() + 1);
            }
            // 判断是否是结尾节点，结尾节点end++
            if(i == chars.length-1){
                treeCur.setEnd(treeCur.getEnd()+1);
            }
        }
    }

    /**
     * 删除一个单词
     * @param word
     */
    public void delete(String word){
        if (word == null || word.length() == 0){
            return;
        }
        TrieTreeNode[] map = root.getMap();
        char[] chars = word.toCharArray();
        if(search(word)){
            // 能查到再删除
            for (int i = 0; i < chars.length; i++){
                char charCur = chars[i];
                int index = charCur - 'a';
                TrieTreeNode treeCur = map[index];
                // 说明有节点，path++
                treeCur.setPath(treeCur.getPath() - 1);
                // 判断是否是结尾节点，结尾节点end-1
                if(i == chars.length-1){
                    treeCur.setEnd(treeCur.getEnd()-1);
                }
            }
        }
    }


    /**
     * 判断当前词是否在树里面
     * @param word
     * @return
     */
    public boolean search(String word){
        if (word == null || word.length() == 0){
            return false;
        }
        TrieTreeNode[] map = root.getMap();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++){
            char charCur = chars[i];
            int index = charCur - 'a';
            TrieTreeNode treeCur = map[index];
            if(treeCur == null){
                // 说明没有查到对应node，返回false
                return false;
            }else {
                // 判断是否结尾节点
                if(i == chars.length-1){
                    // 判断当前树节点是否存在以该节点为节点的string
                    return treeCur.getEnd()>0;
                }
            }
        }
        return false;
    }

    /**
     * 获取以当前word为前缀的str有多少个
     * @param word
     * @return
     */
    public int prefixNumber(String word){
        if (word == null || word.length() == 0){
            return 0;
        }
        TrieTreeNode[] map = root.getMap();
        char[] chars = word.toCharArray();
        if(search(word)){
            for (int i = 0; i < chars.length; i++){
                char charCur = chars[i];
                int index = charCur - 'a';
                TrieTreeNode treeCur = map[index];
                    // 判断是否结尾节点
                if(i == chars.length-1){
                    return treeCur.getPath();
                }
            }
        }
        return 0;
    }


    public TrieTreeNode getTrieTreeNode() {
        return root;
    }
}
