package leetcode.a819;

import java.util.*;

public class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < paragraph.length(); ) {
            if(!Character.isAlphabetic(paragraph.charAt(i))) {
                ++ i;
                continue;
            }
            int j = i;
            builder.setLength(0);
            for (; j < paragraph.length() && Character.isAlphabetic(paragraph.charAt(j)); ++ j) {
                builder.append(Character.toLowerCase(paragraph.charAt(j)));
            }
            String word = builder.toString();
            if (!bannedSet.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            i = j;
        }
        int mcc = 0;
        String mcw = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > mcc) {
                mcc = entry.getValue();
                mcw = entry.getKey();
            }
        }
        return mcw;
    }

    public static void main(String[] args) {
        List tests = Arrays.asList();
        List results = Arrays.asList();

        Solution s = new Solution();
        System.out.printf(s.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
        }
    }
}