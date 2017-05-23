package leetcode.a054;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;
        for(int i = 0; i * 2 < Math.min(R, C); ++ i) {
            if(i == R - 1 - i) {
                for(int j = i; j <= C - 1 - i; ++ j) {
                    spiral.add(matrix[i][j]);
                }
            }
            else if(i == C - 1 - i) {
                for(int j = i; j <= R - 1 - i; ++ j) {
                    spiral.add(matrix[j][i]);
                }
            }
            else {
                for(int j = i; j < C - 1 - i; ++ j) {
                    spiral.add(matrix[i][j]);
                }
                for(int j = i; j < R - 1 - i; ++ j) {
                    spiral.add(matrix[j][C - 1 - i]);
                }
                for(int j = C - 1 - i; j > i; -- j) {
                    spiral.add(matrix[R - 1 - i][j]);
                }
                for(int j = R - 1 - i; j > i; -- j) {
                    spiral.add(matrix[j][i]);
                }
            }
        }
        return spiral;
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