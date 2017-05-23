package leetcode.a064;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minPathSum(int[][] grid) {
        int R = grid.length;
        int C = R > 0 ? grid[0].length : 0;
        if(R == 0 || C == 0) {
            return 0;
        }
        int[][] minPathSumForEveryCell = new int[R][];
        for(int i = 0; i < R; ++ i) {
            minPathSumForEveryCell[i] = new int[C];
        }
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(i == 0 && j == 0) {
                    minPathSumForEveryCell[i][j] = grid[i][j];
                }
                else if(i == 0) {
                    minPathSumForEveryCell[i][j] = grid[i][j] + minPathSumForEveryCell[i][j-1];
                }
                else if(j == 0) {
                    minPathSumForEveryCell[i][j] = grid[i][j] + minPathSumForEveryCell[i-1][j];
                }
                else {
                    minPathSumForEveryCell[i][j] = grid[i][j] + Math.min(minPathSumForEveryCell[i-1][j], minPathSumForEveryCell[i][j-1]);
                }
            }
        }
        return minPathSumForEveryCell[R-1][C-1];
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