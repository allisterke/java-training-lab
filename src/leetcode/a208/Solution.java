package leetcode.a208;

import java.util.Arrays;
import java.util.List;

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

public class Solution {


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