package leetcode.a221;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maximalSquare(char[][] matrix) {
        int maximum = 0;
        int[][] dp = new int[matrix.length][];
        for(int i = 0; i < matrix.length; ++ i) {
            dp[i] = new int[matrix[i].length];
        }
        for(int i = 0; i < matrix.length; ++ i) {
            for(int j = 0; j < matrix[i].length; ++ j) {
                if(matrix[i][j] == '0') {
                    continue;
                }
                if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
                maximum = Math.max(maximum, dp[i][j]);
            }
        }
        return maximum * maximum;
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