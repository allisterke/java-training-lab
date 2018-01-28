package leetcode.a766;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        for(int i = 0; i < C; ++ i) {
            int v = matrix[R-1][i];
            for (int j = 1; j <= i && j < R; ++ j) {
                if(matrix[R-1-j][i-j] != v) {
                    return false;
                }
            }
        }
        for (int i = R-1; i >= 0; -- i) {
            int v = matrix[i][C-1];
            for (int j = 1; j <= i && j < C; ++ j) {
                if(matrix[i-j][C-1-j] != v) {
                    return false;
                }
            }
        }
        return true;
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