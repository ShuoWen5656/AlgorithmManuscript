package classespackage.stringproblem;

import dataConstruct.TrieTreeNode;

/**
 * @author swzhao
 * @data 2023/1/2 11:33
 * @Discreption <> 字典树（前缀树）的实现 二轮测试
 */
public class TrieTreeCp {



    TrieTreeNode root;


    public TrieTreeCp() {
        root = new TrieTreeNode(0, 0, new TrieTreeNode[26]);
    }


    /**
     * 插入新字符串
     * @param newStr
     */
    public void insert(String newStr) {
        if (newStr == null) {
            return;
        }
        char[] chars = newStr.toCharArray();
        TrieTreeNode cur = this.root;
        // 锁对象实例即可，不影响其他对象
        synchronized (this) {
            for (char c : chars) {
                int index = c - 'a';
                if (cur.getMap()[index] == null) {
                    cur.getMap()[index] = new TrieTreeNode(0, 0, new TrieTreeNode[26]);
                }
                // 经过当前位置
                TrieTreeNode treeNode = cur.getMap()[index];
                treeNode.setPath(treeNode.getPath()+1);
                cur = treeNode;
            }
            // 结束之后需要将end增加
            cur.setEnd(cur.getEnd() + 1);
        }
    }


    /**
     * 删除字符串
     * @param str
     */
    public void delete(String str) {
        if (!contains(str)) {
            return;
        }
        char[] chars = str.toCharArray();
        TrieTreeNode cur = this.root;
        // 说明存在，沿途减数量即可
        synchronized (this) {
            for (char c : chars) {
                int index = c - 'a';
                // 经过当前位置
                TrieTreeNode treeNode = cur.getMap()[index];
                treeNode.setPath(treeNode.getPath()-1);
                cur = treeNode;
            }
            // 结束之后需要将end减掉1
            cur.setEnd(cur.getEnd() - 1);
        }
    }


    /**
     * 判断是否存在该字符串
     * @param str
     * @return
     */
    public boolean contains(String str) {
        if (str == null) {
            return true;
        }
        char[] chars = str.toCharArray();
        TrieTreeNode cur = this.root;
        // 只读不需要加锁
        for (char c : chars) {
            int index = c - 'a';
            if (cur.getMap()[index] == null || cur.getMap()[index].getPath() == 0) {
                // 不存在该节点或者经过该节点的字母都被删掉了
                return false;
            }else {
                // 当前位置存在,继续检查下一个位置
                cur = cur.getMap()[index];
            }
        }
        // 全部检查完了也不算存在，还要检查一下end是否大于0，也就是是否存在以cur为结尾的单次
        if (cur.getEnd() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 返回以string为前缀的单词数量
     * @param str
     * @return
     */
    public int preFixNum(String str) {
        if (str == null) {
            return 0;
        }
        char[] chars = str.toCharArray();
        TrieTreeNode cur = this.root;
        // 返回经过最后一个单词的path数量即可
        for (char c : chars) {
            int index = c - 'a';
            if (cur.getMap()[index] == null || cur.getMap()[index].getPath() == 0) {
                // 没有存储当前单词
                return 0;
            }else {
                // 当前位置存在,继续检查下一个位置
                cur = cur.getMap()[index];
            }
        }
        // 结束之后返回path
        return cur.getPath();
    }


    public static void main(String[] args) {
        TrieTreeCp trieTreeCp = new TrieTreeCp();
        trieTreeCp.insert("abc");
        trieTreeCp.insert("abcd");
        trieTreeCp.insert("gkd");
        trieTreeCp.insert("yyds");
        System.out.println(trieTreeCp.contains("yyds"));
        trieTreeCp.delete("yyds");
        System.out.println(trieTreeCp.contains("yyds"));
        System.out.println(trieTreeCp.preFixNum("yy"));
        System.out.println(trieTreeCp.preFixNum("ab"));
        trieTreeCp.insert("yyds");
        trieTreeCp.insert("yy");
        trieTreeCp.insert("yyds");
        trieTreeCp.insert("yyds");
        trieTreeCp.insert("yyds");
        trieTreeCp.insert("yyds");
        System.out.println(trieTreeCp.preFixNum("yy"));
    }






}
