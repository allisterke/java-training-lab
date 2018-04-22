package leetcode.a820;

import java.util.Arrays;
import java.util.List;

public class Solution {
    class Trie {

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

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie t = this;
            for(int i = 0; i < word.length(); ++ i) {
                int index = word.charAt(i) - 'a';
                if(t.next[index] == null) {
                    return false;
                }
                t = t.next[index];
            }
            return t.finished;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Trie t = this;
            for(int i = 0; i < prefix.length(); ++ i) {
                int index = prefix.charAt(i) - 'a';
                if(t.next[index] == null) {
                    return false;
                }
                t = t.next[index];
            }
            return true;
        }
    }

    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            StringBuilder builder = new StringBuilder();
            builder.append(word);
            builder.reverse();
            trie.insert(builder.toString());
        }
        return totalLength(trie, 0);
    }

    int totalLength(Trie trie, int depth) {
        int length = 0;
        boolean end = true;
        for (int i = 0; i < trie.next.length; ++ i) {
            if (trie.next[i] != null) {
                length += totalLength(trie.next[i], depth + 1);
                end = false;
            }
        }
        if (end) {
            return depth + 1;
        }
        else {
            return length;
        }
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
                        {"time", "me", "bell"}
                }
        );
        List<Integer> results = Arrays.asList(
                10
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.minimumLengthEncoding(tests.get(i)));
        }
    }
}