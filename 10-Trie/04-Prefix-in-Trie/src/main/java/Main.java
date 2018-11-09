/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/11/8 15:36
 */
public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("hello");
        trie.add("hi");
        trie.add("pan");
        trie.add("panda");
        trie.add("door");
        trie.add("deer");
        System.out.println(trie.startsWith("pal"));
    }
}
