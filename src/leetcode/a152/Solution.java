package leetcode.a152;

import java.util.Arrays;
import java.util.List;

public class Solution {
    int calcProduct(int[] nums, int start, int end) {
        int product = 1;
        for(int i = start; i < end; ++ i) {
            product *= nums[i];
        }
        return product;
    }
    public int maxProduct(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; ) {
            if(nums[i] != 0) {
                int j = i;
                boolean negtive = false;
                int fn = -1, ln = -1;
                for(; j < nums.length && nums[j] != 0; ++ j) {
                    if(nums[j] < 0) {
                        negtive = !negtive;
                        if(fn == -1) {
                            fn = j;
                        }
                        ln = j;
                    }
                }
                if(!negtive) {
                    max = Math.max(max, calcProduct(nums, i, j));
                }
                else if(i+1 != j) {
                    if(ln != i) {
                        max = Math.max(max, calcProduct(nums, i, ln));
                    }
                    if(fn+1 != j) {
                        max = Math.max(max, calcProduct(nums, fn+1, j));
                    }
                }
                else {
                    max = Math.max(max, nums[i]);
                }
                i = j;
            }
            else {
                max = Math.max(max, 0);
                ++ i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {-2},
                new int[] {-3, -3, 1, 3, -2, -2, -2}
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maxProduct(tests.get(i)));
        }
    }
}