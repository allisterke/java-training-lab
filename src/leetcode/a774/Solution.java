package leetcode.a774;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0, hi = stations[stations.length - 1];
        while (hi - lo > 1e-7) {
            double mid = (lo + hi) / 2;
            boolean valid = checkDist(stations, K, mid);
            if(!valid) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }
        return lo;
    }

    boolean checkDist(int[] stations, int K, double d) {
        int consumed = 0;
        for (int i = 0; i < stations.length - 1; ++ i) {
            consumed += Math.ceil((stations[i+1] - stations[i]) / d) - 1;
            if(consumed > K) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
                }
        );
        List<Integer> ks = Arrays.asList(
                9
        );
        List<Double> results = Arrays.asList(
                0.5
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minmaxGasDist(tests.get(i), ks.get(i)));
        }
    }
}