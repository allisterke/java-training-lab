package leetcode.a712;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] sum = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 0; i <= s1.length(); ++ i) {
            for(int j = 0; j <= s2.length(); ++ j) {
                if(i == 0 && j == 0) {
                    // ..
                }
                else if(i == 0) {
                    sum[i][j] = sum[i][j-1] + s2.charAt(j-1);
                }
                else if(j == 0) {
                    sum[i][j] = sum[i-1][j] + s1.charAt(i-1);
                }
                else {
                    if(s1.charAt(i-1) == s2.charAt(j-1)) {
                        sum[i][j] = sum[i-1][j-1];
                    }
                    else {
                        sum[i][j] = Math.min(sum[i][j-1] + s2.charAt(j-1), sum[i-1][j] + s1.charAt(i-1));
                    }
                }
            }
        }
        return sum[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("sea", "eat"),
                Arrays.asList("delete", "leet")
        );
        List<Integer> results = Arrays.asList(
                231,
                403
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minimumDeleteSum(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}