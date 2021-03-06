package leetcode.a679;

import java.util.*;

public class Solution {
    static long gcd(long a, long b) {
        if(a < 0) {
            a = -a;
        }
        if(b < 0) {
            b = -b;
        }
        if(a < b) {
            return gcd(b, a);
        }
        if(b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }

    static class Fraction implements Comparable<Fraction> {
        long n, d;

        public Fraction(long n, long d) {
            long g = gcd(n, d);
            this.n = n / g;
            this.d = d / g;
        }

        Fraction plus(Fraction b) {
            long n1 = n * b.d + d * b.n;
            long d1 = d * b.d;
            return new Fraction(n1, d1);
        }

        Fraction minus(Fraction b) {
            long n1 = n * b.d - d * b.n;
            long d1 = d * b.d;
            return new Fraction(n1, d1);
        }

        Fraction multiply(Fraction b) {
            long n1 = n * b.n;
            long d1 = d * b.d;
            return new Fraction(n1, d1);
        }

        Fraction divide(Fraction b) {
            long n1 = n * b.d;
            long d1 = d * b.n;
            return new Fraction(n1, d1);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Fraction) {
                Fraction o = (Fraction)obj;
                return n == o.n && d == o.d;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(n) * 31 + Long.hashCode(d);
        }

        @Override
        public int compareTo(Fraction o) {
            return Double.compare(1.0 * n / d, 1.0 * o.n / o.d);
        }

        final static Fraction ZERO = new Fraction(0, 1);
        final static Fraction ONE = new Fraction(1, 1);
    }

    private int[] nextPermutation(int[] nums) {
        int k = nums.length - 1;
        for(; k > 0 && nums[k-1] >= nums[k]; -- k);
        if(k <= 0) {
            return null;
        }

        int index = k-1;
        for(k = nums.length - 1; nums[k] <= nums[index]; -- k);
        int tmp = nums[k];
        nums[k] = nums[index];
        nums[index] = tmp;

        for(++ index, k = nums.length - 1; index < k; ++ index, -- k) {
            tmp = nums[k];
            nums[k] = nums[index];
            nums[index] = tmp;
        }

        return nums;
    }

    static class Pair {
        Fraction f;
        String s;

        static Pair makePair(Fraction f, String s) {
            Pair p = new Pair();
            p.f = f;
            p.s = s;
            return p;
        }
    }

    Collection<Pair> permutate(int[] nums, int i, int j) {
        Collection<Pair> p = new ArrayList<>();
        if(i == j) {
            p.add(Pair.makePair(new Fraction(nums[i], 1), String.valueOf(nums[i])));
        }
        else
        for(int k = i; k < j; ++ k) {
            Collection<Pair> left = permutate(nums, i, k);
            Collection<Pair> right = permutate(nums, k+1, j);
            for (Pair p1 : left) {
                for (Pair p2: right) {
                    p.add(Pair.makePair(p1.f.plus(p2.f), "(" + p1.s + "+" + p2.s + ")"));
                    p.add(Pair.makePair(p1.f.minus(p2.f), "(" + p1.s + "-" + p2.s + ")"));
                    p.add(Pair.makePair(p1.f.multiply(p2.f), "(" + p1.s + "*" + p2.s + ")"));
                    if(!p2.f.equals(Fraction.ZERO)) {
                        p.add(Pair.makePair(p1.f.divide(p2.f), "(" + p1.s + "/" + p2.s + ")"));
                    }
                }
            }
        }
        return p;
    }

    boolean permutate(int[] nums) {
        boolean exist = false;
        Fraction f = new Fraction(24, 1);
        for(Pair p : permutate(nums, 0, nums.length - 1)) {
            if(p.f.equals(f)) {
                System.out.println(p.s);
                exist = true;
            }
        }
        return exist;
    }

    public boolean judgePoint24(int[] nums) {
        boolean exist = false;
        Arrays.sort(nums);
        do {
//            Collection<Fraction> cf = getResults(nums, 0, 3);

//            if (cf.contains(new Fraction(24, 1))) {
//                return true;
//            }
            exist |= permutate(nums);
            nums = nextPermutation(nums);
        } while (nums != null);
        return exist;
    }

    public Collection<Fraction> getResults(int[] nums, int i, int j) {
        if(i == j) {
            return Arrays.asList(new Fraction(nums[i], 1));
        }
        else {
            Set<Fraction> result = new HashSet<>();
            for(int k = i; k < j; ++ k) {
                Collection<Fraction> left = getResults(nums, i, k);
                Collection<Fraction> right = getResults(nums, k+1, j);
                for(Fraction f1 : left) {
                    for (Fraction f2 : right) {
                        result.add(f1.plus(f2));
                        result.add(f1.minus(f2));
                        result.add(f1.multiply(f2));
                        if(!f2.equals(Fraction.ZERO)) {
                            result.add(f1.divide(f2));
                        }
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[] {4, 1, 8, 7},
                new int[] {1, 2, 1, 2},
                new int[] {1, 5, 5, 5}
        );
        List<Boolean> results = Arrays.asList(
                true,
                false
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.judgePoint24(tests.get(i)));
        }
    }
}