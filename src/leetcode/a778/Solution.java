package leetcode.a778;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int least = grid[0][0];
        boolean[][] visited = new boolean[grid.length][grid.length];
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(a -> grid[a.get(0)][a.get(1)]));
        queue.add(Arrays.asList(0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            List<Integer> top = queue.poll();
            int x = top.get(0);
            int y = top.get(1);
            least = Math.max(least, grid[x][y]);
            if(x == N-1 && y == N-1) {
                break;
            }
            int[][] dirs = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
            for (int[] dir : dirs) {
                int i = x + dir[0];
                int j = y + dir[1];
                if(i >= 0 && j >= 0 && i < grid.length && j < grid.length && !visited[i][j]) {
                    queue.add(Arrays.asList(i, j));
                    visited[i][j] = true;
                }
            }
        }
        return least;
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