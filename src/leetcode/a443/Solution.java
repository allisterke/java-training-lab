package leetcode.a443;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int compress(char[] chars) {
        int nl = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < chars.length; i = j) {
            for(j = i+1; j < chars.length && chars[j] == chars[i]; ++ j) ;

            chars[nl ++] = chars[i];
            if(j > i+1) {
                sb.setLength(0);
                sb.append(j - i);
                for(int k = 0; k < sb.length(); ++ k) {
                    chars[nl + k] = sb.charAt(k);
                }
                nl += sb.length();
            }
        }
        return nl;
    }

    public static void main(String[] args) {
        List<char[]> tests = Arrays.asList(
                new char[][] {
                        "aabbccc".toCharArray(),
                        "a".toCharArray(),
                        "abbbbbbbbbbbb".toCharArray()
                }
        );
        List<Integer> results = Arrays.asList(
                6,
                1,
                4
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.compress(tests.get(i)));
            System.out.println(new String(tests.get(i)));
        }
    }
}