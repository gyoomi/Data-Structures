/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/11/10 17:51
 */
public class UnionFind implements UF {

    private int[] parent; // parent[i]表示，第i个元素所指向的父节点
    private int[] rank; // rank[i]表示，以第i个元素为根的树的层数
    // 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不在是树的层数值
    // 这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        // 初始化时，每个parent[i]指向自己。表示每个元素自己成一个集合。
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查看元素p和元素q是否所属一个集合
     * 时空复杂度:O(1)
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 查找过程：查找p元素对应的集合编号
     * O（h）  h为树的高度
     *
     *
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        // 不断向上查找父亲节点，直到找到根节点
        // 父节点的特性：p == parent[p]
        while (p != parent[p]) {
            // 查找时，进行路径压缩，把孙子节点的那些进行直接指到根节点
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    /**
     * 合并元素p和元素q所属的集合
     * O(n) 复杂度
     *
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 根据需要合并的两个不同的树的数量，来判断合并的方向
        // 将元素层数少的集合合并到元素层数多的集合上
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) { // sz[pRoot] <= sz[qRoot]
            parent[qRoot] = pRoot;
        } else { // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1; // 此时，被指向的节点的层数加1
        }
    }
}
