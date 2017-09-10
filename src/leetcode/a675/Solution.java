package leetcode.a675;

import java.util.*;

public class Solution {
    List<List<Integer>> forest;
    int R, C;
    int[][] D;

    void init(List<List<Integer>> forest) {
        this.forest = forest;
        R = forest.size();
        C = forest.get(0).size();
        D = new int[R][C];
    }

    public int cutOffTree(List<List<Integer>> forest) {
        if(forest.get(0).get(0) == 0) {
            return -1;
        }

        init(forest);

        int cost = 0;
        int x = 0, y = 0;
        List<List<Integer>> trees = getTrees();
        for (List<Integer> tree : trees) {
            int i = tree.get(1);
            int j = tree.get(2);
            int dist = getDistance(x, y, i, j);
            if(dist < 0) {
                return -1;
            }
            else {
                cost += dist;
                x = i;
                y = j;
                forest.get(i).set(j, 1);
            }
        }
        return cost;
    }

    int getDistance(int sx, int sy, int ex, int ey) {
        if(ex == sx && ey == sy) {
            return 0;
        }
        for (int[] row : D) {
            Arrays.fill(row, -1);
        }
        D[sx][sy] = 0;
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(sx, sy));
        while (!queue.isEmpty()) {
            List<Integer> head = queue.poll();
            int x = head.get(0);
            int y = head.get(1);
            int[][] directions = new int[][] {
                    {1, 0},
                    {-1, 0},
                    {0, 1},
                    {0, -1}
            };
            for(int[] direction : directions) {
                int i = x + direction[0];
                int j = y + direction[1];
                if(i >= 0 && i < R && j >= 0 && j < C && D[i][j] == -1 && forest.get(i).get(j) != 0) {
                    D[i][j] = D[x][y] + 1;
                    queue.add(Arrays.asList(i, j));
                    if(i == ex && j == ey) {
                        break;
                    }
                }
            }
        }
        return D[ex][ey];
    }

    List<List<Integer>> getTrees() {
        List<List<Integer>> trees = new ArrayList<>();
        for(int i = 0; i < R; ++ i) {
            for(int j = 0; j < C; ++ j) {
                if(forest.get(i).get(j) > 1) {
                    trees.add(Arrays.asList(forest.get(i).get(j), i, j));
                }
            }
        }
        trees.sort(Comparator.comparingInt(a -> a.get(0)));
        return trees;
    }

    public static void main(String[] args) {
        List<List<List<Integer>>> tests = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(0, 0, 4),
                        Arrays.asList(7, 6, 5)
                ),
                Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(0, 0, 0),
                        Arrays.asList(7, 6, 5)
                ),
                Arrays.asList(
                        Arrays.asList(2, 3, 4),
                        Arrays.asList(0, 0, 5),
                        Arrays.asList(8, 7, 6)
                ),
                Arrays.asList(
                        Arrays.asList(2)
                )
        );
        List<Integer> results = Arrays.asList(
                6,
                -1,
                6,
                0
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.cutOffTree(tests.get(i)));
        }
    }
}