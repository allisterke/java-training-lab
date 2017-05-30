package leetcode.a201;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        for(long i = 1; i <= n; i <<= 1) {
            if(m >= i && n < i*2) {
                return (int)(i + rangeBitwiseAnd((int)(m - i), (int)(n - i)));
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(5, 7)
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.rangeBitwiseAnd(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}