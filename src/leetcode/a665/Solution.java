package leetcode.a665;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean checkPossibility(int[] nums) {
        if(nums.length <= 1) {
            return true;
        }
        boolean modified = false;
        for(int i = 0; i < nums.length - 1; ++ i) {
            if(i == 0) {
                if(nums[i] > nums[i+1]) {
                    nums[i] = nums[i+1];
                    modified = true;
                }
            }
            else {
                if (nums[i] <= nums[i + 1]) {
                    continue;
                }
                if (modified) {
                    return false;
                }
                modified = true;
                if (nums[i + 1] >= nums[i - 1]) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {3, 4, 2, 3},
                new int[] {-1, 4, 2, 3}
        );
        List<Boolean> results = Arrays.asList(
                false,
                true
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.checkPossibility(tests.get(i)));
        }
    }
}