package leetcode.a795;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int total = 0;
        for (int i = 0; i < A.length; ++ i) {
            if (A[i] > R) {
                continue;
            }
            int j = i + 1;
            for (; j < A.length && A[j] <= R; ++ j) ;
            total += numSubarrayBoundedMax(A, i, j, L, R);
        }
        return total;
    }

    int numSubarrayBoundedMax(int[] A, int begin, int end, int L, int R) {
        int length = end - begin;
        int total = length * (length + 1) / 2;
        for (int i = begin; i < end; ) {
            if(A[i] >= L) {
                continue;
            }
            int j = i+1;
            for (; j < end && A[j] < L; ++ j) ;
            length = j - i;
            total -= length * (length + 1) / 2;
            i = j;
        }
        return total;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}