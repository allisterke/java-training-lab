package leetcode.a029;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int divide(int dividend, int divisor) {
        if(divisor == 0) {
            return Integer.MAX_VALUE;
        }
        long d1 = dividend, d2 = divisor;
        if(d2 < 0) {
            d1 = -d1;
            d2 = -d2;
        }
        int sign = 1;
        if(d1 < 0) {
            sign = -1;
            d1 = -d1;
        }
        long d3 = d2;
        for(; d3 <= d1; d3 <<= 1) ;
        if(d3 != d2) {
            d3 >>= 1;
        }
        long r = 0;
        while (true) {
            r <<= 1;
            if(d1 >= d3) {
                r |= 1;
                d1 -= d3;
            }
            if(d3 == d2) {
                break;
            }
            else {
                d3 >>= 1;
            }
        }
        return (int)(sign > 0 ? r > Integer.MAX_VALUE ? Integer.MAX_VALUE : r : -r);
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(-30, 2),
                Arrays.asList(10, 5),
                Arrays.asList(Integer.MIN_VALUE, -1),
                Arrays.asList(Integer.MAX_VALUE, -1)
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.divide(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}