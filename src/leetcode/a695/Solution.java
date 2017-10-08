package leetcode.a695;

import java.util.Arrays;
import java.util.List;

public class Solution {
    int R, C;
    int[] root;

    public int maxAreaOfIsland(int[][] grid) {
        R = grid.length;
        C = R > 0 ? grid[0].length : 0;
        root = new int[R * C];
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                root[getKey(i, j)] = getKey(i, j);
            }
        }
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(grid[i][j] == 1) {
                    if(i > 0 && grid[i-1][j] == 1) {
                        merge(getKey(i, j), getKey(i-1, j));
                    }
                    if(j > 0 && grid[i][j-1] == 1) {
                        merge(getKey(i, j), getKey(i, j-1));
                    }
                }
            }
        }
        int[] count = new int[R * C + 1];
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(grid[i][j] == 1) {
                    ++ count[getRoot(getKey(i, j))];
                }
            }
        }
        return Arrays.stream(count).max().getAsInt();
    }

    int getRoot(int n) {
        if (root[n] != n) {
            return root[n] = getRoot(root[n]);
        }
        else {
            return n;
        }
    }

    void merge(int i, int j) {
        int ri = getRoot(i);
        int rj = getRoot(j);
        if(ri != rj) {
            root[ri] = rj;
        }
    }

    int getKey(int i, int j) {
        return i * C + j;
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