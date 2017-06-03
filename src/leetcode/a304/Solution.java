package leetcode.a304;

import java.util.Arrays;
import java.util.List;

public class Solution {
//    class NumMatrix {
//
//        int[][] matrix;
//
//        public NumMatrix(int[][] matrix) {
//            this.matrix = matrix;
//            for(int i = 0; i < matrix.length; ++ i) {
//                for(int j = 0; j < matrix[i].length; ++ j) {
//                    if(i == 0 && j == 0) {
//                        // do nothing
//                    }
//                    else if(j == 0) {
//                        matrix[i][j] += matrix[i-1][j];
//                    }
//                    else if(i == 0) {
//                        matrix[i][j] += matrix[i][j-1];
//                    }
//                    else {
//                        matrix[i][j] += matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
//                    }
//                }
//            }
//        }
//
//        public int sumRegion(int row1, int col1, int row2, int col2) {
//            if(row1 > 0 && col1 > 0) {
//                return matrix[row2][col2] - matrix[row1 - 1][col2] - matrix[row2][col1 - 1] + matrix[row1 - 1][col1 - 1];
//            }
//            else if(row1 > 0) {
//                return matrix[row2][col2] - matrix[row1 - 1][col2];
//            }
//            else if(col1 > 0) {
//                return matrix[row2][col2] - matrix[row2][col1 - 1];
//            }
//            else {
//                return matrix[row2][col2];
//            }
//        }
//    }

    class NumMatrix {
        int[][] matrix;
        boolean[][] calculated;
        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;

            calculated = new boolean[matrix.length][];
            for(int i = 0; i < matrix.length; ++ i) {
                calculated[i] = new boolean[matrix[i].length];
            }
        }

        int sumRegion(int i, int j) {
            if(!calculated[i][j]) {
                if (i == 0 && j == 0) {
                    // do nothing
                } else if (j == 0) {
                    matrix[i][j] += sumRegion(i-1, j);
                } else if (i == 0) {
                    matrix[i][j] += sumRegion(i, j-1);
                } else {
                    matrix[i][j] += sumRegion(i-1, j) + sumRegion(i, j-1) - sumRegion(i-1, j-1);
                }
                calculated[i][j] = true;
            }
            return matrix[i][j];
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if(row1 > 0 && col1 > 0) {
                return sumRegion(row2, col2) - sumRegion(row1-1, col2) - sumRegion(row2, col1-1) + sumRegion(row1-1, col1-1);
            }
            else if(row1 > 0) {
                return sumRegion(row2, col2) - sumRegion(row1-1, col2);
            }
            else if(col1 > 0) {
                return sumRegion(row2, col2) - sumRegion(row2, col1-1);
            }
            else {
                return sumRegion(row2, col2);
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