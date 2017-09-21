package practise;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class FloorSum {
    static BigInteger SQRT2_HALF = psqrt2();
    static BigInteger SQRT2 = SQRT2_HALF.multiply(BigInteger.valueOf(2));
    static BigInteger ONE_MINUS_SQRT2_HALF = BigInteger.valueOf(10).pow(101).subtract(SQRT2_HALF);
    static BigInteger COEFFIENT = BigInteger.valueOf(10).pow(101);

    static BigInteger psqrt2() {
        BigInteger target = BigInteger.valueOf(10).pow(202).divide(BigInteger.valueOf(2));
        BigInteger left = BigInteger.valueOf(0), right = target;
        while (left.compareTo(right) < 0) {
            BigInteger mid = left.add(right).divide(BigInteger.valueOf(2));
            if(mid.multiply(mid).compareTo(target) >= 0) {
                right = mid;
            }
            else {
                left = mid.add(BigInteger.ONE);
            }
        }
        return left;
    }

    static BigInteger floorSum(BigInteger n) {
        if(n.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        else {
            BigInteger m = n.multiply(SQRT2).divide(COEFFIENT);
            BigInteger k = m.multiply(ONE_MINUS_SQRT2_HALF).divide(COEFFIENT);
            return m.multiply(m.add(BigInteger.ONE)).divide(BigInteger.valueOf(2))
                    .subtract(k.multiply(k.add(BigInteger.ONE)))
                    .subtract(floorSum(k));
        }
    }

    static long floorSum(int n) {
        if (n == 0) {
            return 0;
        }
        else {
            int m = (int)(Math.sqrt(2)*n);
            int k = (int)(m/(Math.sqrt(2)+2));
            return m*(m+1)/2 - k*(k+1) - floorSum(k);
        }
    }

    static String floorSum(String n) {
        return floorSum(new BigInteger(n)).toString();
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                5, 77, 1000*1000*1000*20
        );
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.println(floorSum(BigInteger.valueOf(tests.get(i))));
            System.out.println(floorSum(tests.get(i)));
        }
        System.out.println(floorSum(BigInteger.valueOf(10).pow(100)).toString());
//        System.out.println(floorSum(77));
//        floorSum(777777);
    }
}
