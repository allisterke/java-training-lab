package leetcode.a718;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length][B.length];
        int length = 0;
        for(int i = 0; i < A.length; ++ i) {
            for(int j = 0; j < B.length; ++ j) {
                if(i == 0 || j == 0) {
                    dp[i][j] = A[i] == B[j] ? 1 : 0;
                }
                else {
                    dp[i][j] = A[i] == B[j] ? dp[i-1][j-1] + 1 : 0;
                }
                length = Math.max(length, dp[i][j]);
            }
        }
        return length;
    }

    public static void main(String[] args) {
        List<int[]> A = Arrays.asList(
                new int[][] {
                        {1, 2, 3, 2, 1}
                }
        );
        List<int[]> B = Arrays.asList(
                new int[][] {
                        {3, 2, 1, 4, 7}
                }
        );
        List<Integer> results = Arrays.asList(
                3
        );

        Solution s = new Solution();
        for(int i = 0; i < A.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findLength(A.get(i), B.get(i)));
        }
    }
}