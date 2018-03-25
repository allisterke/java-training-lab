package leetcode.a804;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder builder = new StringBuilder();
            for (char c : word.toCharArray()) {
                builder.append(morse[c - 'a']);
            }
            set.add(builder.toString());
        }
        return set.size();
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