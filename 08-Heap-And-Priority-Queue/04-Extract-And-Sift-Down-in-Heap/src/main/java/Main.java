/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

import java.util.Random;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/11/5 15:36
 */
public class Main {

    public static void main(String[] args) {
        int times = 100000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i = 0; i < times; i++) {
            maxHeap.add(new Random().nextInt(Integer.MAX_VALUE));
        }
        System.out.println("add complete!!!");

    }
}
