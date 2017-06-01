package leetcode.a240;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;
        for(int i = 0, j = C-1; i < R && j >= 0;) {
            if(target == matrix[i][j]) {
                return true;
            }
            else if(target < matrix[i][j]) {
                -- j;
            }
            else {
                ++ i;
            }
        }
        return false;
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