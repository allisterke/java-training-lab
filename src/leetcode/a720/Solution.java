package leetcode.a720;

import java.util.*;

public class Solution {
    public String longestWord(String[] words) {
        Set<String> dictionary = new HashSet<>();
        for(String word : words) {
            dictionary.add(word);
        }
        Set<String> lastLevel = new HashSet<>();
        lastLevel.add("");
        while(true) {
            Set<String> currentLevel = new HashSet<>();
            for (String word : lastLevel) {
                for (char c = 'a'; c <= 'z'; ++ c) {
                    String o = word + c;
                    if(dictionary.contains(o)) {
                        currentLevel.add(o);
                    }
//                    o = c + word;
//                    if(dictionary.contains(o)) {
//                        currentLevel.add(o);
//                    }
//                    for(int i = 1; i < word.length(); ++ i) {
//                        o = word.substring(0, i) + c + word.substring(i);
//                        if(dictionary.contains(o)) {
//                            currentLevel.add(o);
//                        }
//                    }
                }
            }
            if(currentLevel.isEmpty()) {
                break;
            }
            else {
                lastLevel = currentLevel;
            }
        }
        return lastLevel.stream().min(Comparator.naturalOrder()).get();
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[][] {
                        {"w","wo","wor","worl", "world"},
                        {"a", "banana", "app", "appl", "ap", "apply", "apple"}
                }
        );
        List<String> results = Arrays.asList(
                "world",
                "apple"
        );

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.longestWord(tests.get(i)));
        }
    }
}