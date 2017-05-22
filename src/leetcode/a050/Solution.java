package leetcode.a050;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public double myPow(double x, int n) {
        long N = n;
        int sign = 1;
        if(N < 0) {
            sign = -1;
            N = -N;
        }
        double result = 1.0;
        while (N > 0) {
            if((N & 1) == 1) {
                result *= x;
            }
            N >>= 1;
            x *= x;
        }
        return sign == 1 ? result : 1.0 / result;
    }
    public double myPow0(double x, int n) {
        if(n == 0) {
            return 1;
        }
        if(n < 0) {
            return 1. / (n == Integer.MIN_VALUE ? myPow(x, Integer.MAX_VALUE) * x : myPow(x, -n));
        }
        double r = myPow(x, n/2);
        r *= r;
        if((n & 1) == 1) {
            r *= x;
        }
        return r;
    }

    public static void main(String[] args) {
        List<Double> tests = Arrays.asList(
                1.1,
                0.99
        );
        List<Integer> ns = Arrays.asList(
                2,
                100
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.myPow(tests.get(i), ns.get(i)));
        }
    }
}