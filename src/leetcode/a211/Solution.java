package leetcode.a211;

import java.util.Arrays;
import java.util.List;

class WordDictionary {

    class Trie {
        Trie[] next = new Trie[26];
        boolean finished = false;
    }

    Trie tree = new Trie();

    /** Initialize your data structure here. */
    public WordDictionary() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Trie t = tree;
        for(int i = 0; i < word.length(); ++ i) {
            int index = word.charAt(i) - 'a';
            if(t.next[index] == null) {
                t.next[index] = new Trie();
            }
            t = t.next[index];
        }
        t.finished = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, tree);
    }

    boolean search(String word, int i, Trie t) {
        if(t == null) {
            return false;
        }
        if(i >= word.length()) {
            return t.finished;
        }
        char c = word.charAt(i);
        if(c == '.') {
            for(int j = 0; j < 26; ++ j) {
                if(search(word, i+1, t.next[j])) {
                    return true;
                }
            }
            return false;
        }
        else {
            return search(word, i+1, t.next[c - 'a']);
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        System.out.println(wd.search("pad"));
        System.out.println(wd.search("bad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search("b.."));
    }
}