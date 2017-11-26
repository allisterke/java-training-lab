package leetcode.a736;

import java.util.*;

public class Solution {
    int i;
    String expression;
    Map<String, List<Integer>> valueMap;

    public int evaluate(String expression) {
        valueMap = new HashMap<>();
        i = 0;
        this.expression = expression;
        return nextChar() == '(' ? parse() : getValue(nextToken());
    }

    int parse() {
        skip();
        int result = 0;
        String op = nextToken();
        if(op.equals("let")) {
            Set<String> visited = new HashSet<>();
            while (true) {
                if (nextChar() == '(') {
                    result = parse();
                    break;
                }
                String variable = nextToken();
                if(nextChar() == ')') {
                    result = getValue(variable);
                    break;
                }
                int value = 0;
                if(nextChar() == '(') {
                    value = parse();
                }
                else {
                    value = getValue(nextToken());
                }
                if(!valueMap.containsKey(variable)) {
                    valueMap.put(variable, new ArrayList<>());
                }
                List<Integer> values = valueMap.get(variable);
                if(visited.contains(variable)) {
                    values.set(values.size() - 1, value);
                }
                else {
                    values.add(value);
                }
                visited.add(variable);
            }
            for (String variable : visited) {
                List<Integer> values = valueMap.get(variable);
                values.remove(values.size() - 1);
            }
        }
        else if(op.equals("add") || op.equals("mult")) {
            int[] values = new int[2];
            for(int i = 0; i < 2; ++ i) {
                if (nextChar() != '(') {
                    values[i] = getValue(nextToken());
                } else {
                    values[i] = parse();
                }
            }
            if(op.equals("add")) {
                result = values[0] + values[1];
            }
            else {
                result = values[0] * values[1];
            }
        }
        while (expression.charAt(i) != ')') {
            ++ i;
        }
        ++ i;
        return result;
    }

    int getValue(String token) {
        if(Character.isLowerCase(token.charAt(0))) {
            List<Integer> values = valueMap.get(token);
            return values.get(values.size() - 1);
        }
        else {
            return Integer.valueOf(token);
        }
    }

    char nextChar() {
        while (expression.charAt(i) == ' ') {
            ++ i;
        }
        return expression.charAt(i);
    }

    void skip() {
        ++ i;
    }

    String nextToken() {
        StringBuilder sb = new StringBuilder();
        for(; expression.charAt(i) != ' ' && expression.charAt(i) != ')'; ++ i) {
            sb.append(expression.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
//                "(add 1 2)",
//                "(mult 3 (add 2 3))",
//                "(let x 2 (mult x 5))"
//                "(let x 2 (mult x (let x 3 y 4 (add x y))))"
                "(let x 1 y 2 x (add x y) (add x y))"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.evaluate(tests.get(i)));
        }
    }
}