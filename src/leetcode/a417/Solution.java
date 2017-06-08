package leetcode.a417;

import java.util.*;

public class Solution {
    boolean[][] buildMarkMatrix(int R, int C) {
        boolean[][] mark = new boolean[R][];
        for(int i = 0; i < R; ++ i) {
            mark[i] =  new boolean[C];
        }
        return mark;
    }
    boolean[][] checkPacific(int[][] matrix, int R, int C) {
        boolean[][] pacific = buildMarkMatrix(R, C);
        Queue<List<Integer>> queue = new ArrayDeque<>();
        for(int i = 0; i < R; ++ i) {
            pacific[i][0] = true;
            queue.add(Arrays.asList(i, 0));
        }
        for(int i = 1; i < C; ++ i) {
            pacific[0][i] = true;
            queue.add(Arrays.asList(0, i));
        }
        bfs(matrix, R, C, pacific, queue);
        return pacific;
    }
    boolean[][] checkAtlantic(int[][] matrix, int R, int C) {
        boolean[][] atlantic = buildMarkMatrix(R, C);
        Queue<List<Integer>> queue = new ArrayDeque<>();
        for(int i = 0; i < R; ++ i) {
            atlantic[i][C-1] = true;
            queue.add(Arrays.asList(i, C-1));
        }
        for(int i = 0; i < C-1; ++ i) {
            atlantic[R-1][i] = true;
            queue.add(Arrays.asList(R-1, i));
        }
        bfs(matrix, R, C, atlantic, queue);
        return atlantic;
    }
    void bfs(int[][] matrix, int R, int C, boolean[][] pacific, Queue<List<Integer>> queue) {
        while (!queue.isEmpty()) {
            List<Integer> front = queue.poll();
            int i = front.get(0);
            int j = front.get(1);
            for(int p = -1; p <= 1; ++ p) {
                for(int q = -1; q <= 1; ++ q) {
                    if(Math.abs(p) != Math.abs(q)) {
                        int x = i + p;
                        int y = j + q;
                        if(x >= 0 && x < R && y >= 0 && y < C && matrix[x][y] >= matrix[i][j] && !pacific[x][y]) {
                            pacific[x][y] = true;
                            queue.add(Arrays.asList(x, y));
                        }
                    }
                }
            }
        }
    }
    public List<int[]> pacificAtlantic(int[][] matrix) {
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;
        if(R == 0 || C == 0) {
            return Arrays.asList();
        }
        boolean[][] pacific = checkPacific(matrix, R, C);
        boolean[][] atlantic = checkAtlantic(matrix, R, C);
        List<int[]> result = new ArrayList<>();
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(pacific[i][j] & atlantic[i][j]) {
                    result.add(new int[] {i, j});
                }
            }
        }
        return result;
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