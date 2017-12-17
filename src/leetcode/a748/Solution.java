package leetcode.a748;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] licence = parseLetters(licensePlate);
        String shortest = null;
        for(String word: words) {
            int[] letters = parseLetters(word);
            if(contains(letters, licence)) {
                if(shortest == null || shortest.length() > word.length()) {
                    shortest = word;
                }
            }
        }
        return shortest;
    }

    int[] parseLetters(String s) {
        int[] letters = new int[26];
        for (int i = 0; i < s.length(); ++ i) {
            char c = s.charAt(i);
            if(Character.isLowerCase(c)) {
                ++ letters[c - 'a'];
            }
            else if(Character.isUpperCase(c)) {
                ++ letters[c - 'A'];
            }
        }
        return letters;
    }

    boolean contains(int[] a, int[] b) {
        for(int i = 0; i < 26; ++ i) {
            if(a[i] < b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> licences = Arrays.asList(
                "1s3 PSt",
                "1s3 456"
        );
        List<String[]> words = Arrays.asList(
                new String[][] {
                        {"step", "steps", "stripe", "stepple"},
                        {"looks", "pest", "stew", "show"}
                }
        );
        List<String> results = Arrays.asList(
                "steps",
                "pest"
        );

        Solution s = new Solution();
        for(int i = 0; i < licences.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.shortestCompletingWord(licences.get(i), words.get(i)));
        }
    }
}