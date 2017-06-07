package leetcode.a377;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);
        return backtrace(nums, target, dp);
    }

    int backtrace(int[] nums, int target, int[] dp) {
        if(target == 0) {
            return 1;
        }
        if(dp[target] == -1) {
            int sum = 0;
            for (int n : nums) {
                if (target >= n) {
                    sum += backtrace(nums, target - n, dp);
                }
            }
            dp[target] = sum;
        }
        return dp[target];
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