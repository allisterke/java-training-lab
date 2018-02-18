package leetcode.a738;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int monotoneIncreasingDigits(int N) {
        StringBuilder sb = new StringBuilder();
        sb.append(N);
        for (int i = 0; i < sb.length(); ++ i) {
            if(sb.substring(i+1, sb.length()).compareTo(repeat(sb.length() - i - 1, sb.charAt(i))) >= 0) {
                continue;
            }
            else {
                sb.setCharAt(i, (char)(sb.charAt(i) - 1));
                for(++ i; i < sb.length(); ++ i) {
                    sb.setCharAt(i, '9');
                }
                break;
            }
        }
        return Integer.valueOf(sb.toString());
    }

    String repeat(int r, char c) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < r; ++ i) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                10, 1234, 332, 120, 102
        );
        List<Integer> results = Arrays.asList(
                9, 1234, 299, 119, 99
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.monotoneIncreasingDigits(tests.get(i)));
        }
    }
}