package leetcode.a823;

import java.util.*;

public class Solution {
    public int numFactoredBinaryTrees(int[] A) {
        long mod = (long)(1e9+7);
        Arrays.sort(A);
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            set.add(a);
        }
        Map<Integer, Long> dp = new HashMap<>();
        long total = 0;
        for (int i = 0; i < A.length; ++ i) {
            long num = 1;
            for (int j = 0; j < i; ++ j) {
                int o;
                if (A[i] % A[j] == 0 && set.contains(o = A[i] / A[j])) {
                    long extra = dp.get(A[j]) * dp.get(A[i] / A[j]) % mod;
                    num += extra;
                    num %= mod;
                }
            }
            dp.put(A[i], num);
            total = (total + num) % mod;
        }
        return (int)total;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {2, 4},
                        {2, 4, 5, 10},
                        {15,13,22,7,11}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.numFactoredBinaryTrees(tests.get(i)));
        }
    }
}