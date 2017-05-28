package leetcode.a153;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length);
    }

    int findMin(int[] nums, int begin, int end) {
        if(nums[begin] <= nums[end-1]) {
            return nums[begin];
        }
        int mid = (begin + end) / 2;
        return Math.min(findMin(nums, begin, mid), findMin(nums, mid, end));
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