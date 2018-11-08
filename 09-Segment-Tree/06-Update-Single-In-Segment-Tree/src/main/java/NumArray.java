/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/11/8 11:21
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        Integer[] data = new Integer[nums.length];
        if (nums.length > 0) {
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public void update(int i, int val) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("The segment tree is null,Must initial before using.");
        }
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("The segment tree is null,Must initial before using.");
        }
        return segmentTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        NumArray na = new NumArray(nums);
        System.out.println(na.sumRange(0, 2));
        na.update(1, 2);
        System.out.println(na.sumRange(0, 2));
    }
}
