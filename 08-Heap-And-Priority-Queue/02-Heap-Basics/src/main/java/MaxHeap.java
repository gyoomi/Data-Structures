/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * 二叉堆：最大堆
 *
 * @author Leon
 * @version 2018/11/5 15:37
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }
    public MaxHeap() {
        data = new Array<>();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的父亲节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Index 0 does't exist parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的左孩子的节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树数组表示中，一个索引所表示的右
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }
}
