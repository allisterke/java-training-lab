package leetcode.a306;

import java.util.Arrays;
import java.util.List;

public class Solution {
    boolean checkMatched(StringBuilder sb, String num, int begin, int end) {
        if(end - begin != sb.length() || end > num.length()) {
            return false;
        }
        for(int i = 0; i < sb.length(); ++ i) {
            if(sb.charAt(i) != num.charAt(begin + i)) {
                return false;
            }
        }
        return true;
    }
    public boolean isAdditiveNumber(String num) {
        StringBuilder sb = new StringBuilder();
        long a = 0;
        for(int i = 0; i < num.length(); ++ i) {
            a = a*10 + (num.charAt(i) - '0');
            long b = 0;
            for(int j = i+1; j < num.length(); ++ j) {
                b = b*10 + (num.charAt(j) - '0');
                boolean allMatch = false;
                long ta = a, tb = b;
                for(int k = j+1; k < num.length(); allMatch = true) {
                    long tc = ta + tb;
                    sb.setLength(0);
                    sb.append(tc);
                    if (!checkMatched(sb, num, k, k+sb.length())) {
                        allMatch = false;
                        break;
                    } else {
                        ta = tb;
                        tb = tc;
                        k += sb.length();
                    }
                }
                if(allMatch) {
                    return true;
                }
                if(j == i+1 && b == 0) {
                    break;
                }
            }
            if(i == 0 && a == 0) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "112358",
                "199100199",
                "001",
                "121474836472147483648"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.isAdditiveNumber(tests.get(i)));
        }
    }
}