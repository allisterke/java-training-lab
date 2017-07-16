package leetcode.a640;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String solveEquation(String equation) {
        String[] parts = equation.split("=");
        List<Integer> left = reduce(parts[0]);
        List<Integer> right = reduce(parts[1]);
        int c = left.get(0) - right.get(0);
        int n = right.get(1) - left.get(1);
        if(c == 0) {
            if(n == 0) {
                return "Infinite solutions";
            }
            else {
                return "No solution";
            }
        }
        else {
            return "x=" + (n / c);
        }
    }

    List<Integer> reduce(String expression) {
        int c = 0, n = 0;
        for(int i = 0; i < expression.length(); ) {
            int j = i+1;
            for(; j < expression.length() && !isOperator(expression.charAt(j)); ++ j) ;
            int end = j - 1;
            if(expression.charAt(end) != 'x') {
                n += Integer.valueOf(expression.substring(i, j));
            }
            else {
                if(end == i) {
                    c += 1;
                } else if (end == i + 1 && isOperator(expression.charAt(i))) {
                    c += expression.charAt(i) == '+' ? 1 : -1;
                }
                else {
                    c += Integer.valueOf(expression.substring(i, end));
                }
            }
            i = j;
        }
        return Arrays.asList(c, n);
    }

    boolean isOperator(char c) {
        return c == '+' || c == '-';
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "x+5-3+x=6+x-2",
                "x=x",
                "2x=x",
                "2x+3x-6x=x+2",
                "x=x+2"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.solveEquation(tests.get(i)));
        }
    }
}