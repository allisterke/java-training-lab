package leetcode.a746;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] f = new int[cost.length + 1];
        for(int i = 0; i <= cost.length; ++ i) {
            if(i == 0 || i == 1) {
                f[i] = 0;
            }
            else {
                f[i] = Math.min(f[i-1] + cost[i-1], f[i-2] + cost[i-2]);
            }
        }
        return f[cost.length];
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {10, 15, 20},
                        {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}
                }
        );
        List<Integer> results = Arrays.asList(
                15,
                6
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minCostClimbingStairs(tests.get(i)));
        }
    }
}