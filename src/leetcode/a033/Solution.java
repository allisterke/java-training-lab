package leetcode.a033;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if(target == nums[mid]) {
                return mid;
            }
            if(nums[mid] >= nums[left]) {
                if(target >= nums[left] && target < nums[mid]) {
                    right = mid;
                }
                else {
                    left = mid + 1;
                }
            }
            else {
                if(target > nums[mid] && target <= nums[right - 1]) {
                    left = mid + 1;
                }
                else {
                    right = mid;
                }
            }
        }
        return -1;
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