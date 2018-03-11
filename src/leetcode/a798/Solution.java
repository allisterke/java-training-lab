package leetcode.a798;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int bestRotation(int[] A) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        int br = 0, score = 0;
        for (int i = 0; i < A.length; ++ i) {
            int diff = A[i] - i;
            if(diff <= 0) {
                ++ score;
            }
            left.putIfAbsent(diff, 0);
            left.put(diff, left.get(diff) + 1);
        }
        int last = score;
        for (int i = 0; i < A.length; ++ i) {
            int ns = last;

            int diff1 = A[i] - i;
            left.put(diff1, left.get(diff1) - 1);
            if (diff1 <= -i) {
                -- ns;
            }
            ns -= left.getOrDefault(-i, 0);

            int diff2 = A[i] - (A.length + i);
            right.putIfAbsent(diff2, 0);
            right.put(diff2, right.get(diff2) + 1);
            if (diff2 < -i) {
                ++ ns;
            }
            ns -= right.getOrDefault(-i, 0);

            if (ns > score) {
                br = i + 1;
                score = ns;
            }

            last = ns;
        }
        return br;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {4,1,4,0,0},
                        {2, 3, 1, 4, 0},
                        {1, 3, 0, 2, 4}
                }
        );
        List<Integer> results = Arrays.asList(
                3,
                0
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.bestRotation(tests.get(i)));
        }
    }
}