package leetcode.a629;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int kInversePairs(int n, int k) {
        long MOD = (long)(1e9 + 7);
        long[][] dp = new long[n+1][k+1];
//        dp[1][0] = 1;
//        dp[2][0] = 1;
//        dp[2][1] = 1;
        for(int i = 1; i <= n; ++ i) {
            long[] acc = new long[k+2];
            for(int q = 1; q < acc.length; ++ q) {
                acc[q] = acc[q-1] + dp[i-1][q-1];
            }
            for(int j = 0; j <= k; ++ j) {
                if(j == 0) {
                    dp[i][j] = 1;
                }
                else if(i != 0) {
//                    for(int p = Math.max(j-i+1, 0); p <= j; ++ p) {
//                        dp[i][j] += dp[i-1][p];
//                        dp[i][j] %= MOD;
//                    }
                    dp[i][j] = (acc[j+1] - acc[Math.max(j-i+1, 0)]) % MOD;
                }
            }
        }
        return (int)dp[n][k];
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(1000, 1),
                Arrays.asList(3, 0),
                Arrays.asList(3, 2),
                Arrays.asList(3, 1),
                Arrays.asList(2, 2),
                Arrays.asList(10, 5)
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.kInversePairs(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}