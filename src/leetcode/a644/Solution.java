package leetcode.a644;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int[] sums = new int[nums.length + 1];
        for(int i = 0; i < nums.length; ++ i) {
            sums[i+1] = sums[i] + nums[i];
        }
        double lower = -10000, upper = 10000;
        while (upper - lower >= 1e-5) {
            double mid = (lower + upper) / 2;
            if(check(sums, k, mid)) {
                upper = mid;
            }
            else {
                lower = mid;
            }
        }
        return Math.abs(upper) < 1e-5 ? 0 : upper;
    }

    boolean check(int[] sums, int k, double avg) {
        double[] t = new double[sums.length];
        for(int i = 0; i < sums.length; ++ i) {
            t[i] = sums[i] - avg * i;
        }
        double min = 0;
        for (int i = k; i < t.length; ++ i) {
            if(t[i - k] < min) {
                min = t[i - k];
            }
            if(t[i] > min) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        new int[] {1,12,-5,-6,50,3},
                        new int[] {1, 2}
                }
        );
        List<Integer> ks = Arrays.asList(
                4,
                2
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.findMaxAverage(tests.get(i), ks.get(i)));
        }
    }
}