package leetcode.a785;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        for (int i = 0; i < visited.length; ++ i) {
            if (visited[i] != 0) {
                continue;
            }
            if (!dfs(graph, i, 1, visited)) {
                return false;
            }
        }
        return true;
    }

    boolean dfs(int[][] graph, int index, int color, int[] visited) {
        if (visited[index] != 0) {
            return visited[index] == color;
        }
        visited[index] = color;
        color = -color;
        for (int j : graph[index]) {
            if(!dfs(graph, j, color, visited)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {
                                {1, 3},
                                {0, 2},
                                {1, 3},
                                {0, 2}
                        },
                        {
                                {1, 2, 3},
                                {0, 2},
                                {0, 1, 3},
                                {0, 2}
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.isBipartite(tests.get(i)));
        }
    }
}