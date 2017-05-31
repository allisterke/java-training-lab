package wap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by ubuntu on 5/20/17.
 */
public class FastPower {
    static long MOD = 100003;

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        long m = scanner.nextLong();
//        long n = scanner.nextLong();
//        System.out.println(solve(m, n));
        Random r = new Random();
        for(int i = 0; i < 100; ++ i) {
            long m = r.nextInt(Integer.MAX_VALUE);
            long n = r.nextInt(Integer.MAX_VALUE);
            System.out.format("%d, %d, %d, %s\n", i, m, n, solve(m, n) == solve1(m, n));
        }
    }

    static long solve(long m, long n) {
        return (MOD + fastPower(m, n) - (m % MOD) * fastPower(m-1, n-1) % MOD) % MOD;
    }

    static long solve1(long m, long n) {
        return (MOD + fastPowerRecursive(m, n) - (m % MOD) * fastPowerRecursive(m-1, n-1) % MOD) % MOD;
    }

    static long fastPower(long m, long n) {
        m %= MOD;
        long r = 1;
        while (n != 0) {
            if((n & 1) == 1) {
                r *= m;
                r %= MOD;
            }
            m *= m;
            m %= MOD;

            n >>= 1;
        }
        return r;
    }

    static long fastPowerRecursive(long m, long n) {
        if(n == 0) {
            return 1;
        }
        m %= MOD;
        long r = fastPowerRecursive(m, n/2);
        r = r * r % MOD;
        if((n & 1) == 1) {
            r = r * m % MOD;
        }
        return r;
    }
}
