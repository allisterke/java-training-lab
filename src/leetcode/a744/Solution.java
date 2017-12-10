package leetcode.a744;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        boolean[] alphabet = new boolean[26];
        for (char c : letters) {
            alphabet[c - 'a'] = true;
        }
        int t = target - 'a';
        for (int i = 1; i < 26; ++ i) {
            if (alphabet[(t + i) % 26]) {
                return (char)('a' + (t + i) % 26);
            }
        }
        return 0;
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