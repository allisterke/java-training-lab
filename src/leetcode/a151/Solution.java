package leetcode.a151;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        for(int i = words.length - 1; i >= 0; -- i) {
            sb.append(words[i]);
            if(i > 0) {
                sb.append(' ');
            }
        }
        return sb.toString();
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