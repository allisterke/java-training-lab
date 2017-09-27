package practise;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NonSelfChosenPossibility {
    static List<Double> cache = new ArrayList<>();
    static {
        cache.add(0.);
        cache.add(0.5);
        while (true) {
            double p = (cache.get(cache.size() - 2) + cache.get(cache.size() - 1) * cache.size()) / (cache.size() + 1);
            cache.add(p);
            if(Math.abs(p - cache.get(cache.size() - 2)) < 1e-4) {
                break;
            }
        }
    }
    public static double getPossibility(int n) {
        double p1 = 0, p2 = 0.5;
        for(int i = 3; i <= n; ++ i) {
            double p = 1.0 * (p1 + (i-1)*p2) / i;
//            System.out.println(p);
            p1 = p2;
            p2 = p;
        }
        return p2;
    }
    public static double getPossibility0(int n) {
        if(n == 1) {
            return 0;
        }
        else if(n == 2) {
            return 0.5;
        }
        BigInteger f1 = BigInteger.ZERO, f2 = BigInteger.ONE, f3 = null;
        for(int i = 3; i <= n; ++ i) {
            f3 = BigInteger.valueOf(i-1).multiply(f2.add(f1));
            f1 = f2;
            f2 = f3;
        }
        BigInteger g = BigInteger.ONE;
        for(int i = 1; i <= n; ++ i) {
            g = g.multiply(BigInteger.valueOf(i));
        }
        return g.subtract(f2).multiply(BigInteger.valueOf(100000)).divide(g).doubleValue()/100000;
    }

    static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.format("%.4f\n", getPossibility(scanner.nextInt()));
    }

    public static void main(String[] args) {

//        System.out.println(getPossibility(3));
//        System.out.println(getPossibility(4));
//        System.out.println(getPossibility(5));
//        System.out.println(getPossibility(6));
//        System.out.println(getPossibility(40));
//        System.out.println(getPossibility(400));
//        System.out.println(getPossibility(4000));
//        System.out.println(getPossibility(40000));
//        System.out.println(getPossibility(400000));

        for (int i = 0; i < cache.size(); ++ i) {
            System.out.format("%d\t%f\n", i+1, cache.get(i));
        }
    }
}
