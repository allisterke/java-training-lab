package leetcode.a664;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int strangePrinter(String s) {
        if(s.isEmpty()) {
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];
        for(int x = 0; x < s.length(); ++ x) {
            for(int i = 0; i + x < s.length(); ++ i) {
                int j = i + x;
                if(x == 0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = i; k < j; ++ k) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
                    }
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
                    }
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "ab",
                "tbgtgb",
                "cdcaadbcdddbcadbcaabbcbacadbbbaadccbbc"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.strangePrinter(tests.get(i)));
        }
    }
}