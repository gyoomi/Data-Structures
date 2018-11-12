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
 * @version 2018/11/12 11:31
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
        int size = 100000;
        int m = 1000000000;

        UnionFind uf = new UnionFind(size);
        System.out.println("UnionFind : " + testUF(uf, m) + " s");

    }
}
