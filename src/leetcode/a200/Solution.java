package leetcode.a200;

import java.util.Arrays;
import java.util.List;

public class Solution {
    int R, C;
    public int numIslands(char[][] grid) {
        R = grid.length;
        C = R > 0 ? grid[0].length : 0;
        int count = 0;
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(grid[i][j] == '1') {
                    ++ count;
                    markDfs(grid, i, j);
                }
            }
        }
        return count;
    }

    void markDfs(char[][] grid, int i, int j) {
        if(i >= 0 && j >= 0 && i < R && j < C && grid[i][j] == '1') {
            grid[i][j] = '2';
            markDfs(grid, i+1, j);
            markDfs(grid, i-1, j);
            markDfs(grid, i, j+1);
            markDfs(grid, i, j-1);
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