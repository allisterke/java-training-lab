package leetcode.a805;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public boolean splitArraySameAverage(int[] A) {
        if (A.length <= 1) {
            return false;
        }
        int sum = sum(A);
        Set<Integer> partial = new HashSet<>();
        for (int i = 1; i < A.length; ++ i) {
            if (sum * i % A.length == 0) {
                partial.add(sum * i / A.length);
            }
        }
        boolean[][] exist = new boolean[sum + 1][A.length + 1];
        for (int a : A) {
            for (int i = exist.length - 1; i >= 0; -- i) {
                for (int j = 0; j < A.length; ++ j) {
                    if (exist[i][j]) {
                        exist[i+a][j+1] = true;
                    }
                }
            }
            exist[a][1] = true;
        }
        for (int i = 0; i < exist.length; ++ i) {
            for (int j = 1; j < A.length; ++ j) {
                if (exist[i][j] && i * A.length == sum * j) {
                    return true;
                }
            }
        }
        return false;
    }
    
    int sum(int[] A) {
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        return sum;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1,2,3,4,5,6,7,8},
                        {18, 10, 5, 3}
                }
        );
        List<Boolean> results = Arrays.asList(
                true,
                false
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.splitArraySameAverage(tests.get(i)));
        }
    }
}