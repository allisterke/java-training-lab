package leetcode.a034;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] >= target) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }

    private int upperBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] > target) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return right;
    }

    public int[] searchRange(int[] nums, int target) {
        int lb = lowerBound(nums, target);
        int ub = upperBound(nums, target);
        return lb != ub ? new int[] {lb, ub - 1} : new int[] {-1, -1};
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