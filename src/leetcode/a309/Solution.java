package leetcode.a309;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int p1 = 0, p2 = 0;
        int cache = -prices[0];
        for(int i = 1; i < prices.length; ++ i) {
            int p3 = Math.max(p2, prices[i] + cache);
            cache = Math.max(cache, -prices[i] + p1);
            p1 = p2;
            p2 = p3;
        }
        return p2;
    }
    
    public int maxProfit0(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int[] profit = new int[prices.length+2];
        int cache = -prices[0];
        for(int i = 1; i < prices.length; ++ i) {
            profit[i+2] = Math.max(profit[i+1], prices[i] + cache);
            cache = Math.max(cache, -prices[i] + profit[i]);
        }
        return profit[profit.length - 1];
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}