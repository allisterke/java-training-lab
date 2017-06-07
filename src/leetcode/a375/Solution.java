package leetcode.a375;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][];
        for(int i = 0; i <= n; ++ i) {
            dp[i] = new int[n+1];
            Arrays.fill(dp[i], -1);
        }
        return backtrace(1, n, dp);
    }

    int backtrace(int begin, int end, int[][] dp) {
        if(begin >= end) {
            return 0;
        }
        else {
            if(dp[begin][end] == -1) {
                int min = Integer.MAX_VALUE;
                for (int i = begin; i <= end; ++i) {
                    min = Math.min(min,
                            i + Math.max(backtrace(begin, i - 1, dp), backtrace(i + 1, end, dp)));
                }
                dp[begin][end] = min;
            }
            return dp[begin][end];
        }
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