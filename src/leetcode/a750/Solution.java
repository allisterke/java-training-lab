package leetcode.a750;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int countCornerRectangles(int[][] grid) {
        int total = 0;
        for(int i = 0; i < grid.length; ++ i) {
            for(int j = i+1; j < grid.length; ++ j) {
                int count = countSame(grid[i], grid[j]);
                total += count * (count - 1) / 2;
            }
        }
        return total;
    }

    int countSame(int[] a, int[] b) {
        int count = 0;
        for(int i = 0; i < a.length; ++ i) {
            if(a[i] == 1 && b[i] == 1) {
                ++ count;
            }
        }
        return count;
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