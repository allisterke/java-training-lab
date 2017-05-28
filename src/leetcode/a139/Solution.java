package leetcode.a139;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    static class Trie {
        Trie next[] = new Trie[26];
        boolean finish = false;
    }

    Trie trie = new Trie();

    void buildTrie(List<String> wordDict) {
        for(String word : wordDict) {
            if(word.isEmpty()) {
                continue;
            }
            Trie t = trie;
            for(int i = 0; i < word.length(); ++ i) {
                int index = word.charAt(i) - 'a';
                if(t.next[index] == null) {
                    t.next[index] = new Trie();
                }
                t = t.next[index];
            }
            t.finish = true;
        }
    }

    Map<Integer, Boolean> cache = new HashMap<>();

    boolean wordBreak(String s, int offset) {
        if(offset >= s.length()) {
            return true;
        }
        if(!cache.containsKey(offset)) {
            Trie t = trie;
            boolean breakable = false;
            for (int i = offset; i < s.length(); ++i) {
                int index = s.charAt(i) - 'a';
                if (t.next[index] == null) {
                    break;
                }
                if (t.next[index].finish && wordBreak(s, i + 1)) {
                    breakable = true;
                    break;
                }
                t = t.next[index];
            }
            cache.put(offset, breakable);
        }
        return cache.get(offset);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        buildTrie(wordDict);
        return wordBreak(s, 0);
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "leetcode",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
        );
        List<List<String>> dicts = Arrays.asList(
                Arrays.asList("leet", "code"),
                Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.wordBreak(tests.get(i), dicts.get(i)));
        }
    }
}