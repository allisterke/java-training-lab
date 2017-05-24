package leetcode.a080;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for(int i = 0; i < nums.length; ) {
            int k = i+1;
            for(; k < nums.length && nums[k] == nums[i]; ++ k) ;
            for(int p = 0; p < Math.min(k - i, 2); ++ p) {
                nums[j ++] = nums[i];
            }
            i = k;
        }
        return j;
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