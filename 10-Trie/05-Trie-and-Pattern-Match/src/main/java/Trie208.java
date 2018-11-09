/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

import java.util.TreeMap;

/**
 * Leetcode-208
 *
 * @author Leon
 * @version 2018/11/9 10:34
 */
public class Trie208 {


    private Node root;
    private int size;

    public Trie208() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
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
