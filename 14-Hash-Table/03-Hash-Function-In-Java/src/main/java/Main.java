/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/21 13:56
 */
public class Main {

    public static void main(String[] args) {
        Student s1 = new Student(1, 2, "leon", "keh");
        Student s2 = new Student(1, 2, "leon", "keh");
        System.out.println(s1.equals(s2));

    }
}
