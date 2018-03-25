package leetcode.a807;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] top = new int[grid[0].length];
        int[] left = new int[grid.length];
        for (int i = 0; i < grid[0].length; ++ i) {
            top[i] = grid[0][i];
            for (int j = 1; j < grid.length; ++ j) {
                top[i] = Math.max(top[i], grid[j][i]);
            }
        }
        for (int i = 0; i < grid.length; ++ i) {
            left[i] = grid[i][0];
            for (int j = 1; j < grid[i].length; ++ j) {
                left[i] = Math.max(left[i], grid[i][j]);
            }
        }
        int maxIncrease = 0;
        for (int i = 0; i < grid.length; ++ i) {
            for (int j = 0; j < grid[i].length; ++ j) {
                maxIncrease += Math.min(left[i], top[j]) - grid[i][j];
            }
        }
        return maxIncrease;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {{3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0}}
                }
        );
        List<Integer> results = Arrays.asList(
                35
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maxIncreaseKeepingSkyline(tests.get(i)));
        }
    }
}