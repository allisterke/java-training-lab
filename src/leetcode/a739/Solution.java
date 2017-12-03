package leetcode.a739;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[][] next = new int[temperatures.length][101];
        for(int i = 0; i < temperatures.length; ++ i) {
            for (int j = 30; j <= 100; ++ j) {
                if(i == 0 || temperatures[i] == j) {
                    int pos = i + 1;
                    for(; pos < temperatures.length && temperatures[pos] != j; ++ pos) ;
                    next[i][j] = pos;
                }
                else {
                    next[i][j] = next[i-1][j];
                }
            }
        }
        int[] warmer = new int[temperatures.length];
        for(int i = 0; i < warmer.length; ++ i) {
            for(int j = temperatures[i] + 1; j <= 100; ++ j) {
                if(next[i][j] != temperatures.length) {
                    if(warmer[i] == 0 || next[i][j] - i < warmer[i]) {
                        warmer[i] = next[i][j] - i;
                    }
                }
            }
        }
        return warmer;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {73, 74, 75, 71, 69, 72, 76, 73}
                }
        );
        List<int[]> results = Arrays.asList(
                new int[][] {
                        {1, 1, 4, 2, 1, 1, 0, 0}
                }
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.dailyTemperatures(tests.get(i))).boxed().collect(Collectors.toList()));
        }
    }
}