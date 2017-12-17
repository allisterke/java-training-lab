package leetcode.a749;

import java.util.*;

public class Solution {
    public int containVirus(int[][] grid) {
        int total = 0;
        while (true) {
            int count = detectMost(grid);
            if(count > 0) {
                total += count;
            }
            else {
                break;
            }
        }
        return total;
    }

    public int detectMost(int[][] grid) {
        Map<Integer, Set<Integer>> sets = new HashMap<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; ++ i) {
            for(int j = 0; j < grid[i].length; ++ j) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    Set<Integer> current = new HashSet<>();
                    dfs(i, j, grid, visited, current);
                    sets.put((i << 16) | j, current);
                }
            }
        }
        if(sets.isEmpty()) {
            return 0;
        }
        int mostInfectKey = -1;
        Set<Integer> mostInfect = null;
        Map<Integer, Set<Integer>> infectMap = new HashMap<>();
        for(Map.Entry<Integer, Set<Integer>> entry: sets.entrySet()) {
            Set<Integer> area = toInfect(grid, entry.getValue(), true);
            if(mostInfectKey == -1 || area.size() > mostInfect.size()) {
                mostInfectKey = entry.getKey();
                mostInfect = area;
            }
            infectMap.put(entry.getKey(), area);
        }
        for (int n: sets.get(mostInfectKey)) {
            int i = n >> 16;
            int j = n & 0xffff;
            grid[i][j] = -1;
        }
        for(Map.Entry<Integer, Set<Integer>> entry: infectMap.entrySet()) {
            if(entry.getKey() == mostInfectKey) {
                continue;
            }
            else {
                for (int n: entry.getValue()) {
                    int i = n >> 16;
                    int j = n & 0xffff;
                    grid[i][j] = 1;
                }
            }
        }
        int count = 0;
        for (int n: mostInfect) {
            int i = n >> 16;
            int j = n & 0xffff;
            int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] d : directions) {
                int x = i + d[0];
                int y = j + d[1];
                if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
                    continue;
                }
                if(sets.get(mostInfectKey).contains((x << 16) | y)) {
                    ++ count;
                }
            }
        }
        return count;
    }

    Set<Integer> toInfect(int[][] grid, Set<Integer> current, boolean nonInfected) {
        Set<Integer> area = new HashSet<>();
        for (int n: current) {
            int i = n >> 16;
            int j = n & 0xffff;
            int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for(int[] d: directions) {
                int x = i + d[0];
                int y = j + d[1];
                if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
                    continue;
                }
                if(nonInfected && grid[x][y] != 0) {
                    continue;
                }
                area.add((x << 16) | y);
            }
        }
        return area;
    }

    void dfs(int i, int j, int[][] grid, boolean[][] visited, Set<Integer> current) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || visited[i][j] || grid[i][j] != 1) {
            return;
        }
        visited[i][j] = true;
        current.add((i << 16) | j);
        dfs(i+1, j, grid, visited, current);
        dfs(i-1, j, grid, visited, current);
        dfs(i, j+1, grid, visited, current);
        dfs(i, j-1, grid, visited, current);
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {{0,1,0,0,0,0,0,1},
                                {0,1,0,0,0,0,0,1},
                                {0,0,0,0,0,0,0,1},
                                {0,0,0,0,0,0,0,0}},
                        {{1,1,1},
                                {1,0,1},
                                {1,1,1}},
                        {{1,1,1,0,0,0,0,0,0},
                                {1,0,1,0,1,1,1,1,1},
                                {1,1,1,0,0,0,0,0,0}}
                }
        );
        List<Integer> results = Arrays.asList(
                10,
                4,
                13
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.containVirus(tests.get(i)));
        }
    }
}