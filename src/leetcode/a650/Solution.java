package leetcode.a650;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[1] = 0;
        for(int i = 2; i <= n; ++ i) {
            for(int j = 1; j <= i/2; ++ j) {
                if(i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }
        }
        return dp[n];
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