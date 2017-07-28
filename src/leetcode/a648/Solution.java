package leetcode.a648;

import java.util.Arrays;
import java.util.List;

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

        public String getRoot(String word) {
            StringBuilder sb = new StringBuilder();
            Trie t = this;
            for(int i = 0; i < word.length(); ++ i) {
                sb.append(word.charAt(i));
                int index = word.charAt(i) - 'a';
                if(t.next[index] == null) {
                    return word;
                }
                else if(t.next[index].finished) {
                    return sb.toString();
                }
                else {
                    t = t.next[index];
                }
            }
            return word;
        }
    }

    public String replaceWords(List<String> dict, String sentence) {
        Trie tree = new Trie();
        for(String s : dict) {
            tree.insert(s);
        }
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(String word : words) {
            sb.append(tree.getRoot(word));
            sb.append(' ');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
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