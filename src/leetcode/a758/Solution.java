package leetcode.a758;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Solution {
    static class Trie {

        Trie[] next = new Trie[26];
        boolean finished = false;

        /** Initialize your data structure here. */
        public Trie() {

        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie t = this;
            for(int i = 0; i < word.length(); ++ i) {
                int index = word.charAt(i) - 'a';
                if(t.next[index] == null) {
                    t.next[index] = new Trie();
                }
                t = t.next[index];
            }
            t.finished = true;
        }

        public int longestMath(String word, int offset) {
            Trie t = this;
            int longest = offset;
            for (int i = 0; offset + i < word.length(); ++ i) {
                int index = word.charAt(i+offset) - 'a';
                if(t.next[index] == null) {
                    break;
                }
                if(t.next[index].finished) {
                    longest = offset + i + 1;
                }
                t = t.next[index];
            }
            return longest;
        }
    }

    public String boldWords(String[] words, String S) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < S.length(); ) { // TODO: next
            int end = trie.longestMath(S, i);
            if(end == i) {
                builder.append(S.charAt(i));
                ++ i;
            }
            else {
                int start = i;
                for (++ i; i < end; ++ i) {
                    end = Math.max(end, trie.longestMath(S, i));
                }
                builder.append("<b>");
                for(int j = start; j < end; ++ j) {
                    builder.append(S.charAt(j));
                }
                builder.append("</b>");
            }
        }
        return builder.toString().replace("</b><b>", "");
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
                        {"ab", "bc"},
                        {"b","dee","a","ee","c"}
                }
        );
        List<String> ss = Arrays.asList(
                "aabcd",
                "cebcecceab"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.boldWords(tests.get(i), ss.get(i)));
        }
    }
}