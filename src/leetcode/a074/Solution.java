package leetcode.a074;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;
        int left = 0, right = R * C;
        while (left < right) {
            int mid = (left + right) / 2;
            int r = mid / C;
            int c = mid % C;
            if(matrix[r][c] > target) {
                right = mid;
            }
            else if(matrix[r][c] < target) {
                left = mid + 1;
            }
            else {
                return true;
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