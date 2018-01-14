package leetcode.a762;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int countPrimeSetBits(int L, int R) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19};
        Set<Integer> primeSet = new HashSet<>();
        for(int n : primes) {
            primeSet.add(n);
        }
        int count = 0;
        for(int i = L; i <= R; ++ i) {
            int bits = 0;
            for(int n = i; n != 0; n >>= 1) {
                if((n & 1) == 1) {
                    ++ bits;
                }
            }
            if(primeSet.contains(bits)) {
                ++ count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(6, 10),
                Arrays.asList(10, 15)
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.countPrimeSetBits(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}