package leetcode.a719;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = (int)(1e6);
        while(left < right) {
            int mid = (left + right) / 2;
            int count = 0;
            for(int i = 0, j = 0; i < nums.length; ++ i) {
                for(j = Math.max(j, i+1); j < nums.length && nums[i] + mid >= nums[j]; ++ j) ;
                count += j - i - 1;
            }
            if(count >= k) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 3, 2, 4},
                        {1, 3, 2, 4},
                        {1, 3, 2, 4},
                        {1, 3, 2, 4},
                        {1, 3, 2, 4},
                        {1, 3, 2, 4}
                }
        );
        List<Integer> ks = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );
        List<Integer> results = Arrays.asList(
                0
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.smallestDistancePair(tests.get(i), ks.get(i)));
        }
    }
}