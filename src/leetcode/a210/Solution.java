package leetcode.a210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    List<Integer>[] getPrerequisites(int numCourses, int[][] prerequisites) {
        List<Integer>[] pre = new List[numCourses];
        for(int i = 0; i < pre.length; ++ i) {
            pre[i] = new ArrayList<>();
        }
        for(int[] pair : prerequisites) {
            pre[pair[0]].add(pair[1]);
        }
        return pre;
    }
    int[] buildSchedule(int[] finishOrder) {
        int[] result = new int[finishOrder.length];
        for(int i = 0; i < finishOrder.length; ++ i) {
            result[finishOrder[i] - 1] = i;
        }
        return result;
    }
    int finishTime = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] pre = getPrerequisites(numCourses, prerequisites);
        int[] finishOrder = new int[numCourses];
        Arrays.fill(finishOrder, -1);
        finishTime = 0;
        for(int i = 0; i < numCourses; ++ i) {
            if(finishOrder[i] < 0) {
                if(!dfs(i, pre, finishOrder)) {
                    return new int[0];
                }
            }
        }
        return buildSchedule(finishOrder);
    }

    boolean dfs(int i, List<Integer>[] pre, int[] finishOrder) {
        if(finishOrder[i] < 0) {
            finishOrder[i] = 0;
            for(int j : pre[i]) {
                if(!dfs(j, pre, finishOrder)) {
                    return false;
                }
            }
            finishOrder[i] = ++ finishTime;
        }
        else if(finishOrder[i] == 0) {
            return false;
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