package leetcode.a634;

import java.util.Arrays;
import java.util.List;

public class Solution {
    static long MOD = (long)(1e9 + 7);
    public int findDerangement(int n) {
        long a = 1;
        long result = sign(n) * a;
        for(int i = n-1; i >= 0; -- i) {
            a *= (i+1);
            a %= MOD;
            result += sign(i) * a;
            result = (result + MOD) % MOD;
        }
        return (int)result;
    }

    int sign(int n) {
        return (n & 1) == 1 ? -1 : 1;
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