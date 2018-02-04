package leetcode.a777;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean canTransform(String start, String end) {
        int L = 0, R = 0;
        for (int i = 0; i < start.length(); ++ i) {
            if (end.charAt(i) == 'L') {
                ++ L;
            }
            if (start.charAt(i) == 'L') {
                -- L;
                if(L < 0) {
                    return false;
                }
            }
            if (start.charAt(i) == 'R') {
                ++ R;
                if (L != 0) {
                    return false;
                }
            }
            if (end.charAt(i) == 'R') {
                -- R;
                if (R < 0) {
                    return false;
                }
            }
        }
        return L == 0 && R == 0;
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