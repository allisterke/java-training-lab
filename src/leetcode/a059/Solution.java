package leetcode.a059;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][];
        for(int i = 0; i < n; ++ i) {
            matrix[i] = new int[n];
        }
        int k = 1;
        for(int i = 0; i * 2 < n; ++ i) {
            if(i == n - 1 - i) {
                matrix[i][i] = k;
            }
            else {
                for(int j = i; j < n - 1 - i; ++ j) {
                    matrix[i][j] = k ++;
                }
                for(int j = i; j < n - 1 - i; ++ j) {
                    matrix[j][n - 1 - i] = k ++;
                }
                for(int j = n - 1 - i; j > i; -- j) {
                    matrix[n - 1 - i][j] = k ++;
                }
                for(int j = n - 1 - i; j > i; -- j) {
                    matrix[j][i] = k ++;
                }
            }
        }
        return matrix;
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