package leetcode.a660;

import java.util.Arrays;
import java.util.List;

public class Solution {
    public int newInteger(int n) {
        return newIntegerFromZero(n+1);
    }
    public int newIntegerFromZero(int n) {
        if(n <= 10) {
            return n - 1;
        }
        long M = 9;
        int digit = 1;
        while (M < n) {
            M *= 9;
            ++ digit;
        }
        int k = 1;
        M /= 9;
        -- digit;
        while (k * M < n) {
            ++ k;
        }
        -- k;
        int N = k;
        for(int i = 0; i < digit; ++ i) {
            N *= 10;
        }
        return N + newIntegerFromZero((int)(n - k * M));
    }

    public static void main(String[] args) {
        List<Integer> tests = Arrays.asList(
                9, 11, 81
        );
        List results = Arrays.asList();

        Solution s = new Solution();
        for(int i = 0; i < tests.size(); ++ i) {
            System.out.print("case ");
            System.out.println(i);
            System.out.println(s.newInteger(tests.get(i)));
        }
    }
}