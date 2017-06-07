package leetcode.a376;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int wiggleMaxLength(int[] nums) {
        int[][] dp = new int[2][];
        for(int i = 0; i < dp.length; ++ i) {
            dp[i] = new int[nums.length];
        }
        int max = 0;
        for(int i = 0; i < nums.length; ++ i) {
            dp[0][i] = 0;
            for(int j = 0; j < i; ++ j) {
                if(nums[j] < nums[i] && dp[1][j] > dp[0][i]) {
                    dp[0][i] = dp[1][j];
                }
            }
            ++ dp[0][i];
            dp[1][i] = 0;
            for(int j = 0; j < i; ++ j) {
                if(nums[j] > nums[i] && dp[0][j] > dp[1][i]) {
                    dp[1][i] = dp[0][j];
                }
            }
            ++ dp[1][i];
            max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
        }
        return max;
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