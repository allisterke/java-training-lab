package leetcode.a816;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> ambiguousCoordinates(String S) {
        List<String> ac = new ArrayList<>();
        S = S.substring(1, S.length() - 1);
        for (int i = 0; i < S.length() - 1; ++ i) {
            List<String> left = possibleNotation(S.substring(0, i+1));
            List<String> right = possibleNotation(S.substring(i+1));
            if (!left.isEmpty() && !right.isEmpty()) {
                for (String c1 : left) {
                    for (String c2 : right) {
                        ac.add("(" + c1 + ", " + c2 + ")");
                    }
                }
            }
        }
        return ac;
    }

    List<String> possibleNotation(String S) {
        List<String> notations = new ArrayList<>();
        if(isValid(S, false)) {
            notations.add(S);
        }
        if (S.length() > 1) {
            for (int i = 0; i < S.length() - 1; ++i) {
                String str = S.substring(0, i + 1) + '.' + S.substring(i+1);
                if (isValid(str, true)) {
                    notations.add(str);
                }
            }
        }
        return notations;
    }

    boolean isValid(String S, boolean dot) {
        if (!dot) {
            return S.charAt(0) != '0' || S.length() == 1;
        }
        else {
            return S.charAt(S.length() - 1) != '0' &&
                    (S.charAt(0) != '0' || S.charAt(1) == '.');
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "(123)",
                "(00011)",
                "(0123)",
                "(100)"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        s.ambiguousCoordinates("(..)");
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.ambiguousCoordinates(tests.get(i)));
        }
    }
}