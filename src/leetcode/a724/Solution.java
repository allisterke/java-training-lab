package leetcode.a724;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int pivotIndex(int[] nums) {
        int[] acc = new int[nums.length + 2];
        for(int i = 0; i < nums.length; ++ i) {
            acc[i+1] = acc[i] + nums[i];
        }
        acc[nums.length + 1] = acc[nums.length];
        for(int i = 0; i < nums.length; ++ i) {
            if(acc[i] == acc[nums.length + 1] - acc[i+1]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 7, 3, 6, 5, 6},
                        {1, 2, 3}
                }
        );
        List<Integer> results = Arrays.asList(
                3,
                -1
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.pivotIndex(tests.get(i)));
        }
    }
}