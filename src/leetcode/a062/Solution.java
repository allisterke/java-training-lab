package leetcode.a062;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0) {
            return 0;
        }
        int[][] pathCount = new int[m][];
        for(int i = 0; i < m; ++ i) {
            pathCount[i] = new int[n];
        }
        for(int i = 0; i < m; ++ i) {
            for(int j = 0; j < n; ++ j) {
                pathCount[i][j] = i == 0 || j == 0 ? 1 : pathCount[i-1][j] + pathCount[i][j-1];
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