package leetcode.a764;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        boolean[][] board = new boolean[N][N];
        for (int[] mine : mines) {
            board[mine[0]][mine[1]] = true;
        }
        int maxOrder = 0;
        int[][][] dp = new int[4][N][N];
        for (int i = 0; i < N; ++ i) {
            for (int j = 0; j < N; ++ j) {
                if(board[i][j]) {
                    continue;
                }
                int[][] top = dp[0];
                if(i == 0 || board[i-1][j]) {
                    top[i][j] = i;
                }
                else {
                    top[i][j] = top[i-1][j];
                }
                int[][] left = dp[1];
                if(j == 0 || board[i][j-1]) {
                    left[i][j] = j;
                }
                else {
                    left[i][j] = left[i][j-1];
                }
                int[][] bottom = dp[2];
                if (i == 0 || board[i-1][j]) {
                    for (int k = i; k < N && !board[k][j]; ++ k) {
                        bottom[i][j] = k;
                    }
                }
                else {
                    bottom[i][j] = bottom[i-1][j];
                }
                int[][] right = dp[3];
                if (j == 0 || board[i][j-1]) {
                    for (int k = j; k < N && !board[i][k]; ++ k) {
                        right[i][j] = k;
                    }
                }
                else {
                    right[i][j] = right[i][j-1];
                }
                int order = Integer.MAX_VALUE;
                order = Math.min(order, i - top[i][j]);
                order = Math.min(order, bottom[i][j] - i);
                order = Math.min(order, j - left[i][j]);
                order = Math.min(order, right[i][j] - j);
                maxOrder = Math.max(maxOrder, order + 1);
            }
        }
        return maxOrder;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                5,
                2,
                1
        );
        List<int[][]> mines = Arrays.asList(
                new int[][][] {
                        {{4, 2}},
                        new int[][] {},
                        {{0, 0}}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.orderOfLargestPlusSign(tests.get(i), mines.get(i)));
        }
    }
}