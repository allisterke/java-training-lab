package leetcode.a714;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxProfit(int[] prices, int fee) {
        int[] profit = new int[prices.length];
        int max = - prices[0] - fee;
        for (int i = 1; i < prices.length; ++ i) {
            profit[i] = Math.max(profit[i-1], prices[i] + max);
            max = Math.max(max, profit[i-1] - prices[i] - fee);
        }
        return profit[profit.length - 1];
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 3, 2, 8, 4, 9}
                }
        );
        List<Integer> fees = Arrays.asList(
                2
        );
        List<Integer> results = Arrays.asList(
                8
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maxProfit(tests.get(i), fees.get(i)));
        }
    }
}