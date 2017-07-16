package leetcode.a643;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        for(int i = 0; i < k; ++ i) {
            sum += nums[i];
        }
        long r = sum;
        for(int i = k; i < nums.length; ++ i) {
            r -= nums[i - k];
            r += nums[i];
            if(r > sum) {
                sum = r;
            }
        }
        return 1.0 * sum / k;
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