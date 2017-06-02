package leetcode.a260;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[] singleNumber(int[] nums) {
        int zero = 0;
        for(int n : nums) {
            if(n == 0) {
                ++ zero;
            }
        }
        if(zero == 1) {
            int a = 0;
            for(int n : nums) {
                a ^= n;
            }
            return new int[] {0, a};
        }
        int mask = 0;
        for(int i = 0; i < 32; ++ i) {
            int a = 0, b = 0;
            int m = (1 << i);
            for(int n : nums) {
                if((n & m) != 0) {
                    a ^= n;
                }
                else {
                    b ^= n;
                }
            }
            if(a != 0 && b != 0) {
                return new int[] { a, b };
            }
        }
        return new int[0];
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