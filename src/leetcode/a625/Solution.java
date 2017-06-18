package leetcode.a625;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solution {
    public int smallestFactorization(int a) {
        return smallestFactorization(a, 0);
    }

    int smallestFactorization1(int a, int d) {
        if(a < 10) {
            long c = d * 10L + a;
            return c > Integer.MAX_VALUE ? 0 : (int)c;
        }
        int f = 0;
        for(int n = 2; n <= 9; ++ n) {
            if(a % n == 0) {
                long k = d * 10L + n;
                if (k <= Integer.MAX_VALUE) {
                    int m = smallestFactorization1(a / n, (int)k);
                    if(m != 0 && (f == 0 || m < f)) {
                        f = m;
                    }
                }
            }
        }
        return f;
    }

    int smallestFactorization(int a, int d) {
        if(d >= 11) {
            return 0;
        }
        if(a < 10) {
            return a;
        }
        int f = 0;
        for(int n = 2; n <= 9; ++ n) {
            if(a % n == 0) {
                int b = smallestFactorization(a / n, d+1);
                if(b != 0) {
                    long c = b * 10L + n;
                    if(c <= Integer.MAX_VALUE && (f == 0 || c < f)) {
                        f = (int)c;
                        if(f < 0) {
                            break;
                        }
                    }
                }
            }
        }
        return f;
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                3000000
        );
        List results = Arrays.asList();

        Solution s = new Solution();
//        for(int i = 0; i < tests.size(); ++ i) {
//            System.out.print("case ");
//            System.out.println(i);
//            System.out.println(s.smallestFactorization(tests.get(i), 0));
//            System.out.println(s.smallestFactorization1(tests.get(i), 0));
//        }
        for(int i = 0; i < 10; ++ i) {
            int n = new Random().nextInt((int)1e4);
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.smallestFactorization(n, 0));
            System.out.println(s.smallestFactorization1(n, 0));
        }
    }
}