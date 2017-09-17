package leetcode.a677;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Trie {

    Trie[] next = new Trie[26];
    int value = 0;

    /** Initialize your data structure here. */
    public Trie() {

    }

    /** Inserts a word into the trie. */
    public void insert(String word, int value) {
        Trie t = this;
        for(int i = 0; i < word.length(); ++ i) {
            int index = word.charAt(i) - 'a';
            if(t.next[index] == null) {
                t.next[index] = new Trie();
            }
            t = t.next[index];
        }
        t.value = value;
    }

    /** Returns if the word is in the trie. */
    public Trie search(String word) {
        Trie t = this;
        for(int i = 0; i < word.length(); ++ i) {
            int index = word.charAt(i) - 'a';
            if(t.next[index] == null) {
                return null;
            }
            t = t.next[index];
        }
        return t;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public int sum(String prefix) {
        Trie t = this;
        for(int i = 0; i < prefix.length(); ++ i) {
            int index = prefix.charAt(i) - 'a';
            if(t.next[index] == null) {
                return 0;
            }
            t = t.next[index];
        }
        return sum(t);
    }

    int sum(Trie trie) {
        if(trie == null) {
            return 0;
        }
        int s = trie.value;
        for(Trie t : trie.next) {
            s += sum(t);
        }
        return s;
    }
}

class MapSum {

    Trie root = new Trie();

    /** Initialize your data structure here. */
    public MapSum() {

    }

    public void insert(String key, int val) {
        root.insert(key, val);
    }

    public int sum(String prefix) {
        return root.sum(prefix);
    }
}

public class Solution {


    public static void main(String[] args) {
        MapSum ms = new MapSum();
        ms.insert("apple", 3);
        System.out.println(ms.sum("ap"));
        ms.insert("app", 2);
        System.out.println(ms.sum("ap"));
    }
}