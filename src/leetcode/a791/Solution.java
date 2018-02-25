package leetcode.a791;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Solution {
    public String customSortString(String S, String T) {
        int[] position = new int[26];
        for (int i = 0; i < S.length(); ++ i) {
            int c = S.charAt(i) - 'a';
            position[c] = i;
        }
        Character[] ts = new Character[T.length()];
        for (int i = 0; i < T.length(); ++ i) {
            ts[i] = T.charAt(i);
        }
        Arrays.sort(ts, Comparator.comparingInt(c -> position[c - 'a']));
        StringBuilder builder = new StringBuilder();
        for (char c : ts) {
            builder.append(c);
        }
        return builder.toString();
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