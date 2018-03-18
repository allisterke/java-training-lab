package leetcode.a803;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        Set<List<Integer>> ones = new HashSet<>();
        for(int i = 0; i < grid.length; ++ i) {
            for (int j = 0; j < grid[i].length; ++ j) {
                if(grid[i][j] == 1) {
                    ones.add(Arrays.asList(i, j));
                }
            }
        }
        for (int i = 0; i < hits.length; ++ i) {
            int x = hits[i][0];
            int y = hits[i][1];
            if(grid[x][y] == 1) {
                grid[x][y] = 0;
            }
            else {
                hits[i][0] = hits[i][1] = -1;
            }
        }
        collectAccessible(grid);
        for(int i = 0; i < grid.length; ++ i) {
            for (int j = 0; j < grid[i].length; ++ j) {
                if(grid[i][j] == 1) {
                    ones.remove(Arrays.asList(i, j));
                }
            }
        }
        for (int[] hit : hits) {
            ones.remove(Arrays.asList(hit[0], hit[1]));
        }
        int[] bricks = new int[hits.length];
        for (int i = bricks.length - 1; i >= 0; -- i) {
            int x = hits[i][0];
            int y = hits[i][1];
            if (x < 0) {
                continue;
            }
            ones.add(Arrays.asList(x, y));
            if (connected(grid, x, y)) {
                int size0 = ones.size();
                recollectByDfs(grid, x, y, ones);
                int size1 = ones.size();
                bricks[i] = size0 - size1 - 1;
            }
        }
        return bricks;
    }

    void recollectByDfs(int[][] grid, int i, int j, Set<List<Integer>> ones) {
        List<Integer> index = Arrays.asList(i, j);
        if (ones.contains(index)) {
            grid[i][j] = 1;
            ones.remove(index);
            recollectByDfs(grid, i-1, j, ones);
            recollectByDfs(grid, i+1, j, ones);
            recollectByDfs(grid, i, j-1, ones);
            recollectByDfs(grid, i, j+1, ones);
        }
    }

    boolean connected(int[][] grid, int i, int j) {
        return i == 0 ||
                i - 1 >= 0 && grid[i-1][j] == 1 ||
                i + 1 < grid.length && grid[i+1][j] == 1 ||
                j - 1 >= 0 && grid[i][j-1] == 1 ||
                j + 1 < grid[i].length && grid[i][j+1] == 1;
    }

    void collectAccessible(int[][] grid) {
        for (int i = 0; i < Math.min(1, grid.length); ++ i) {
            for (int j = 0; j < grid[i].length; ++ j) {
                dfs(grid, i, j);
            }
        }
        for (int i = 0; i < grid.length; ++ i) {
            for (int j = 0; j < grid[i].length; ++ j) {
                if(grid[i][j] == -1) {
                    grid[i][j] = 1;
                }
                else {
                    grid[i][j] = 0;
                }
            }
        }
    }

    void dfs(int[][] grid, int i, int j) {
        if(i >= 0 && j >= 0 && i < grid.length && j < grid[i].length) {
            if(grid[i][j] == 1) {
                grid[i][j] = -1;
                dfs(grid, i-1, j);
                dfs(grid, i+1, j);
                dfs(grid, i, j-1);
                dfs(grid, i, j+1);
            }
        }
    }

    public static void main(String[] args) {
        List<int[][]> grids = Arrays.asList(
                new int[][][] {
                        {
                                {1, 0, 0, 0},
                                {1, 1, 1, 0}
                        },
                        {
                                {1, 0, 0, 0},
                                {1, 1, 0, 0}
                        },
                        {
                                {1},
                                {1},
                                {1},
                                {1},
                                {1}
                        }
                }
        );
        List<int[][]> hits = Arrays.asList(
                new int[][][] {
                        {
                                {1, 0}
                        },
                        {
                                {1, 1},
                                {1, 0}
                        },
                        {
                                {3, 0},
                                {4, 0},
                                {1, 0},
                                {2, 0},
                                {0, 0},
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < grids.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.hitBricks(grids.get(i), hits.get(i))).boxed().collect(Collectors.toList()));
        }
    }
}