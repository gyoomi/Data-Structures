/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

import java.util.TreeSet;

/**
 * LeetCode 804号问题
 *
 * @author Leon
 * @version 2018/10/31 14:58
 */
public class Solution {

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                String msCode = codes[word.charAt(i) - 97];
                sb.append(msCode);
            }
            set.add(String.valueOf(sb));
        }
        return set.size();
    }

}
