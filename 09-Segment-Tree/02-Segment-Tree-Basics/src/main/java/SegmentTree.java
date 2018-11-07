/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * 线段树
 *
 * @author Leon
 * @version 2018/11/7 10:44
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;

    public SegmentTree(E[] arr) {
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[])new Object[4 * arr.length];
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    /**
     * 返回完全二叉树数组表示中，一个索引所在元素的左孩子的索引
     *
     * @param index
     * @return
     */
    public int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树数组表示中，一个索引所在元素的右孩子的索引
     *
     * @param index
     * @return
     */
    public int rightChild(int index) {
        return index * 2 + 2;
    }
}
