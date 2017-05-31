package leetcode.a213;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int rob(int[] nums) {
        if(nums.length < 2) {
            return nums.length == 0 ? 0 : nums[0];
        }
        return Math.max(
                maxRobbery(nums, 0, nums.length - 1),
                maxRobbery(nums, 1, nums.length));
    }

    int maxRobbery(int[] nums, int begin, int end) {
        int f0 = 0, f1 = 0, f2 = 0;
        for(int i = begin; i < end; ++ i) {
            f2 = Math.max(f1, f0 + nums[i]);
            f0 = f1;
            f1 = f2;
        }
        return f1;
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