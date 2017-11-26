package leetcode.a734;

import java.util.*;

public class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if(words1.length != words2.length) {
            return false;
        }
        Map<String, Set<String>> similar = new HashMap<>();
        for(String[] pair: pairs) {
            for(int i = 0; i <= 1; ++ i) {
                if (!similar.containsKey(pair[i])) {
                    similar.put(pair[i], new HashSet<>());
                }
                similar.get(pair[i]).add(pair[1 - i]);
            }
        }
        for (int i = 0; i < words1.length; ++ i) {
            String word1 = words1[i];
            String word2 = words2[i];
            if(word1.length() == word2.length() && word1.equals(word2)) {
                continue;
            }
            if(similar.containsKey(word1) && similar.get(word1).contains(word2)) {
                continue;
            }
            return false;
        }
        return true;
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