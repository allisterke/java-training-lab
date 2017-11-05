package leetcode.a717;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        if(bits.length == 1 || bits[bits.length - 2] == 0) {
            return true;
        }
        boolean[] valid = new boolean[bits.length - 1];
        valid[0] = true;
        for(int i = 0; i < bits.length - 2; ++ i) {
            if(bits[i] == 1) {
                valid[i+1] = i > 0 && bits[i-1] == 1 && valid[i-1];
            }
            else {
                valid[i+1] = valid[i] || i > 0 && bits[i-1] == 1 && valid[i-1];
            }
        }
        return !valid[valid.length - 1];
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 0, 0},
                        {1, 1, 1, 0}
                }
        );
        List<Boolean> results = Arrays.asList(
                true, false
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.isOneBitCharacter(tests.get(i)));
        }
    }
}