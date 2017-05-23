package leetcode.a063;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = m == 0 ? 0 : obstacleGrid[0].length;
        if(m <= 0 || n <= 0 || obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        int[][] pathCount = new int[m][];
        for(int i = 0; i < m; ++ i) {
            pathCount[i] = new int[n];
        }
        for(int i = 0; i < m; ++ i) {
            for(int j = 0; j < n; ++ j) {
                pathCount[i][j] = obstacleGrid[i][j] == 1 ? 0 : i == 0 || j == 0 ? 1 : pathCount[i-1][j] + pathCount[i][j-1];
            }
        }
        return pathCount[m-1][n-1];

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