package leetcode.a779;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        else {
            int g = kthGrammar(N-1, (K+1)/2);
            if(g == 0) {
                if(K % 2 == 1) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else {
                if(K % 2 == 1) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> tests = Arrays.asList(
                Arrays.asList(1, 1),
                Arrays.asList(2, 1),
                Arrays.asList(2, 2),
                Arrays.asList(4, 5)
        );
        List results = Arrays.asList(0, 0, 1, 1);

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.kthGrammar(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}