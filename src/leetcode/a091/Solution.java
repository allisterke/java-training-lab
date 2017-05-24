package leetcode.a091;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numDecodings(String s) {
        if(s.isEmpty()) {
            return 0;
        }
        int a = 1, b = s.charAt(0) == '0' ? 0 : 1, c = 0;
        for(int i = 1; i < s.length() && (a != 0 || b != 0); ++ i) {
            if(s.charAt(i) != '0') {
                c += b;
            }
            int lastTwoCharacters = (s.charAt(i-1) - '0') * 10 + (s.charAt(i) - '0');
            if(lastTwoCharacters >= 10 && lastTwoCharacters <= 26) {
                c += a;
            }
            a = b;
            b = c;
            c = 0;
        }
        return b;
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