package leetcode.a784;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    StringBuilder builder;
    List<String> permutation;

    public List<String> letterCasePermutation(String S) {
        builder = new StringBuilder();
        permutation = new ArrayList<>();
        permutate(S, 0);
        return permutation;
    }

    void permutate(String S, int index) {
        if (index >= S.length()) {
            permutation.add(builder.toString());
            return;
        }
        char c = S.charAt(index);
        if(Character.isAlphabetic(c)) {
            builder.append(Character.toLowerCase(c));
            permutate(S, index + 1);
            builder.setLength(builder.length() - 1);

            builder.append(Character.toUpperCase(c));
            permutate(S, index + 1);
            builder.setLength(builder.length() - 1);
        }
        else {
            builder.append(c);
            permutate(S, index + 1);
            builder.setLength(builder.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "a1b2",
                "3z4",
                "12345"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.letterCasePermutation(tests.get(i)));
        }
    }
}