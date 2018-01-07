package leetcode.a760;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        Integer[] ai = argsort(A);
        Integer[] bi = argsort(B);
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; ++ i) {
            result[ai[i]] = bi[i];
        }
        return result;
    }

    Integer[] argsort(int[] A) {
        Integer[] ai = new Integer[A.length];
        for (int i = 0; i < ai.length; ++ i) {
            ai[i] = i;
        }
        Arrays.sort(ai, Comparator.comparingInt(i -> A[i]));
        return ai;
    }

    public static void main(String[] args) {
        List<int[][]> tests = Arrays.asList(
                new int[][][] {
                        {
                                {12, 28, 46, 32, 50},
                                {50, 12, 32, 46, 28}
                        }
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(Arrays.stream(s.anagramMappings(tests.get(i)[0], tests.get(i)[1])).boxed().collect(Collectors.toList()));
        }
    }
}