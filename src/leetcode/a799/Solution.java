package leetcode.a799;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] row = null;
        for (int i = 0; i <= query_row; ++ i) {
            if(i == 0) {
                row = new double[]{poured};
            }
            else {
                double[] next = new double[i+1];
                for (int j = 0; j < row.length; ++ j) {
                    double spill = Math.max(row[j] - 1, 0) / 2;
                    next[j] += spill;
                    next[j+1] += spill;
                }
                row = next;
            }
        }
        return Math.min(row[query_glass], 1);
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(1, 1, 1),
                Arrays.asList(2, 1, 1),
                Arrays.asList(2, 0, 0)
        );
        List<Double> results = Arrays.asList(
                0.0,
                0.5,
                1.0
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.champagneTower(tests.get(i).get(0), tests.get(i).get(1), tests.get(i).get(2)));
        }
    }
}