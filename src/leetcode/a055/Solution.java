package leetcode.a055;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; ++ i) {
            if(reachable < i) {
                return false;
            }
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
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