package leetcode.a747;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int dominantIndex(int[] nums) {
        for (int i = 0; i < nums.length; ++ i) {
            boolean twice = true;
            for (int j = 0; j < nums.length; ++ j) {
                if(i != j && nums[i] < nums[j] * 2) {
                    twice = false;
                    break;
                }
            }
            if(twice) {
                return i;
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