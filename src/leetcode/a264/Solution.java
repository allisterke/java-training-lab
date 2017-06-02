package leetcode.a264;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int[] primes = new int[] {2, 3, 5};
        int[] positions = new int[primes.length];
        Arrays.fill(positions, 0);
        for(int i = 1; i < n; ++ i) {
            int next = Integer.MAX_VALUE;
            for(int j = 0; j < primes.length; ++ j) {
                for(; uglyNumbers[positions[j]] * primes[j] <= uglyNumbers[i-1]; ++ positions[j])
                    ;
                next = Math.min(next, uglyNumbers[positions[j]] * primes[j]);
            }
            uglyNumbers[i] = next;
        }
        return uglyNumbers[n-1];
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}