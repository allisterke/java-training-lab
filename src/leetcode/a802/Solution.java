package leetcode.a802;

import java.util.*;

public class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int[] status = new int[graph.length];
        for (int i = 0; i < graph.length; ++ i) {
            dfs(i, graph, status);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < status.length; ++ i) {
            if(status[i] > 0) {
                result.add(i);
            }
        }
        return result;
    }

    boolean dfs(int n, int[][] graph, int[] status) {
        if(status[n] != 0) {
            return status[n] > 0;
        }
        boolean safe = true;
        status[n] = -1;
        for (int k : graph[n]) {
            if (!dfs(k, graph, status)) {
                safe = false;
            }
        }
        status[n] = safe ? 1 : -1;
        return safe;
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