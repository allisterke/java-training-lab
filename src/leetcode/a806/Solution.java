package leetcode.a806;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[] numberOfLines(int[] widths, String S) {
        int lines = 1;
        int last = 0;
        for (char c : S.toCharArray()) {
            int i = c - 'a';
            if (last + widths[i] > 100) {
                ++ lines;
                last = widths[i];
            }
            else {
                last += widths[i];
            }
        }
        return new int[] {lines, last};
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