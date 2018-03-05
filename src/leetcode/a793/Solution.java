package leetcode.a793;

import java.util.*;

public class Solution {
    public int preimageSizeFZF(int K) {
        long lo = 0, hi = Long.MAX_VALUE / 2;
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            long count = countFive(mid);
            if(count < K) {
                lo = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        return countFive(lo) == K ? 5 : 0;
    }

    long countFive(long n) {
        long count = 0;
        for (long k = 5; k <= n; k *= 5) {
            count += n / k;
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                0,
                5
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.preimageSizeFZF(tests.get(i)));
        }
    }
}