package leetcode.a770;

import java.util.*;

public class Solution {
    String expression;
    int i;
    Map<String, Integer> vars;

    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        this.expression = expression;
        i = 0;
        vars = getVars(evalvars, evalints);
        Expression exp = parse();
        Map<List<String>, Integer> coMap = new HashMap<>();
        for (Item item : exp.items) {
            if(item.coeffient != 0) {
                List<String> varList = item.vars;
                Collections.sort(varList);
                if(!coMap.containsKey(varList)) {
                    coMap.put(varList, 0);
                }
                coMap.put(varList, coMap.get(varList) + item.coeffient);
            }
        }
        List<List<String>> keys = new ArrayList<>(coMap.keySet());
        Collections.sort(keys, (a, b) -> {
            if(a.size() != b.size()) {
                return - Integer.compare(a.size(), b.size());
            }
            else {
                for (int i = 0; i < a.size(); ++ i) {
                    if(!a.get(i).equals(b.get(i))) {
                        return a.get(i).compareTo(b.get(i));
                    }
                }
                return 0;
            }
        });
        List<String> result = new ArrayList<>();
        for (List<String> key : keys) {
            int co = coMap.get(key);
            if(co != 0) {
                StringBuilder builder = new StringBuilder();
                builder.append(co);
                for (String var : key) {
                    builder.append('*');
                    builder.append(var);
                }
                result.add(builder.toString());
            }
        }
        return result;
    }

    static class Item {
        int coeffient = 1;
        List<String> vars = new ArrayList<>();
    }

    static class Expression {
        List<Item> items = new ArrayList<>();

        Expression() {

        }

        Expression(int n) {
            Item item = new Item();
            item.coeffient = n;
            items.add(item);
        }

        Expression(String var) {
            Item item = new Item();
            item.vars.add(var);
            items.add(item);
        }
    }

    Expression parse() {
        Expression first = nextExpression();
        String op1 = nextOperator();
        if (op1 == null || op1.equals(")")) {
            return first;
        }
        Expression exp1 = nextExpression();
        while (true) {
            String op2 = nextOperator();
            if (op2 == null || op2.equals(")")) {
                break;
            }
            Expression exp2 = nextExpression();
            if (!op2.equals("*")) {
                first = calculate(first, exp1, op1);
                op1 = op2;
                exp1 = exp2;
            }
            else {
                exp1 = calculate(exp1, exp2, op2);
            }
        }
        return calculate(first, exp1, op1);
    }

    Expression calculate(Expression e1, Expression e2, String op) {
        if(op.equals("+")) {
            e1.items.addAll(e2.items);
            return e1;
        }
        else if(op.equals("-")) {
            for (Item item : e2.items) {
                item.coeffient = -item.coeffient;
            }
            e1.items.addAll(e2.items);
            return e1;
        }
        else { // *
            Expression exp = new Expression();
            for (Item item1 : e1.items) {
                for (Item item2 : e2.items) {
                    exp.items.add(multiply(item1, item2));
                }
            }
            return exp;
        }
    }

    Item multiply(Item item1, Item item2) {
        Item item = new Item();
        item.coeffient = item1.coeffient * item2.coeffient;
        item.vars.addAll(item1.vars);
        item.vars.addAll(item2.vars);
        return item;
    }

    String nextOperator() {
        return next();
    }

    Expression nextExpression() {
        String token = next();
        if(token.equals("(")) {
            return parse();
        }
        else {
            if(Character.isLowerCase(token.charAt(0))) {
                if (vars.containsKey(token)) {
                    return new Expression(vars.get(token));
                }
                else {
                    return new Expression(token);
                }
            }
            else {
                return new Expression(Integer.valueOf(token));
            }
        }
    }

    String next() {
        for(; i < expression.length() && expression.charAt(i) == ' '; ++ i) ;
        if(i >= expression.length()) {
            return null;
        }
        if (Character.isDigit(expression.charAt(i))) {
            int j = i + 1;
            for (; j < expression.length() && Character.isDigit(expression.charAt(j)); ++ j) ;
            String val = expression.substring(i, j);
            i = j;
            return val;
        }
        if (Character.isLowerCase(expression.charAt(i))) {
            int j = i + 1;
            for (; j < expression.length() && Character.isLowerCase(expression.charAt(j)); ++ j) ;
            String var = expression.substring(i, j);
            i = j;
            return var;
        }
        i += 1;
        return expression.substring(i-1, i);
    }

    Map<String, Integer> getVars(String[] evalvars, int[] evalints) {
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < evalints.length; ++ i) {
            map.put(evalvars[i], evalints[i]);
        }
        return map;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
//                "e + 8 - a + 5",
//                "e - 8 + temperature - pressure",
//                "(e + 8) * (e - 8)",
//                "7 - 7",
                "a * b * c + b * a * c * 4",
                "((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))"
        );
        List<String[]> vars = Arrays.asList(
                new String[][] {
//                        {"e"},
//                        {"e", "temperature"},
//                        {},
//                        {},
                        {},
                        {}
                }
        );
        List<int[]> vals = Arrays.asList(
                new int[][] {
//                        {1},
//                        {1, 12},
//                        {},
//                        {},
                        {},
                        {}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.basicCalculatorIV(tests.get(i), vars.get(i), vals.get(i)));
        }
    }
}