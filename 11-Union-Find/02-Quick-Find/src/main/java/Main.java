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
 * @version 2018/11/10 18:31
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

        double time = (endTime - startTime) / 1000000000.0;
        return time;
    }

    public static void main(String[] args) {

        int size = 10;
        int m = 9;
        UnionFind uf = new UnionFind(size);
        System.out.println(testUF(uf, m) + " s");

    }
}
