package leetcode.a043;

import java.util.Arrays;
import java.util.List;

public class Solution {
    private int[] stringToReversedInteger(String s) {
        int[] ri = new int[s.length()];
        for(int i = 0; i < s.length(); ++ i) {
            ri[i] = s.charAt(s.length() - 1 - i) - '0';
        }
        return ri;
    }

    private String reversedIntegerToString(int[] n, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < end; ++ i) {
            sb.append(n[i]);
        }
        return sb.reverse().toString();
    }

    public String multiply(String num1, String num2) {
        int[] n1 = stringToReversedInteger(num1);
        int[] n2 = stringToReversedInteger(num2);
        int[] result = new int[n1.length + n2.length];
        for(int i = 0; i < n1.length; ++ i) {
            for(int j = 0; j < n2.length; ++ j) {
                result[i+j] += n1[i] * n2[j];
            }
        }
        int carry = 0;
        for(int i = 0; i < result.length; ++ i) {
            int tmp = result[i];
            result[i] = (tmp + carry) % 10;
            carry = (tmp + carry) / 10;
        }
        int end = result.length - 1;
        for(; end > 0 && result[end] == 0; -- end);
        ++ end;

        return reversedIntegerToString(result, 0, end);
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("120", "120"),
                Arrays.asList("120", "0"),
                Arrays.asList("120", "12000000000000")
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.multiply(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}