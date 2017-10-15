package leetcode.a699;

import scala.Int;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> height = new ArrayList<>();
        for(int i = 0; i < positions.length; ++ i) {
            int h = 0;
            for(int j = 0; j < i; ++ j) {
                if(positions[i][0] >= positions[j][0] + positions[j][1] ||
                        positions[i][0] + positions[i][1] <= positions[j][0]) {
                    continue;
                }
                else {
                    h = Math.max(h, height.get(j));
                }
            }
            height.add(h + positions[i][1]);
        }
        List<Integer> peaks = new ArrayList<>();
        int peak = 0;
        for(int h : height) {
            peaks.add(peak = Math.max(peak, h));
        }
        return peaks;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][]{
                        {
                                {1, 2},
                                {2, 3},
                                {6, 1}
                        },
                        {
                                {100, 100},
                                {200, 100}
                        }
                }
        );
        List<List<Integer>> results = Arrays.asList(
                Arrays.asList(2, 5, 5),
                Arrays.asList(100, 100)
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.fallingSquares(tests.get(i)));
        }
    }
}