/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/11/10 20:26
 */
public class Main {

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.unionElements(1, 2);
        uf.unionElements(2, 8);
    }
}
