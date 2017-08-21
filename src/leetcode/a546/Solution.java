package leetcode.a546;
import java.util.*;

public class Solution {
    Map<Integer, Integer> map;
    public int removeBoxes1(int[] boxes) {
        map = new HashMap<>();
        return dfs(boxes, 0, boxes.length - 1, 0);
    }

    int dfs(int[] boxes, int i, int j, int k) {
        if(i > j) {
            return 0;
        }
        if(i == j) {
            return (k+1) * (k+1);
        }
        if(boxes[i] == boxes[i+1]) {
            return dfs(boxes, i+1, j, k+1);
        }
        if(boxes[i] == boxes[j]) {
            return dfs(boxes, i, j-1, k+1);
        }
        int index = (i << 16) | (j << 8) | k;
        if(!map.containsKey(index)) {
            int best = 0;
            for(int p = i; p < j; ++ p) {
                best = Math.max(best, dfs(boxes, i, p, k) + dfs(boxes, p+1, j, 0));
            }
            map.put(index, best);
        }
        return map.get(index);
    }

    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        int[][][] dp = new int[boxes.length][boxes.length][boxes.length];
        for(int x = 0; x < boxes.length; ++ x) {
            for(int i = 0; i + x < boxes.length; ++ i) {
                int j = i + x;
                for(int k = 0; x + k < boxes.length; ++ k) {
                    if(x == 0) {
                        dp[i][j][k] = (k+1)*(k+1);
                    }
                    else if(boxes[i] == boxes[i+1]) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i+1][j][k+1]);
                    }
                    else if(boxes[i] == boxes[j]) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k+1]);
                    }
                    else {
                        for(int p = i; p < j; ++ p) {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i][p][k] + dp[p+1][j][0]);
                        }
                    }
                }
            }
        }
        return dp[0][boxes.length - 1][0];
    }

    public int removeBoxes0(int[] boxes) {
        if(boxes == null || boxes.length == 0) {
            return 0;
        }
        int[][][] dp = new int[boxes.length][][];
        for(int i = 0; i < dp.length; ++ i) {
            dp[i] = new int[boxes.length][];
            for(int j = 0; j < dp.length; ++ j) {
                dp[i][j] = new int[boxes.length];
            }
        }

        for(int k = 0; k < boxes.length; ++ k) {
            for(int i = 0; i + k < boxes.length; ++ i) {
                if(boxes[i] == boxes[i+k]) {
                    int j1 = i + 1;
                    for (; j1 <= i + k && boxes[j1] == boxes[i]; ++ j1) ; // boxes[j1] != boxes[i]
                    int j2 = i + k - 1;
                    for (; j2 >= i && boxes[j2] == boxes[i]; -- j2) ; // boxes[j2] != boxes[i]
                    if (j1 > j2) {
                        for(int p1 = 0; p1 + k + 1 <= boxes.length; ++ p1) {
                            dp[i][i + k][p1] = (k + 1 + p1) * (k + 1 + p1);
                        }
                    } else {
                        for(int p1 = 0; p1 + k + 1 <= boxes.length; ++ p1) {
                            for (int p2 = j2; p2 > i; -- p2)
                                dp[i][i+k][p1] = Math.max(dp[i][i+k][p1], dp[p2][j2][0] + dp[i][p2-1][p1 + i + k - j2]);
                        }
                    }
                }
                else {
                    for(int p1 = 0; p1 + k + 1 <= boxes.length; ++ p1) {
                        for(int p2 = i+k; p2 > i; -- p2)
                            dp[i][i+k][p1] = Math.max(dp[i][i+k][p1], dp[p2][i+k][0] + dp[i][p2-1][p1]);
                    }
                }
            }
        }

        return dp[0][boxes.length - 1][0];
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {3, 2, 3},
                new int[] {1, 3, 2, 2, 2, 3, 4, 3, 1},
                new int[] {3, 8, 8, 5, 5, 3, 9, 2, 4, 4, 6, 5, 8, 4, 8, 6, 9, 6, 2, 8, 6, 4, 1, 9, 5, 3, 10, 5, 3, 3, 9, 8, 8, 6, 5, 3, 7, 4, 9, 6, 3, 9, 4, 3, 5, 10, 7, 6, 10, 7}
        );
        List<Integer> results = Arrays.asList(5, 23, 136);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(i);
            System.out.println(s.removeBoxes(tests.get(i)));
        }
    }
}