package leetcode.a722;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> removeComments(String[] source) {
        StringBuilder sb = new StringBuilder();
        for(String line : source) {
            sb.append(line);
            sb.append('\n');
        }
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < sb.length(); ++ i) {
            if(sb.charAt(i) == '/') {
                if(i + 1 < sb.length()) {
                    if(sb.charAt(i+1) == '/') {
                        for(i += 2; i < sb.length() && sb.charAt(i) != '\n'; ++ i) ;
                        -- i;
                        continue;
                    }
                    else if(sb.charAt(i+1) == '*') {
                        for(i += 2; sb.charAt(i) != '*' || sb.charAt(i+1) != '/'; ++ i);
                        i += 2;
                        -- i;
                        continue;
                    }
                }
            }
            output.append(sb.charAt(i));
        }
        String[] tmp = output.toString().split("\n");
        List<String> result = new ArrayList<>();
        for(String s : tmp) {
            if(!s.isEmpty()) {
                result.add(s);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
                        {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"},
                        {"a/*comment", "line", "more_comment*/b"}
                }
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.removeComments(tests.get(i)));
        }
    }
}