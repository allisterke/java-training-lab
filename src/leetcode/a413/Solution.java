package leetcode.a413;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        for(int i = 0; i < A.length - 1; ) {
            int j = i+1;
            int d = A[j] - A[i];
            for(; j < A.length && A[j] - A[j-1] == d; ++ j)
                ;
            -- j;
            count += (j - i - 1) * (j - i) / 2;
            i = j;
        }
        return count;
    }

    public int numberOfArithmeticSlices0(int[] A) {
        Map<Integer, Integer>[] dp = new Map[A.length];
        for(int i = 0; i < A.length; ++ i) {
            dp[i] = new HashMap<>();
        }
        int count = 0;
        for(int i = 1; i < A.length; ++ i) {
//            for(int j = 0; j < i; ++ j) {
            int j = i-1;
                int d = A[i] - A[j];
                dp[i].putIfAbsent(d, 0);
                int cj = dp[j].getOrDefault(d, 0);
                count += cj;
                dp[i].put(d, dp[i].get(d) + cj + 1);
//            }
        }
        return count;
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