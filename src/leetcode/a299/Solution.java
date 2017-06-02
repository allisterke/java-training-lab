package leetcode.a299;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int[] secretDigits = new int[10];
        int[] guessDigits = new int[10];
        for(int i = 0; i < secret.length(); ++ i) {
            int i1 = secret.charAt(i) - '0';
            int i2 = guess.charAt(i) - '0';
            if(i1 == i2) {
                ++ bulls;
            }
            else {
                ++secretDigits[i1];
                ++guessDigits[i2];
            }
        }
        int cows = 0;
        for(int i = 0; i < secretDigits.length; ++ i) {
            cows += Math.min(secretDigits[i], guessDigits[i]);
        }
        return String.format("%dA%dB", bulls, cows);
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