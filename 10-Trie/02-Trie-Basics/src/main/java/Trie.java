/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

import java.util.TreeMap;

/**
 * 字典树
 *
 * @author Leon
 * @version 2018/11/8 15:36
 */
public class Trie {

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }


    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node() {
            this(false);
        }
    }

}
