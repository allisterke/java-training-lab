package leetcode.a150;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static String[] operators = new String[] { "+", "-", "*", "/" };
    static boolean isOperator(String token) {
        for(String op : operators) {
            if(op.equals(token)) {
                return true;
            }
        }
        return false;
    }
    static int calculate(int a, int b, String op) {
        switch (op) {
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
            default:
                return 0;
        }
    }
    public int evalRPN(String[] tokens) {
        java.util.Deque<Integer> stack = new ArrayDeque<Integer>();
        for(String token : tokens) {
            if(!isOperator(token)) {
                stack.push(Integer.valueOf(token));
            }
            else {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(calculate(a, b, token));
            }
        }
        return stack.pop();
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