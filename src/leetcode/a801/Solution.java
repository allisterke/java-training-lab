package leetcode.a801;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minSwap(int[] A, int[] B) {
        int[][] dp = new int[A.length][2];
        for (int i = 0; i < dp.length; ++ i) {
            dp[i][0] = dp[i][1] = A.length + 1;
        }
        for (int i = 0; i < A.length; ++ i) {
            if(i == 0) {
                dp[i][0] = 0;
                dp[i][1] = 1;
            }
            else {
                if(A[i] > A[i-1] && B[i] > B[i-1]) {
                    dp[i][0] = Math.min(dp[i][0], dp[i-1][0]);
                    dp[i][1] = Math.min(dp[i][1], dp[i-1][1] + 1);
                }
                if(A[i] > B[i-1] && B[i] > A[i-1]) {
                    dp[i][0] = Math.min(dp[i][0], dp[i-1][1]);
                    dp[i][1] = Math.min(dp[i][1], dp[i-1][0] + 1);
                }
            }
        }
        return Math.min(dp[dp.length-1][0], dp[dp.length-1][1]);
    }

    public static void main(String[] args) {
        List<int[]> As = Arrays.asList(
                new int[][] {
                        {1, 3, 5, 4},
                        {0, 3, 5, 8, 9}
                }
        );
        List<int[]> Bs = Arrays.asList(
                new int[][] {
                        {1, 2, 3, 7},
                        {2, 1, 4, 6, 9}
                }
        );
        List<Integer> results = Arrays.asList(
                1,
                1
        );

        Solution s = new Solution();
        for(int i = 0; i < As.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minSwap(As.get(i), Bs.get(i)));
        }
    }
}