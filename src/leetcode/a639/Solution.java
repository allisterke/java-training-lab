package leetcode.a639;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numDecodings(String s) {
        long MOD = (long)(1e9 + 7);
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        for(int i = 0; i < s.length(); ++ i) {
            if(s.charAt(i) == '*') {
                dp[i+1] += dp[i] * 9;
                if(i > 0) {
                    char c = s.charAt(i-1);
                    if(c == '*') {
                        dp[i+1] += dp[i-1] * 15;
                    }
                    else if(c == '1') {
                        dp[i+1] += dp[i-1] * 9;
                    }
                    else if(c == '2') {
                        dp[i+1] += dp[i-1] * 6;
                    }
                }
            }
            else {
                if(s.charAt(i) != '0') {
                    dp[i + 1] += dp[i];
                }
                if(i > 0) {
                    char c = s.charAt(i-1);
                    if(c == '*') {
                        dp[i+1] += dp[i-1] * (s.charAt(i) <= '6' ? 2 : 1);
                    }
                    else if(c != '0') {
                        int n = (s.charAt(i-1) - '0') * 10 + (s.charAt(i) - '0');
                        if(n <= 26) {
                            dp[i+1] += dp[i-1];
                        }
                    }
                }
            }
            dp[i+1] %= MOD;
        }
        return (int)dp[dp.length - 1];
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "**"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.numDecodings(tests.get(i)));
        }
    }
}