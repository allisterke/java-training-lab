package leetcode.a790;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numTilings(int N) {
        long MOD = (long)(1e9 + 7);
        long[] dp = new long[N+1];
        dp[0] = 1;
        for (int i = 1; i <= N; ++ i) {
            dp[i] = dp[i-1];
            if(i >= 2) {
                dp[i] += dp[i-2];
            }
            for(int j = i-3; j >= 0; -- j) {
                dp[i] += dp[j] * 2;
            }
            dp[i] %= MOD;
        }
        return (int)dp[N];
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