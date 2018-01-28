package leetcode.a768;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public int maxChunksToSorted(int[] arr) {
        TreeSet<Long> set = new TreeSet<>();
        for(int i = 0; i < arr.length; ++ i) {
            set.add(compose(arr[i], i));
        }
        int chunks = 0;
        for (int i = 0; i < arr.length; ++ i) {
            int max = arr[i];
            set.remove(compose(arr[i], i));
            int j = i+1;
            for (; !set.isEmpty() && max > (set.first() >> 32); ++ j) {
                max = Math.max(max, arr[j]);
                set.remove(compose(arr[j], j));
            }
            ++ chunks;
            i = j-1;
        }
        return chunks;
    }

    long compose(int num, int index) {
        long n = num;
        n <<= 32;
        n |= index;
        return n;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {5, 4, 3, 2, 1},
                        {2, 1, 3, 4, 4}
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