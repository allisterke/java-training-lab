package leetcode.a775;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public boolean isIdealPermutation(int[] A) {
        return countLocalReverse(A) == countGlobalReverse(A.clone(), 0, A.length);
    }

    int countLocalReverse(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length - 1; ++ i) {
            if (A[i] > A[i+1]) {
                ++ count;
            }
        }
        return count;
    }

    int countGlobalReverse(int[] A, int begin, int end) {
        if(begin + 1 == end) {
            return 0;
        }
        int mid = (begin + end) / 2;
        int c1 = countGlobalReverse(A, begin, mid);
        int c2 = countGlobalReverse(A, mid, end);
        int count = 0;
        for (int i = mid, j = begin; i < end; ++ i) {
            for (; j < mid && A[j] <= A[i]; ++ j) ;
            count += mid - j;
        }
        int[] cache = new int[end - begin];
        for (int i = begin, j = mid, k = 0; i < mid || j < end;) {
            if (i < mid && j < end && A[i] < A[j] || j >= end) {
                cache[k ++] = A[i ++];
            }
            else {
                cache[k ++] = A[j ++];
            }
        }
        for (int i = begin; i < end; ++ i) {
            A[i] = cache[i - begin];
        }
        return count + c1 + c2;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 0, 2},
                        {1, 2, 0}
                }
        );
        List<Boolean> results = Arrays.asList(
                Boolean.TRUE,
                Boolean.FALSE
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.isIdealPermutation(tests.get(i)));
        }
    }
}