package leetcode.a674;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        for(int i = 0; i < nums.length; ++ i) {
            if(i == 0 || nums[i] > nums[i-1]) {
                dp[i+1] = dp[i] + 1;
            }
            else {
                dp[i+1] = 1;
            }
        }
        return Arrays.stream(dp).max().getAsInt();
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