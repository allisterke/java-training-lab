package leetcode.a646;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> {return Integer.compare(a[1], b[1]);});
        int[] dp = new int[pairs.length];
        for(int i = 0; i < pairs.length; ++ i) {
            if(i == 0) {
                dp[i] = 1;
            }
            else {
                for(int j = 0; j < i; ++ j) {
                    if(pairs[j][1] < pairs[i][0]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0);
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