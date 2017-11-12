package leetcode.a727;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String minWindow(String S, String T) {
        int[][] next = new int[S.length()][26];
        for(int i = 0; i < S.length(); ++ i) {
            for(int c = 'a'; c <= 'z'; ++ c) {
                if(i == 0 || c == S.charAt(i)) {
                    int k = i+1;
                    for(; k < S.length() && S.charAt(k) != c; ++ k) ;
                    next[i][c - 'a'] = k;
                }
                else {
                    next[i][c - 'a'] = next[i-1][c - 'a'];
                }
            }
        }
        int mw = Integer.MAX_VALUE;
        int start = 0;
        for(int i = 0; i < S.length(); ++ i) {
            if(S.charAt(i) != T.charAt(0)) {
                continue;
            }
            int pos = i;
            for(int k = 1; k < T.length(); ++ k) {
                pos = next[pos][T.charAt(k) - 'a'];
                if(pos >= S.length()) {
                    break;
                }
            }
            if(pos < S.length()) {
                int w = pos - i + 1;
                if(w < mw) {
                    mw = w;
                    start = i;
                }
            }
        }
        if(mw != Integer.MAX_VALUE) {
            return S.substring(start, start + mw);
        }
        else {
            return "";
        }
    }

    public static void main(String[] args) {
        List<List<String>> tests = Arrays.asList(
                Arrays.asList("abcdebdde", "bde"),
                Arrays.asList("abccdebdde", "ae")
        );
        List<String> results = Arrays.asList(
                "bcde"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minWindow(tests.get(i).get(0), tests.get(i).get(1)));
        }
    }
}