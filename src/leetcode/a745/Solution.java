package leetcode.a745;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WordFilter {

    Map<List<String>, Integer> cache = new HashMap<>();

    public WordFilter(String[] words) {
        for (int i = words.length - 1; i >= 0; -- i) {
            String word = words[i];
            for(int p = 0; p <= word.length(); ++ p) {
                for (int q = 0; q <= word.length(); ++ q) {
                    String prefix = word.substring(0, p);
                    String suffix = word.substring(word.length() - q);
                    List<String> key = Arrays.asList(prefix, suffix);
                    if(!cache.containsKey(key)) {
                        cache.put(key, i);
                    }
                }
            }
        }
    }

    public int f(String prefix, String suffix) {
        Integer n = cache.get(Arrays.asList(prefix, suffix));
        return n != null ? n : -1;
    }
}

public class Solution {


    public static void main(String[] args) {
        WordFilter filter = new WordFilter(new String[] {"apple"});
        System.out.println(filter.f("a", "e"));
        System.out.println(filter.f("b", ""));
    }
}