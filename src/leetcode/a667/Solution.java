package leetcode.a667;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int[] constructArray(int n, int k) {
        int[] a = new int[n];
        if(k == 1) {
            for(int i = 1; i <= n; ++ i) {
                a[i-1] = i;
            }
        }
        else {
            for (int i = k + 1; i <= n; ++i) {
                a[i - k - 1] = i;
            }
            for (int i = n - k, p = 1, q = k, d = 0; i < n; ++i) {
                if (d == 0) {
                    a[i] = p++;
                    d = 1;
                } else {
                    a[i] = q--;
                    d = 0;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(3, 1),
                Arrays.asList(3, 2),
                Arrays.asList(9999, 9998)
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.constructArray(tests.get(i).get(0), tests.get(i).get(1))).boxed().collect(Collectors.toList()));
        }
    }
}