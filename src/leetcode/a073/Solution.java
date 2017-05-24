package leetcode.a073;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public void setZeroes(int[][] matrix) {
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;
        if(R == 0 || C == 0) {
            return;
        }
        boolean[] zeroLines = new boolean[R];
        boolean[] zeroColumns = new boolean[C];

        for(int i = 0; i < matrix.length; ++ i) {
            for(int j = 0; j < matrix[i].length; ++ j) {
                if(matrix[i][j] == 0) {
                    zeroLines[i] = true;
                    zeroColumns[j] = true;
                }
            }
        }
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(zeroLines[i] || zeroColumns[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
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