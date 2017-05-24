package leetcode.a075;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public void sortColors(int[] nums) {
        int[] count = new int[4];
        for(int n : nums) { ++ count[n+1]; }
        for(int i = 0; i < 3; ++ i) { count[i+1] += count[i]; }
        for(int i = 0; i < 3; ++ i) { Arrays.fill(nums, count[i], count[i+1], i); }
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