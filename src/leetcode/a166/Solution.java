package leetcode.a166;

import java.util.*;

public class Solution {
    public String fractionToDecimal(int numeratori, int denominatori) {
        long numerator = numeratori;
        long denominator = denominatori;
        if(denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int sign = 1;
        if(numerator < 0) {
            sign = -1;
            numerator = -numerator;
        }
        String decimal = String.format("%s%d", sign == 1 ? "" : "-", numerator / denominator);
        numerator %= denominator;
        if(numerator == 0) {
            return decimal;
        }
        List<Long> fraction = new ArrayList<>();
        Map<Long, Integer> map = new HashMap<>();
        for(int i = 0; ; ++ i) {
            if(map.containsKey(numerator)) {
                break;
            }
            map.put(numerator, i);
            numerator *= 10;
            fraction.add(numerator / denominator);
            numerator %= denominator;
            if(numerator == 0) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(decimal);
        sb.append('.');
        if(numerator == 0) {
            for(long n : fraction) {
                sb.append(n);
            }
        }
        else {
            for(int i = 0; i < map.get(numerator); ++ i) {
                sb.append(fraction.get(i));
            }
            sb.append('(');
            for(int i = map.get(numerator); i < fraction.size(); ++ i) {
                sb.append(fraction.get(i));
            }
            sb.append(')');
        }
        return sb.toString();
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