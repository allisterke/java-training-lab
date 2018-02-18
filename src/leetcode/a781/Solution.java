package leetcode.a781;

import java.util.*;

public class Solution {
    public int numRabbits(int[] answers) {
        Arrays.sort(answers);
        int nr = 0;
        for (int i = 0; i < answers.length; ) {
            int j = i+1;
            for (; j < answers.length && answers[j] == answers[i]; ++ j) ;
            int d = j - i;
            nr += Math.ceil(1.0 * d / (answers[i] + 1)) * (answers[i] + 1);

            i = j;
        }
        return nr;
    }

    public static void main(String[] args) {
        List<int[]> tests = Arrays.asList(
                new int[][] {
                        {1, 1, 2},
                        {10, 10, 10},
                        {}
                }
        );
        List<Integer> results = Arrays.asList(
                5,
                11,
                0
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.numRabbits(tests.get(i)));
        }
    }
}