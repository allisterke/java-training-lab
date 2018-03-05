package leetcode.a792;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int[][] next = new int[26][S.length()];
        for (int i = 0; i < 26; ++ i) {
            for(int j = 0; j < S.length(); ++ j) {
                if (j == 0 || S.charAt(j) - 'a' == i) {
                    int k = j + 1;
                    for (; k < S.length() && S.charAt(k) - 'a' != i; ++ k) ;
                    next[i][j] = k;
                }
                else {
                    next[i][j] = next[i][j-1];
                }
            }
        }
        int count = 0;
        for(String word : words) {
            int pos = S.charAt(0) == word.charAt(0) ? 0 : next[word.charAt(0) - 'a'][0];
            if (pos == S.length()) {
                continue;
            }
            boolean contains = true;
            for (int i = 1; i < word.length(); ++ i) {
                pos = next[word.charAt(i) - 'a'][pos];
                if (pos == S.length()) {
                    contains = false;
                    break;
                }
            }
            if(contains) {
                ++ count;
            }
        }
        return count;
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