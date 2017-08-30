package practise;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;
import java.util.stream.Collectors;

public class RemoveOneWall {
    int shortestPath(int[][] maze) {
        int R = maze.length;
        int C = maze[0].length;
        int shortest = shortestPath1(maze);
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(maze[i][j] == 1) {
                    maze[i][j] = 0;
                    shortest = Math.min(shortest, shortestPath1(maze));
                    maze[i][j] = 1;
                }
            }
        }
        return shortest;
    }

    int shortestPath1(int[][] maze) {
        int R = maze.length;
        int C = maze[0].length;
        Deque<Integer> x = new ArrayDeque<>();
        Deque<Integer> y = new ArrayDeque<>();
        x.push(0);
        y.push(0);
        int[][] direction = new int[][] {
                new int[] {1, 0},
                new int[] {-1, 0},
                new int[] {0, 1},
                new int[] {0, -1}
        };
        int[][] dist = new int[R][C];
        dist[0][0] = 1;
        while (!x.isEmpty()) {
            int p = x.poll();
            int q = y.poll();
            for(int[] dir : direction) {
                int u = p + dir[0];
                int v = q + dir[1];
                if(u >= 0 && v >= 0 && u < R && v < C && dist[u][v] == 0 && maze[u][v] == 0) {
                    dist[u][v] = dist[p][q] + 1;
                    x.add(u);
                    y.add(v);
                }
            }
        }
//        return dist[R-1][C-1];
        return dist[R-1][C-1] != 0 ? dist[R-1][C-1] : Integer.MAX_VALUE;
    }

    int shortestPath0(int[][] maze) {
        int R = maze.length;
        int C = maze[0].length;
        int[][][][] dp = new int[R][C][R][C];
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                for(int p = 0; p < R; ++ p) {
                    for(int q = 0; q < C; ++ q) {
                        dp[i][j][p][q] = Integer.MAX_VALUE;
                    }
                }
                Deque<Integer> x = new ArrayDeque<>();
                Deque<Integer> y = new ArrayDeque<>();
                Deque<Integer> z = new ArrayDeque<>();
                x.push(i);
                y.push(j);
                z.push(1);
                dp[i][j][i][j] = 1;
                int[][] direction = new int[][] {
                        new int[] {1, 0},
                        new int[] {-1, 0},
                        new int[] {0, 1},
                        new int[] {0, -1}
                };
                while (!x.isEmpty()) {
                    int p = x.poll();
                    int q = y.poll();
                    int r = z.poll() + 1;
                    for(int[] dir : direction) {
                        int u = p + dir[0];
                        int v = q + dir[1];
                        if(u >= 0 && v >= 0 && u < R && v < C && dp[i][j][u][v] == Integer.MAX_VALUE) {
                            dp[i][j][u][v] = r;
                            if(maze[u][v] == 0) {
                                x.add(u);
                                y.add(v);
                                z.add(r);
                            }
                        }
                    }
                }
            }
        }
        int shortest = dp[0][0][R-1][C-1];
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(maze[i][j] == 1 &&
                    dp[0][0][i][j] != Integer.MAX_VALUE && dp[i][j][R-1][C-1] != Integer.MAX_VALUE) {
                    shortest = Math.min(shortest, dp[0][0][i][j] + dp[i][j][R-1][C-1] - 1);
                }
            }
        }
        return shortest;
    }

    static void run() {
        RemoveOneWall r = new RemoveOneWall();
        System.out.println(r.shortestPath(new int[][] {
                {0, 1, 1, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        }));
        System.out.println(r.shortestPath0(new int[][] {
                {0, 1, 1, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        }));
//        System.out.println(r.shortestPath(new int[][] {
//                new int[] {0, 1, 1, 0},
//                new int[] {0, 0, 0, 1},
//                new int[] {1, 1, 0, 0},
//                new int[] {1, 1, 1, 0}
//        }));
//        System.out.println(r.shortestPath(new int[][] {
//                {0, 0, 0, 0, 0, 0},
//                {1, 1, 1, 1, 1, 0},
//                {0, 0, 0, 0, 0, 0},
//                {0, 1, 1, 1, 1, 1},
//                {0, 1, 1, 1, 1, 1},
//                {0, 0, 0, 0, 0, 0}}
//        ));
    }

    static void test() {
        Random r = new Random();
        RemoveOneWall w = new RemoveOneWall();
        for(int i = 0; i < 10; ++ i) {
            int R = r.nextInt(19) + 2;
            int C = r.nextInt(19) + 2;
            int[][] maze = new int[R][C];
            for(int x = 0; x < R; ++ x) {
                for(int y = 0; y < C; ++ y) {
                    maze[x][y] = r.nextInt(2);
                }
            }
            maze[0][0] = 0;
            maze[R-1][C-1] = 0;
            if(w.shortestPath(maze) == w.shortestPath0(maze)) {
                continue;
            }
            for(int[] row : maze) {
                System.out.println(Arrays.stream(row).boxed().collect(Collectors.toList()));
            }
            break;
        }
    }

    public static void main(String[] args) {
        test();
//        run();
    }
}
