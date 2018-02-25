package leetcode.a788;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int rotatedDigits(int N) {
        int count = 0;
        for(int i = 1; i <= N; ++ i) {
            if(validNumber(i)) {
                ++ count;
            }
        }
        return count;
    }

    boolean validNumber(int N) {
        int diff = 0;
        while(N > 0) {
            int last = N % 10;
            N /= 10;
            switch (last) {
                case 0:
                case 1:
                case 8:
                    continue;
                case 2:
                case 5:
                case 6:
                case 9:
                    ++ diff;
                    break;
                default:
                    return false;
            }
        }
        return diff > 0;
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