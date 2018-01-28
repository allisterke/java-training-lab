package leetcode.a767;

import java.util.*;

public class Solution {
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); ++ i) {
            char c = S.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        Map.Entry<Character, Integer>[] entries = new Map.Entry[map.size()];
        {
            int i = 0;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                entries[i ++] = entry;
            }
        }
        Arrays.sort(entries, Comparator.comparingInt(e -> - e.getValue()));
        if(entries[0].getValue() *2 > S.length() + 1) {
            return "";
        }
        else {
            StringBuilder[] builders = new StringBuilder[entries[0].getValue()];
            for (int i = 0; i < builders.length; ++ i) {
                builders[i] = new StringBuilder();
            }
            int k = 0;
            for (Map.Entry<Character, Integer> entry : entries) {
                for (int i = 0; i < entry.getValue(); ++ i) {
                    builders[k ++].append(entry.getKey());
                    if(k == builders.length) {
                        k = 0;
                    }
                }
            }
            StringBuilder composite = new StringBuilder();
            for (StringBuilder builder : builders) {
                composite.append(builder);
            }
            return composite.toString();
        }
    }

    public static void main(String[] args) {
        List<String> tests = Arrays.asList(
                "a",
                "aabb",
                "aab",
                "aaab",
                "vvvlo"
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.reorganizeString(tests.get(i)));
        }
    }
}