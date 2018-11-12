/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

import java.util.Random;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/11/10 20:26
 */
public class Main {

    private static double testUF(UF uf, int m){

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();


        for(int i = 0 ; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for(int i = 0 ; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        // UnionFind1 慢于 UnionFind3
//        int size = 100000;
//        int m = 10000;

        // UnionFind3 慢于 UnionFind1, 但UnionFind3最快
        int size = 100000;
        int m = 1000000;

        UnionFind2 uf1 = new UnionFind2(size);
        System.out.println("UnionFind2 : " + testUF(uf1, m) + " s");

        UnionFind3 uf2 = new UnionFind3(size);
        System.out.println("UnionFind3 : " + testUF(uf2, m) + " s");

    }
}
