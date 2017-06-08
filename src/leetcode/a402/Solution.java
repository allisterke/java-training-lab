package leetcode.a402;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Solution {
    public String removeKdigits(String num, int k) {
        k = num.length() - k;
        if(k <= 0) {
            return "0";
        }
        Deque<Character> reserve = new ArrayDeque<>();
        for(int i = 0; i < num.length(); ++ i) {
            char c = num.charAt(i);
            while(!reserve.isEmpty() && c < reserve.peek()
                    && reserve.size() + num.length() - i > k) {
                reserve.pop();
            }
            reserve.push(c);
        }
        while(reserve.size() > k) {
            reserve.pop();
        }
        StringBuilder sb = new StringBuilder();
        while(!reserve.isEmpty()) {
            sb.append(reserve.pop());
        }
        while (sb.length() > 1 && sb.charAt(sb.length() - 1) == '0') {
            sb.setLength(sb.length() - 1);
        }
        return sb.reverse().toString();
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