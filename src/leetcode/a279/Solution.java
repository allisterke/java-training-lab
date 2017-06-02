package leetcode.a279;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numSquares(int n) {
        return numSquares(n, 0, n);
    }

    int maxSquareNotGreaterThan(int n) {
        long left = 0, right = n+1L;
        while(left < right) {
            long mid = (left + right) / 2;
            long square = mid * mid;
            if(square < n) {
                left = mid + 1;
            }
            else if(square > n) {
                right = mid;
            }
            else {
                return (int)mid;
            }
        }
        return (int)(right - 1);
    }

    int numSquares(int n, int k, int g) {
        if(n == 0) {
            return g;
        }
        if(k+1 >= g) {
            return n == 0 ? k : g;
        }
        else {
            int m = maxSquareNotGreaterThan(n);
            if(m * m == n) {
                return k+1;
            }
            else {
                for(int i = m; i > 0; -- i) {
                    int square = i * i;
                    g = Math.min(g, numSquares(n - square, k+1, g));
                    if(g == k+2) {
                        break;
                    }
                }
                return g;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                123456789,
                4,
                12,
                13,
                12479
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.numSquares(tests.get(i)));
        }
    }
}