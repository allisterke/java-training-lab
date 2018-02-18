package leetcode.a786;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Solution {

    static class Fraction implements Comparable<Fraction> {
        int p, q;
        double f;

        public Fraction(int p, int q) {
            this.p = p;
            this.q = q;
            this.f = 1.0 * p / q;
        }

        @Override
        public int compareTo(Fraction fraction) {
            return Double.compare(this.f, fraction.f);
        }
    }

    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double lo = 0, hi = 1.0;
        while (Math.abs(lo - hi) >= 1e-8) {
            double mid = (lo + hi) / 2;
            int count = 0;
            for (int i = 0; i < A.length; ++ i) {
                int floor = (int) Math.floor(mid * A[i]);
                int index = Arrays.binarySearch(A, 0, i, floor);
                if (index >= 0) {
                    count += index + 1;
                }
                else {
                    count += -index - 1;
                }
            }
            if(count >= K) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        for (int i = 0; i < A.length; ++ i) {
            for (int j = 0; j < i; ++ j) {
                if(Math.abs(1.0 * A[j] / A[i] - lo) < 1e-8) {
                    return new int[] {A[j], A[i]};
                }
            }
        }
        return null;
    }

    public int[] kthSmallestPrimeFraction1(int[] A, int K) {
        Fraction[] fractions = getFractions(A);

        -- K;
        nthElement(fractions, K, 0, fractions.length);

        return new int[] {fractions[K].p, fractions[K].q};
    }

    void nthElement(Fraction[] fractions, int K, int begin, int end) {
        if(begin == end - 1) {
            return;
        }
        {
            int mid = (begin + end) / 2;
            Fraction tmp = fractions[begin];
            fractions[begin] = fractions[mid];
            fractions[mid] = tmp;
        }

        int i = begin, j = end;
        Fraction pivot = fractions[begin];
        for (int k = i + 1; k < j; ++ k) {
            if(fractions[k].compareTo(pivot) < 0) {
                fractions[i ++] = fractions[k];
            }
            else if(fractions[k].compareTo(pivot) > 0) {
                Fraction tmp = fractions[j - 1];
                fractions[j - 1] = fractions[k];
                fractions[k] = tmp;
                -- k;
                -- j;
            }
        }
        for (int k = i; k < j; ++ k) {
            fractions[k] = pivot;
        }
        if (K < i) {
            nthElement(fractions, K, begin, i);
        }
        else if(K >= j) {
            nthElement(fractions, K, j, end);
        }
        else {
            return;
        }
    }

    Fraction[] getFractions(int[] A) {
        int total = A.length * (A.length - 1) / 2;
        Fraction[] fractions = new Fraction[total];
        for(int i = 1, index = 0; i < A.length; ++ i) {
            for (int j = 0; j < i; ++ j) {
                fractions[index ++] = new Fraction(A[j], A[i]);
            }
        }
        return fractions;
    }

    public int[] kthSmallestPrimeFraction0(int[] A, int K) {
        int total = A.length * (A.length - 1) / 2;
        if (K * 2 > total) {
            return kthLargestPrimeFraction(A, K);
        }

        double[][] matrix = new double[A.length][A.length];
        for (int i = 0; i < A.length; ++ i) {
            for (int j = 0; j < A.length; ++ j) {
                matrix[i][j] = 1.0 * A[i] / A[j];
            }
        }
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(
                Comparator.comparingDouble(a -> matrix[a.get(0)][a.get(1)])
        );
        for (int i = 1; i < A.length; ++ i) {
            queue.add(Arrays.asList(0, i));
        }
        while (K > 1) {
            List<Integer> top = queue.poll();
            int x = top.get(0) + 1;
            int y = top.get(1);
            if(x != y) {
                queue.add(Arrays.asList(x, y));
            }
            -- K;
        }
        List<Integer> a = queue.poll();
        return new int[] {A[a.get(0)], A[a.get(1)]};
    }

    int[] kthLargestPrimeFraction(int[] A, int K) {
        int total = A.length * (A.length - 1) / 2;
        K = total - K + 1;
        double[][] matrix = new double[A.length][A.length];
        for (int i = 0; i < A.length; ++ i) {
            for (int j = 0; j < A.length; ++ j) {
                matrix[i][j] = 1.0 * A[i] / A[j];
            }
        }
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(
                Comparator.comparingDouble(a -> - matrix[a.get(0)][a.get(1)])
        );
        for (int i = 1; i < A.length; ++ i) {
            queue.add(Arrays.asList(i-1, i));
        }
        while (K > 1) {
            List<Integer> top = queue.poll();
            int x = top.get(0) - 1;
            int y = top.get(1);
            if(x >= 0) {
                queue.add(Arrays.asList(x, y));
            }
            -- K;
        }
        List<Integer> a = queue.poll();
        return new int[] {A[a.get(0)], A[a.get(1)]};
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 2, 3, 5},
                        {1, 7}
                }
        );
        List<Integer> ks = Arrays.asList(
                3,
                1
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.kthSmallestPrimeFraction(tests.get(i), ks.get(i))).boxed().collect(Collectors.toList()));
        }
    }
}