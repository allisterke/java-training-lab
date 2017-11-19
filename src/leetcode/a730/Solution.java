package leetcode.a730;

import java.util.Arrays;
import java.util.List;

public class Solution {
    long[][] dp;
    static long MOD = (long)(1e9+7);

    public int countPalindromicSubsequences(String S) {
        dp = new long[S.length()][S.length()];
        return (int)count(S,0, S.length() - 1) - 1;
    }

    long count(String S, int i, int j) {
        if(dp[i][j] == 0) {
            long total = 1; // empty sub sequence
            for (char c = 'a'; c <= 'd'; ++c) {
                int left = i;
                for (; left <= j && S.charAt(left) != c; ++left) ;
                if (left > j) {
                    continue;
                }
                total += 1; // single character sub sequence
                int right = j;
                for (; right > left && S.charAt(right) != c; --right) ;
                if (left == right) {
                    continue;
                } else if(left + 1 == right) {
                    total += 1;
                } else {
                    total += count(S, left + 1, right - 1);
                }
                total %= MOD;
            }
            dp[i][j] = total;
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "bccb",
                "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"
        );
        List results = Arrays.asList(
                6,
                104860361
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.countPalindromicSubsequences(tests.get(i)));
        }
    }
}