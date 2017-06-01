package leetcode.a227;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public int calculate(String s) {
        Scanner scanner = new Scanner(
                new ByteArrayInputStream(
                        s.replaceAll("(\\+|-|\\*|/)", " $1 ").getBytes()));
        if(scanner.hasNextInt()) {
            int a = 0;
            String op = "+";
            int b = scanner.nextInt();

            while (scanner.hasNext()) {
                String nextOp = scanner.next();
                int c = scanner.nextInt();
                if(nextOp.equals("*") || nextOp.equals("/")) {
                    b = calculate(b, c, nextOp);
                }
                else {
                    a = calculate(a, b, op);
                    b = c;
                    op = nextOp;
                }
            }
            return calculate(a, b, op);
        }
        return 0;
    }

    int calculate(int a, int b, String op) {
        switch (op) {
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "3+2*2",
                " 3/2 ",
                " 3+5 / 2 "
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.calculate(tests.get(i)));
        }
    }
}