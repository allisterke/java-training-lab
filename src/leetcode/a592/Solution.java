package leetcode.a592;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    class Fraction {
        long n, d;
        long gcd(long a, long b) {
            if(b == 0) {
                return a;
            }
            if(a < b) {
                return gcd(b, a);
            }
            if(a < 0) {
                return gcd(-a, b);
            }
            if(b < 0) {
                return gcd(a, -b);
            }
            return gcd(b, a % b);
        }
        public Fraction(long n, long d) {
            if(d < 0) {
                n = - n;
                d = -d;
            }
            this.n = n;
            this.d = d;
        }
        public Fraction add(Fraction f) {
            long nn = n * f.d + d * f.n;
            long dd = d * f.d;
            long g = gcd(nn, dd);
            return new Fraction(nn / g, dd / g);
        }
        public Fraction sub(Fraction f) {
            return this.add(f.negate());
        }
        public Fraction negate() {
            return new Fraction(-n, d);
        }
        public String toString() {
            return String.format("%d/%d", n, d);
        }
    }
    public String fractionAddition(String expression) {
        Fraction result = new Fraction(0, 1);
        if(!expression.isEmpty()) {
            expression = expression.replace("+", " +").replace("-", " -").replace("/", " / ");
            Scanner scanner = new Scanner(new StringReader(expression));
            while (scanner.hasNext()) {
                long n = scanner.nextLong();
                scanner.next();
                long d = scanner.nextLong();
                result = result.add(new Fraction(n, d));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "-1/2+1/2",
                "-1/2+1/2+1/3",
                "1/3-1/2",
                "5/3+1/3"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.fractionAddition(tests.get(i)));
        }
    }
}