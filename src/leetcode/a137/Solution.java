package leetcode.a137;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int singleNumber(int[] nums) {
        int[] bitCount = new int[32];
        for(int n : nums) {
            for(int i = 0; i < 32; ++ i) {
                if((n & (1 << i)) != 0) {
                    ++ bitCount[i];
                }
            }
        }
        int result = 0;
        for(int i = 0; i < 32; ++ i) {
            if(bitCount[i] % 3 != 0) {
                result |= (1 << i);
            }
        }
        return result;
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