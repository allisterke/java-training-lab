package leetcode.a207;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    static int UNVISITED = 0;
    static int VISITING = 1;
    static int VISITED = 2;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> pre[] = new List[numCourses];
        for(int i = 0; i < pre.length; ++ i) {
            pre[i] = new ArrayList<>();
        }
        for(int[] order : prerequisites) {
            pre[order[0]].add(order[1]);
        }
        int[] status = new int[numCourses];
        int vid = 0;
        for(int i = 0; i < numCourses; ++ i) {
            if(status[i] == UNVISITED) {
                if(!dfs(numCourses, i, ++ vid, pre, status)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int numCourses, int i, int vid, List<Integer>[] pre, int[] status) {
        if(status[i] != UNVISITED) {
            return status[i] == VISITED;
        }
        try {
            status[i] = VISITING;
            for (int j : pre[i]) {
                if (!dfs(numCourses, j, vid, pre, status)) {
                    return false;
                }
            }
        }
        finally {
            status[i] = VISITED;
        }
        return true;
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