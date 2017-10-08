package leetcode.a694;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    int R, C;
    int[] root;

    public int numDistinctIslands(int[][] grid) {
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
        int[] top = new int[R * C];
        int[] bottom = new int[R * C];
        int[] left = new int[R * C];
        int[] right = new int[R * C];
        Arrays.fill(top, R);
        Arrays.fill(bottom, -1);
        Arrays.fill(left, C);
        Arrays.fill(right, -1);

        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(grid[i][j] == 1) {
                    int r = getRoot(getKey(i, j));
                    top[r] = Math.min(top[r], i);
                    bottom[r] = Math.max(bottom[r], i);
                    left[r] = Math.min(left[r], j);
                    right[r] = Math.max(right[r], j);
                }
            }
        }

        Set<String> set = new HashSet<>();
        for(int r = 0; r < top.length; ++ r) {
            if(top[r] != R) {
                StringBuilder sb = new StringBuilder();
                for(int i = top[r]; i <= bottom[r]; ++ i) {
                    for(int j = left[r]; j <= right[r]; ++ j) {
                        if(grid[i][j] == 1 && getRoot(getKey(i, j)) == r) {
                            sb.append(1);
                        }
                        else {
                            sb.append(0);
                        }
                    }
                    sb.append('\n');
                }
                set.add(sb.toString());
            }
        }
        return set.size();
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
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        new int[][] {
                                new int[] {1, 1, 0, 0},
                                new int[] {1, 1, 0, 0},
                                new int[] {0, 0, 1, 1},
                                new int[] {0, 0, 1, 1}
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.numDistinctIslands(tests.get(i)));
        }
    }
}