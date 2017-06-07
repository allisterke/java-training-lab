package leetcode.a378;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        if((k << 1) > n*n) {
            return kthLargest(matrix, n*n - k + 1);
        }
        int flag = matrix[0][0] - 1;
        while (-- k > 0) {
            int i = 0, j = 0;
            while(true) {
                if(i+1 < n && j+1 < n && matrix[i+1][j] != flag && matrix[i][j+1] != flag) {
                    if(matrix[i+1][j] < matrix[i][j+1]) {
                        matrix[i][j] = matrix[i+1][j];
                        ++ i;
                    }
                    else {
                        matrix[i][j] = matrix[i][j+1];
                        ++ j;
                    }
                }
                else if(i+1 < n && matrix[i+1][j] != flag) {
                    matrix[i][j] = matrix[i+1][j];
                    ++ i;
                }
                else if(j+1 < n && matrix[i][j+1] != flag) {
                    matrix[i][j] = matrix[i][j+1];
                    ++ j;
                }
                else {
                    break;
                }
            }
            matrix[i][j] = flag;
        }
        return matrix[0][0];
    }

    int kthLargest(int[][] matrix, int k) {
        int n = matrix.length;
        int flag = matrix[n-1][n-1] + 1;
        while (-- k > 0) {
            int i = n-1, j = n-1;
            while(true) {
                if(i-1 >= 0 && j-1 >= 0 && matrix[i-1][j] != flag && matrix[i][j-1] != flag) {
                    if(matrix[i-1][j] > matrix[i][j-1]) {
                        matrix[i][j] = matrix[i-1][j];
                        -- i;
                    }
                    else {
                        matrix[i][j] = matrix[i][j-1];
                        -- j;
                    }
                }
                else if(i-1 >= 0 && matrix[i-1][j] != flag) {
                    matrix[i][j] = matrix[i-1][j];
                    -- i;
                }
                else if(j-1 >= 0 && matrix[i][j-1] != flag) {
                    matrix[i][j] = matrix[i][j-1];
                    -- j;
                }
                else {
                    break;
                }
            }
            matrix[i][j] = flag;
        }
        return matrix[n-1][n-1];
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