package leetcode.a769;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int maxChunksToSorted(int[] arr) {
        int chunks = 0;
        for (int i = 0; i < arr.length; ++ i) {
            int end = arr[i];
            for (int j = i; j <= end; ++ j) {
                end = Math.max(end, arr[j]);
            }
            ++ chunks;
            i = end;
        }
        return chunks;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {4, 3, 2, 1, 0},
                        {1, 0, 2, 3, 4}
                }
        );
        List results = Arrays.asList(
                1,
                4
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.maxChunksToSorted(tests.get(i)));
        }
    }
}