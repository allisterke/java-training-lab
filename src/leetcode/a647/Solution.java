package leetcode.a647;

import java.util.Arrays;
import java.util.List;

public class Solution {
    int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        for(int k = 0; k < s.length(); ++ k) {
            for(int i = 0; i + k < s.length(); ++ i) {
                if(k == 0) {
                    dp[i][i+k] = true;
                }
                else if(k == 1) {
                    dp[i][i+k] = s.charAt(i) == s.charAt(i+k);
                }
                else {
                    dp[i][i+k] = dp[i+1][i+k-1] && s.charAt(i) == s.charAt(i+k);
                }
                if(dp[i][i+k]) {
                    ++ count;
                }
            }
        }
        return count;
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