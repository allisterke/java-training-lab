package leetcode.a789;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        for (int[] ghost : ghosts) {
            if(Math.abs(target[0] - ghost[0]) + Math.abs(target[1] - ghost[1]) <=
                    Math.abs(target[0]) + Math.abs(target[1])) {
                return false;
            }
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