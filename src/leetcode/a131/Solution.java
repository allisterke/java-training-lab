package leetcode.a131;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    boolean isPalindrome(String s, int begin, int end) {
        for(; begin < end; ++ begin, -- end) {
            if (s.charAt(begin) != s.charAt(end)) {
                return false;
            }
        }
        return true;
    }

    boolean[][] checkPalindrome(String s) {
        boolean[][] mark = new boolean[s.length()][];
        for(int i = 0; i < mark.length; ++ i) {
            mark[i] = new boolean[s.length()];
        }
        for(int k = 0; k < s.length(); ++ k) {
            for(int i = 0; i + k < s.length(); ++ i) {
                if(k == 0) {
                    mark[i][i+k] = true;
                }
                else if(k == 1) {
                    mark[i][i+k] = s.charAt(i) == s.charAt(i+k);
                }
                else {
                    mark[i][i+k] = s.charAt(i) == s.charAt(i+k) && mark[i+1][i+k-1];
                }
            }
        }
        return mark;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(!s.isEmpty()) {
            List<String> part = new ArrayList<>();
            boolean[][] mark = checkPalindrome(s);
            tryPartition(s, 0, mark, part, result);
        }
        return result;
    }

    void tryPartition(String s, int offset, boolean[][] mark, List<String> part, List<List<String>> result) {
        if(offset >= s.length()) {
            result.add(new ArrayList<>(part));
            return;
        }
        for(int i = offset; i < s.length(); ++ i) {
//            if(isPalindrome(s, offset, i)) {
            if(mark[offset][i]) {
                part.add(s.substring(offset, i+1));
                tryPartition(s, i+1, mark, part, result);
                part.remove(part.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList("aab");
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.partition(tests.get(i)));
        }
    }
}