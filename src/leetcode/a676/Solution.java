package leetcode.a676;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    static class MagicDictionary {

        Set<String> set = new HashSet<>();

        /** Initialize your data structure here. */
        public MagicDictionary() {

        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            for(String s : dict) {
                set.add(s);
            }
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            for(int i = 0; i < word.length(); ++ i) {
                for(char c = 'a'; c <= 'z'; ++ c) {
                    if(c == word.charAt(i)) {
                        continue;
                    }
                    if(set.contains(word.substring(0, i) + c + word.substring(i+1))) {
                        return true;
                    }
                }
            }
            return false;
        }
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