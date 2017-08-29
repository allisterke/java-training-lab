package practise;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by ally on 8/29/2017.
 */
public class FastOne {
    int solve(String binary) {
        int[][] dp = new int[binary.length() + 1][2];
        for(int i = 1; i <= binary.length(); ++ i) {
            if(binary.charAt(i-1) == '0') {
                dp[i][0] = dp[i-1][0] + 1;
                dp[i][1] = Math.min(dp[i][0] + 2, dp[i-1][1] + 2);
            }
            else {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = dp[i][0] + 2;
                int j = i-1;
                for(; j > 0 && binary.charAt(j-1) == '1'; -- j) ;
                if(j == 0) {
                    dp[i][1] = Math.min(dp[i][1], i+2);
                }
                else {
                    dp[i][1] = Math.min(dp[i][1], dp[j-1][1] + i - j + 2);
                }
            }
        }
        return dp[binary.length()][0];
    }

    int solve(int n) {
        if(n == 1) {
            return 0;
        }
        if(n % 2 == 0) {
            return solve(n/2) + 1;
        }
        else {
            return Math.min(solve(n+1), solve(n-1)) + 1;
        }
    }

    String getBinaryString(String n) {
        BigInteger bi = new BigInteger(n);
        StringBuilder sb = new StringBuilder();
        BigInteger TWO = BigInteger.valueOf(2);
        while (!bi.equals(BigInteger.ZERO)) {
            sb.append(bi.mod(TWO).intValue());
            bi = bi.divide(TWO);
        }
        String s = sb.reverse().toString();
        return s;
    }

    int answer(String n) {
        String s = getBinaryString(n);
//        System.out.println(s + "\t" + solve(s));
        return solve(s);
    }

    static void test() {
        FastOne fo = new FastOne();
        Random r = new Random();
        for(int i = 0; i < 10; ++ i) {
            int n = r.nextInt(1024) + 10;
            if(fo.solve(n) != fo.answer(String.valueOf(n))) {
                System.out.println(n);
                break;
            }
        }
    }

    static void run() {
        FastOne fo = new FastOne();
        System.out.println(fo.answer("185"));
        System.out.println(fo.solve(185));
    }

    public static void main(String[] args) {
        test();
    }
}
