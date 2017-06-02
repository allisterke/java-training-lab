package leetcode.a229;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
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

    int count(int[] nums, int n) {
        int k = 0;
        for(int num : nums) {
            if(n == num) {
                ++ k;
            }
        }
        return k;
    }

    List<Integer> majorityElement0(int[] nums) {
        List<Integer> majority = new ArrayList<>();
        for(int i = 1; i <= 2; ++ i) {
            int pos = (nums.length / 3 + 1) * i - 1;
            if(pos < nums.length) {
                int n = findKthLargest(nums, pos, 0, nums.length-1);
                if (count(nums, n) > nums.length / 3) {
                    if(majority.isEmpty() || n != majority.get(0)) {
                        majority.add(n);
                    }
                }
            }
        }
        return majority;
    }

    List<Integer> majorityElement(int[] nums) {
        int a = 0, b = 0;
        int ca = 0, cb = 0;
        for(int n : nums) {
            if(ca == 0 && cb == 0) {
                a = n;
                ++ ca;
            }
            else if(cb == 0) {
                if(a == n) {
                    ++ ca;
                }
                else {
                    b = n;
                    ++ cb;
                }
            }
            else if(ca == 0) {
                if(b == n) {
                    ++ cb;
                }
                else {
                    a = n;
                    ++ ca;
                }
            }
            else {
                if(a == n) {
                    ++ ca;
                }
                else if(b == n) {
                    ++ cb;
                }
                else {
                    -- ca;
                    -- cb;
                }
            }
        }
        List<Integer> majority = new ArrayList<>();
        if(ca > 0 && count(nums, a) > nums.length/3) {
            majority.add(a);
        }
        if(cb > 0 && count(nums, b) > nums.length/3) {
            majority.add(b);
        }
        return majority;
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