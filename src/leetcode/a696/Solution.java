package leetcode.a696;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int countBinarySubstrings(String s) {
        int lastRepeat = 0;
        int total = 0;
        for(int i = 0, j = 0; i < s.length(); i = j) {
            for(j = i+1; j < s.length() && s.charAt(j) == s.charAt(i); ++ j) ;
            int repeat = j - i;
            total += Math.min(lastRepeat, repeat);
            lastRepeat = repeat;
        }
        return total;
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "00110011",
                "10101"
        );
        List<Integer> results = Arrays.asList(
                6,
                4
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.countBinarySubstrings(tests.get(i)));
        }
    }
}