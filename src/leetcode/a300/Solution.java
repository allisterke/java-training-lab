package leetcode.a300;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] lis = new int[nums.length];
        int length = 0;
        for(int n : nums) {
            if(length == 0 || n > lis[length - 1]) {
                lis[length ++] = n;
            }
            else {
                int index = upper_bound(lis, 0, length, n);
                lis[index] = n;
            }
        }
        return length;
    }

    int upper_bound(int[] nums, int begin, int end, int n) {
        while (begin < end) {
            int mid = (begin + end) / 2;
            if(nums[mid] < n) {
                begin = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return begin;
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