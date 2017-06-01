package leetcode.a241;

import java.util.*;

public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        if(input.isEmpty()) {
            return Arrays.asList();
        }
        List<Integer>[][] dp = new List[input.length()][input.length()];
        return new ArrayList<>(dfs(input, 0, input.length() - 1, dp));
    }

    int calculate(int a, int b, char op) {
        switch(op) {
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':
                return a/b;
        }
        return 0;
    }

    List<Integer> dfs(String input, int begin, int end, List<Integer>[][] dp) {
        if(dp[begin][end] == null) {
            dp[begin][end] = new ArrayList<>();
            int n = 0;
            int i = begin;
            for(; i <= end && Character.isDigit(input.charAt(i)); ++ i) {
                n = n*10 + (input.charAt(i) - '0');
            }
            if(i > end) {
                dp[begin][end].add(n);
            } else {
                for(; i < end; ++ i) {
                    if(Character.isDigit(input.charAt(i))) {
                        continue;
                    }
                    List<Integer> left = dfs(input, begin, i-1, dp);
                    List<Integer> right = dfs(input, i+1, end, dp);
                    for(int a : left) {
                        for(int b : right) {
                            dp[begin][end].add(calculate(a, b, input.charAt(i)));
                        }
                    }
                }
            }
        }
        return dp[begin][end];
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "2-1-1",
                "2*3-4*5"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.diffWaysToCompute(tests.get(i)));
        }
    }
}