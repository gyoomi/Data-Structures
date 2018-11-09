/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/11/9 14:48
 */
public class Main {

    public static void main(String[] args) {
        Dictionary dic = new Dictionary();
        dic.addWord("WordDictionary");
        dic.addWord("addWord");
        dic.addWord("addWord");
        dic.addWord("addWord");
        dic.addWord("search");
        dic.addWord("search");
        dic.addWord("search");
        dic.addWord("search");
        System.out.println(dic.search(""));
        System.out.println(dic.search("bad"));
        System.out.println(dic.search(".ad"));
    }
}
