package leetcode.a416;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean[] reachable = new boolean[sum + 1];
        reachable[0] = true;
        for(int n : nums) {
            for(int j = sum - n; j >= 0; -- j) {
                if(reachable[j]) {
                    reachable[j+n] = true;
                }
            }
        }
        return reachable[sum];
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