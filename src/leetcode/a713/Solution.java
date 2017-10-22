package leetcode.a713;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int product = 1;
        int count = 0;
        for(int i = 0, j = 0, tp; i < nums.length; ++ i) {
            for (j = Math.max(i, j); j < nums.length && (tp = product * nums[j]) < k; ++ j) {
                product = tp;
            }
            count += j - i;
            product /= nums[i];
        }
        return count;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {10, 5, 2, 6},
                        {1, 2, 3}
                }
        );
        List<Integer> ks = Arrays.asList(
                100,
                0
        );
        List<Integer> results = Arrays.asList(
                8,
                0
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.numSubarrayProductLessThanK(tests.get(i), ks.get(i)));
        }
    }
}