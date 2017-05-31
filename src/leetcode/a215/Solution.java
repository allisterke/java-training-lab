package leetcode.a215;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k-1);
    }

    static int LEFTWARD = 0;
    static int RIGHTWARD = 1;
    int findKthLargest(int[] nums, int begin, int end, int k) {
        if(begin == end) {
            return nums[begin];
        }
//        int mid = selectPivot(nums, begin, end);
        int mid = (begin + end) / 2;
        int pivot = nums[mid];
        nums[mid] = nums[end];
        int left = begin, right = end;
        int direction = RIGHTWARD;
        while (left < right) {
            if(direction == RIGHTWARD) {
                if (nums[left] > pivot) {
                    ++left;
                } else {
                    nums[right] = nums[left];
                    direction = LEFTWARD;
                }
            }
            else {
                if(nums[right] <= pivot) {
                    -- right;
                }
                else {
                    nums[left] = nums[right];
                    direction = RIGHTWARD;
                }
            }
        }
        nums[left] = pivot;
        if(left == k) {
            return pivot;
        }
        else if(k < left) {
            return findKthLargest(nums, begin, left - 1, k);
        }
        else {
            return findKthLargest(nums, right + 1, end, k);
        }
    }

    int selectPivot(int[] nums, int begin, int end) {
        if(end - begin < 2) {
            return begin;
        }
        int mid = (begin + end) / 2;
        int a = nums[begin], b = nums[mid], c = nums[end];
        if(a >= b && a <= c) {
            return begin;
        }
        if(b >= a && b <= c) {
            return mid;
        }
        return end;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        for(int i = 1; i <= 6; ++ i) {
            int nums[] = new int[] {1, 4, 2, 8, 5, 7};
            System.out.println(s.findKthLargest(nums, i));
        }
    }
}