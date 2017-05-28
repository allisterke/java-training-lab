package leetcode.a179;

import java.util.Arrays;
import java.util.List;

public class Solution {
    int compare(char[] a, int oa, char[] b, int ob) {
        int i = oa, j = ob;
        for(; i < a.length && j < b.length; ++ i, ++ j) {
            if(a[i] != b[j]) {
                return Character.compare(a[i], b[j]);
            }
        }
        if(i == a.length && j == b.length) {
            return 0;
        }
        if(i < a.length) {
            return compare(a, i, b, ob);
        }
        else {
            return compare(a, oa, b, j);
        }
    }
    public String largestNumber(int[] nums) {
        char[][] strings = new char[nums.length][];
        for(int i = 0; i < nums.length; ++ i) {
            strings[i] = String.valueOf(nums[i]).toCharArray();
        }
        Arrays.sort(strings, (a, b) -> - compare(a, 0, b, 0));
        StringBuilder sb = new StringBuilder();
        for(char[] s :strings) {
            sb.append(s);
        }
        sb.reverse();
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.setLength(sb.length() - 1);
        }
        return sb.reverse().toString();
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