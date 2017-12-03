package leetcode.a741;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        for(int i = 0; i < N; ++ i) {
            for(int j = 0; j < N; ++ j) {
                if(i == 0 && j == 0) {

                }
                else if(i == 0) {
                    if(grid[i][j-1] == -1) {
                        grid[i][j] = -1;
                    }
                }
                else if(j == 0) {
                    if(grid[i-1][j] == -1) {
                        grid[i][j] = -1;
                    }
                }
                else {
                    if (grid[i - 1][j] == -1 && grid[i][j - 1] == -1) {
                        grid[i][j] = -1;
                    }
                }
            }
        }
        int[][][] dp = new int[N*2 - 1][N][N];
        for(int k = 0; k < N*2 - 1; ++ k) {
            for(int i = Math.max(0, k - N + 1); i <= Math.min(k, N-1); ++ i) {
                for(int j = Math.max(0, k - N + 1); j <= Math.min(k, N-1); ++ j) {
                    if(k == 0) {
                        dp[k][i][j] = grid[0][0];
                    }
                    else {
                        for (int p = -1; p <= 0; ++ p) {
                            for(int q = -1; q <= 0; ++ q) {
                                if(i+p < 0 || j+q < 0 || k - 1 - i - p < 0 || k - 1 - j - q < 0) {
                                    continue;
                                }
                                if(grid[i+p][k - 1 - i - p] == -1 || grid[j+q][k - 1 - j - q] == -1) {
                                    continue;
                                }
                                int a = dp[k-1][i+p][j+q] + grid[i][k-i] + grid[j][k-j];
                                if(i == j) {
                                    a -= grid[i][k-i];
                                }
                                dp[k][i][j] = Math.max(dp[k][i][j], a);
                            }
                        }
                    }
                }
            }
        }
        return dp[N*2 - 2][N-1][N-1];
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {
                                {0, 1, -1},
                                {1, 0, -1},
                                {1, 1, 1}
                        }
                }
        );
        List<Integer> results = Arrays.asList(5);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.cherryPickup(tests.get(i)));
        }
    }
}