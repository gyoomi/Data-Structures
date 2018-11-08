/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * Leetcode 303 method-2
 *
 * @author Leon
 * @version 2018/11/8 11:39
 */
public class NumArray2 {
    private int[] sum;

    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 4, 1, 2};
        NumArray2 na2 = new NumArray2(nums);
        System.out.println(na2.sumRange(2, 3));
    }
}
