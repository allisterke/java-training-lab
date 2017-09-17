package leetcode.a678;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean checkValidString(String s) {
        if(s.isEmpty()) {
            return true;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int k = 0; k < s.length(); ++ k) {
            for(int i = 0; i + k < s.length(); ++ i) {
                if(k == 0) {
                    dp[i][i+k] = s.charAt(i) == '*';
                }
                else if(k == 1) {
                    dp[i][i+k] = s.charAt(i) == '*' && s.charAt(i+k) != '(' || s.charAt(i) != ')' && s.charAt(i+k) == '*' || s.charAt(i) == '(' && s.charAt(i+k) == ')';
                }
                else {
                    dp[i][i+k] = s.charAt(i) == '*' ?
                            (dp[i+1][i+k] || s.charAt(i+k) != '(' && dp[i+1][i+k-1]) :
                            (s.charAt(i) == '(' ?
                                    ((s.charAt(i+k) == '*' || s.charAt(i+k) == ')') && dp[i+1][i+k-1]) :
                                    false);
                    if(!dp[i][i+k]) {
                        for(int j = i; j < i+k; ++ j) {
                            if(dp[i][j] && dp[j+1][i+k]) {
                                dp[i][i+k] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "()",
                "(*)",
                "((*)",
                "((*()",
                "()()",
                "(*(()))((())())*(**(()))((*)()(()))*(())()(())(()"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.checkValidString(tests.get(i)));
        }
    }
}