package leetcode.a743;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; ++ i) {
            for(int j = 0; j < N; ++ j) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int[] time: times) {
            matrix[time[0] - 1][time[1] - 1] = Math.min(matrix[time[0] - 1][time[1] - 1], time[2]);
        }
        int[] delay = new int[N];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        queue.add(new int[]{1, K-1});
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int d = top[0];
            int v = top[1];
            if (delay[v] > 0) {
                continue;
            }
            delay[v] = d;
            for(int i = 0; i < N; ++ i) {
                if(delay[i] == 0 && matrix[v][i] != Integer.MAX_VALUE) {
                    queue.add(new int[]{d + matrix[v][i], i});
                }
            }
        }
        return Arrays.stream(delay).filter(a -> a == 0).count() > 0 ? -1 : Arrays.stream(delay).max().getAsInt() - 1;
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